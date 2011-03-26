package com.wowodc2010.helloD2W;

import webobjectsexamples.businesslogic.rentals.common.User;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.directtoweb.D2W;
import com.webobjects.eoaccess.EOEntity;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.wowodc2010.helloD2W.components.Main;

import er.directtoweb.ERD2WDirectAction;
import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXFetchSpecification;
import er.extensions.eof.ERXQ;


public class DirectAction extends ERD2WDirectAction {
	public DirectAction(WORequest request) {
		super(request);
	}

	@Override
	public WOActionResults defaultAction() {
		return pageWithName(Main.class.getName());
	}
	
    /**
     * Checks if a page configuration is allowed to render.
     * Provide a more intelligent access scheme as the default just returns false. And
     * be sure to read the javadoc to the super class.
     * @param pageConfiguration
     * @return
     */
    protected boolean allowPageConfiguration(String pageConfiguration) {
        return false;
    }
    
	public WOActionResults loginAction() {
		
		String username = request().stringFormValueForKey(User.UsernameKey);
		String password = request().stringFormValueForKey(User.PasswordKey);

		//Check credentials
		EOEditingContext ec = ERXEC.newEditingContext();
		EOEntity e = EOUtilities.entityForClass(ec, User.class);
		EOQualifier q = ERXQ.equals(User.UsernameKey, username);
		ERXFetchSpecification<User> fs = new ERXFetchSpecification<User>(e.name(), q, null);
		NSArray<User> users = fs.fetchObjects(ec);
		boolean authFailed = true;
		if(users.count() == 1) {
			User u = users.lastObject();
			authFailed = !u.storedValueForKey(User.PasswordKey).equals(password);
			if(!authFailed) {
				Session session = (Session)session();
				session.setUser(u);
			}
		}
		
		//Display an error message to the user.
		if(authFailed) {
			Main main = pageWithName(Main.class);
			main.setErrorMessage("Authentication failed.");
			return main;
		}
				
		return D2W.factory().defaultPage(session());
	}
	
	public WOActionResults graphAction() {
		// In order for this to work, you must install GraphViz
		// http://www.graphviz.org/Download..php
		return D2W.factory().pageForConfigurationNamed("GraphVizPage", session());
	}
	
}
