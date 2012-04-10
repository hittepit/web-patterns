package controllers
import org.scalatra.scalate.ScalateSupport
import org.scalatra.ScalatraFilter

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
}