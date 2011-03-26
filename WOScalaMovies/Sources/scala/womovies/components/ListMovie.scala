package scala.womovies.components

import com.webobjects.appserver.{WOComponent, WOContext}

import scala.womovies.Session

import scala.reflect.BeanProperty
import scala.collection.JavaConversions._

class ListMovie(context: WOContext) extends WOComponent(context: WOContext) {
	
	// accessors
	@BeanProperty var index: Int = 0
	
	override def session = super.session.asInstanceOf[Session]
	
	lazy val movies = session.movies

	// actions
	def next = {
		session.currentBatchIndex = (session.currentBatchIndex + 1) % session.pageCount
		pageWithName("ListMovie").asInstanceOf[ListMovie]
	}
	
	def previous = {
		session.currentBatchIndex = (session.pageCount + session.currentBatchIndex - 1) % session.pageCount
		pageWithName("ListMovie").asInstanceOf[ListMovie]
	}
	
	def inspect = {
		val nextPage = pageWithName("InspectStudio").asInstanceOf[InspectStudio]
		nextPage.studio = session.studio.get
		nextPage
	}
	
	// accessors
	def rowClassString:String = {
		var rowClassString = "ObjRow ListMovieObjRow"
		if (index % 2 == 0) rowClassString = rowClassString.concat(" EvenObjRow")
		else rowClassString = rowClassString.concat(" OddObjRow")
		rowClassString
	}
}