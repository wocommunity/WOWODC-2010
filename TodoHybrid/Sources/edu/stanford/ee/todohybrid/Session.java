package edu.stanford.ee.todohybrid;

import edu.stanford.ee.todo.eo.UserAccount;
import er.extensions.appserver.ERXSession;

public class Session extends ERXSession {
	private static final long serialVersionUID = 1L;
	private UserAccount _currentUser;

	private MainNavigationController _navController;
	
	public Session() {
	}
	
	public MainNavigationController navController() {
		if (_navController == null) {
			_navController = new MainNavigationController(this);
		}
		return _navController;
	}

	public UserAccount currentUser() {
        return _currentUser;
    }
    
    public void setCurrentUser(UserAccount user) {
        _currentUser = user;
    }

}
