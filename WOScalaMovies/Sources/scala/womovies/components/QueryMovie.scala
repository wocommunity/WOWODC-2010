package scala.womovies.components

import scala.reflect.BeanProperty

import com.webobjects.appserver.{WOComponent, WOContext}

import scala.womovies.Session

class QueryMovie(aContext: WOContext) extends WOComponent(aContext: WOContext) {	
	// actions
	def search = {
		pageWithName("ListMovie").asInstanceOf[ListMovie]
	}
}
