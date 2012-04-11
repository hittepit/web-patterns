package controllers
import org.scalatra.scalate.ScalateSupport
import org.scalatra.ScalatraFilter
import java.util.Date
import bo.Item
import managers.ItemManager
import java.text.DateFormat
import java.text.SimpleDateFormat

class FormController extends ScalatraFilter with ScalateSupport {
  val dateFormat = new SimpleDateFormat("dd/MM/yyyy")
	before(){
	  contentType="text/html"
	}
	
	get("/form/"){
	  layoutTemplate("/form/main","layout" -> "/WEB-INF/views/layout.ssp","items"->ItemManager.findAll.toList)
	}
	
	get("/form/item/create/"){
	  layoutTemplate("/form/simpleForm","layout" -> "/WEB-INF/views/layout.ssp",
	      "errors" -> Map[String,String](),
	      "name" -> "",
	      "price" -> "",
	      "date" -> "")
	}
	
	post("/form/item/create/"){
		val name = params("name").trim
		val price = params("price").trim
		val date = params("date").trim
		var errors = validate(name,price,date)
		if(ItemManager.exists(name)){
		  errors += "name"->"Ce nom existe déjà"
		}
		if(!errors.isEmpty){
		  layoutTemplate("/form/simpleForm","layout" -> "/WEB-INF/views/layout.ssp",
		      "errors" -> errors,
		      "name" -> name,
		      "price" -> price,
		      "date" -> date)
		} else {
			val item = new Item(name,price.toDouble,dateFormat.parse(date))
			ItemManager.save(item)
			redirect("/form/")
		}
	}
	
	get("/form/item/edit/:id"){
	  val item = ItemManager.find(params("id").toInt)
	  layoutTemplate("/form/simpleEditForm","layout" -> "/WEB-INF/views/layout.ssp",
	      "errors" -> Map[String,String](),
	      "name" -> item.get.name,
	      "price" -> item.get.price.toString(),
	      "date" -> dateFormat.format(item.get.availabilityDate),
	      "id" -> params("id"))
	}
	
	post("/form/item/edit/:id"){
		val name = params("name").trim
		val price = params("price").trim
		val date = params("date").trim
		val errors = validate(name,price,date)
		if(!errors.isEmpty){
		  layoutTemplate("/form/simpleEditForm","layout" -> "/WEB-INF/views/layout.ssp",
		      "errors" -> errors,
		      "name" -> name,
		      "price" -> price,
		      "date" -> date,
		      "id" -> params("id"))
		} else {
			val item = ItemManager.find(params("id").toInt).get
			item.name = name
			item.price = price.toDouble
			item.availabilityDate = dateFormat.parse(date)
			ItemManager.save(item)
			redirect("/form/")
		}
	}	
	def validate(name:String,price:String,date:String) = {
		var errors = Map[String,String]()
		if(name==null || name == ""){
		  errors += "name"->"Le nom de ne peut pas être vide"
		}
		if(price == null || price == ""){
		  errors += "price"->"Le prix ne peut pas être vide"
		} else {
		  try{
		    price.toDouble
		  } catch {
		    case _ => errors += "price" -> "Le prix n'est pas un nombre correct"
		  }
		}
		if(date == null || date == ""){
		  errors += "date" ->"La date ne peut pas être vide"
		} else {
		  try{
		    dateFormat.parse(date)
		  }catch{
		    case _ => errors += "date" ->"La date n'est pas correctement formée"
		  }
		}
		errors
	}
}