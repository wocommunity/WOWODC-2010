package com.wowodc2010.helloD2W;

import webobjectsexamples.businesslogic.rentals.common.User;
import er.corebusinesslogic.ERCoreBusinessLogic;
import er.extensions.appserver.ERXSession;

public class Session extends ERXSession {
	private static final long serialVersionUID = 1L;

	private MainNavigationController _navController;
	private User _user;
	
	public Session() {
	}
		
	public MainNavigationController navController() {
		if (_navController == null) {
			_navController = new MainNavigationController(this);
		}
		return _navController;
	}
	
	/*
	 * To use ERCoreUserPreferences requires:
	 * 
	 * 1. Implement ERCoreUserInterface on your user entity
	 * 
	 * 2. Set up the user entity when the app starts with
	 * ERCoreBusinessLogic.sharedInstance().addPreferenceRelationshipToActorEntity()
	 * 
	 * 3. Set the user as the actor at login (obviously) and on awake/sleep
	 * 
	 * 4. (optional) Implement a custom preference handler and set it with the property
	 * er.corebusinesslogic.ERCoreUserPreferences.handlerClassName
	 * 
	 */
	public void setUser(User user) {
		_user = user;
		ERCoreBusinessLogic.setActor(user());
	}
	
	public User user() {
		return _user;
	}
	
    public void awake() {
        super.awake();
        if (user() != null) {
            ERCoreBusinessLogic.setActor(user());
        }
    }
    
    public void sleep() {
        ERCoreBusinessLogic.setActor(null);
        super.sleep();
    }

    public boolean hasValidUser() {
    	return user() != null;
    }

}
