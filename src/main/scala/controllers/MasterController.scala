package controllers
import org.scalatra.scalate.ScalateSupport
import org.scalatra.ScalatraFilter
import managers.ItemManager

class MasterController extends ScalatraFilter with ScalateSupport {
	before(){
		contentType="text/html"
	}
  
	get("/master"){
	  redirect("/master/items/")
	}
  
	get("/master/"){
	  redirect("/master/items/")
	}
	
	get("/master/items/"){
		layoutTemplate("master/master","layout"->"/WEB-INF/views/layout.ssp","items"->ItemManager.findAll.toList)
	}
	
	get("/master/items/:id"){
	  ItemManager.find(params("id").toInt) match{
	    case Some(item) =>	  layoutTemplate("master/detail","layout"->"/WEB-INF/views/layout.ssp","item"->item)
	    case None => 	  layoutTemplate("master/nodetail","layout"->"/WEB-INF/views/layout.ssp")
	  }
	}
}