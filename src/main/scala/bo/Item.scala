package bo
import java.util.Date

class Item(var id:Option[Int], var name:String, var price:Double, var availabilityDate: Date) {
	def this(n:String,p:Double,d:Date) = this(None,n,p,d)
	
	override def equals(o:Any) = o match {
	  case i:Item => i.name == name
	  case _ => false
	}
	
	override def hashCode = name.hashCode()
}