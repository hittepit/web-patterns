package controllers
import org.scalatra.scalate.ScalateSupport
import org.scalatra.ScalatraFilter
import managers.ItemDao

class MasterController extends ScalatraFilter with ScalateSupport {
	before(){
		contentType="text/html"
	}
  
	get("/master"){
	  redirect("/master/item/")
	}
  
	get("/master/"){
	  redirect("/master/item/")
	}
	
	get("/master/item/"){
		layoutTemplate("master/master","items"->ItemDao.findAll.toList)
	}
	
	get("/master/item/:id"){
	  ItemDao.find(params("id").toInt) match{
	    case Some(item) =>	  layoutTemplate("master/detail","item"->item)
	    case None => 	  layoutTemplate("master/nodetail")
	  }
	}
}