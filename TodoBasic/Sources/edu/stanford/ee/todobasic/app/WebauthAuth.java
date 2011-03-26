package edu.stanford.ee.todobasic.app;

import com.webobjects.appserver.WOContext;



public class WebauthAuth {
	public static final String WebauthAuthenticate (WOContext context)
	{	
		// If unauthenticated, this will be blank
		// assumes that web location is WebAuth protected to restrict this setting
		return context.request().headerForKey("webauth_user");
	}
}
