package controllers
import org.scalatra.scalate.ScalateSupport
import org.scalatra.ScalatraFilter
import managers.ItemDao
import java.util.Date

class FormController extends ScalatraFilter with ScalateSupport {
	before(){
	  contentType="text/html"
	}
	
	get("/form/"){
	  layoutTemplate("/form/main","layout" -> "/WEB-INF/views/layout.ssp")
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
		val errors = validate(name,price,date)
		if(!errors.isEmpty){
		  layoutTemplate("/form/simpleForm","layout" -> "/WEB-INF/views/layout.ssp",
		      "errors" -> errors,
		      "name" -> name,
		      "price" -> price,
		      "date" -> date)
		} else {
			redirect("/form/")
		}
	}
	
	def validate(name:String,price:String,date:String) = {
		var errors = Map[String,String]()
		if(name==null || name == ""){
		  errors += "name"->"Le nom de ne peut pas être vide"
		} else if(ItemDao.exists(name)){
		  errors += "name"->"Ce nom existe déjà"
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
		    new Date(date)
		  }catch{
		    case _ => errors += "date" ->"La date n'est pas correctement formée"
		  }
		}
		errors
	}
}