// Generated by the WOLips Templateengine Plug-in at Aug 15, 2009 2:15:59 AM
package scala.womovies.components

import scala.reflect.BeanProperty

import com.webobjects.appserver.{WOComponent, WOContext, WOActionResults}

class Main(context:WOContext) extends WOComponent(context:WOContext) {
	// accessors
	@BeanProperty var username = new String()
	@BeanProperty var password = new String()

	// actions
	def login = {
		pageWithName("QueryMovie")
	}
}
