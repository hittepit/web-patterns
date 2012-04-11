package managers

import scala.collection._
import bo.Item
import java.util.Date

object ItemManager {
	val db = mutable.Map(1 -> new Item(Some(1),"Book",25.0,new Date())
		,2-> new Item(Some(2),"Computer",659.25,new Date())
		,3-> new Item(Some(3),"House",152000,new Date()))
		
	var lastIndex = 3
	
	def findAll = db.values
	
	def find(id:Int) = try {
	  val item = db(id)
	  Some(item)
	} catch {
	  case _=> None
	}
	
	def save(item:Item) = item.id match{
	  case Some(id) => db+=(id->item)
	  case None => lastIndex+=1
	  				item.id = Some(lastIndex)
	  				db+=(item.id.get->item)
	}
	
	def exists(name:String) = db.exists((el:(Int,Item)) => el._2.name==name)
}