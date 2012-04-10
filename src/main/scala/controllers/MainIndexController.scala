package controllers
import org.scalatra.ScalatraFilter
import org.scalatra.scalate.ScalateSupport

class MainIndexController extends ScalatraFilter with ScalateSupport{
  get("/"){
    contentType="text/html"
    layoutTemplate("main","layout"->"/WEB-INF/views/layout.ssp")
  }
}