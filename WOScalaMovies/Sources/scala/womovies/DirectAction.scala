package scala.womovies

import com.webobjects.appserver.{WOActionResults, WORequest, WODirectAction}

class DirectAction(request: WORequest) extends WODirectAction(request: WORequest) {

	override def defaultAction: WOActionResults = {
		pageWithName("Main")
	}
}
