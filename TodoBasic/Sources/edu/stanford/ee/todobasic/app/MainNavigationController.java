package edu.stanford.ee.todobasic.app;

import com.webobjects.appserver.WOComponent;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.ErrorPageInterface;
import com.webobjects.directtoweb.QueryPageInterface;

import edu.stanford.ee.todo.eo.Todo;
import edu.stanford.ee.todo.eo.UserAccount;

public class MainNavigationController {

	private Session _session;

	public MainNavigationController(Session s) {
		super();
		_session = s;
	}

	// NAV ACTIONS
	
	public WOComponent homeAction() {
        return D2W.factory().defaultPage(session());
    }
	
//	// ADMIN
//	
//	public WOComponent adminAction() {
//		return queryPageForEntityName(Talent.ENTITY_NAME);
//	}
//	
	// TODOS
	
	public WOComponent queryTodoAction() {
		return queryPageForEntityName(Todo.ENTITY_NAME);
	}
	
	public WOComponent createTodoAction() {
		return newObjectForEntityName(Todo.ENTITY_NAME);
	}
	
	// USER ACCOUNTS
	
	public WOComponent queryUserAccountAction() {
		return queryPageForEntityName(UserAccount.ENTITY_NAME);
	}
	
	public WOComponent createUserAccountAction() {
		return newObjectForEntityName(UserAccount.ENTITY_NAME);
	}
//	
//	// TALENT
//	
//	public WOComponent queryTalentAction() {
//		return queryPageForEntityName(Talent.ENTITY_NAME);
//	}
//	
//	public WOComponent createTalentAction() {
//		return newObjectForEntityName(Talent.ENTITY_NAME);
//	}
//	
//	// VOTING
//	
//	public WOComponent queryVotingAction() {
//		return queryPageForEntityName(Voting.ENTITY_NAME);
//	}
//	
//	public WOComponent createVotingAction() {
//		return newObjectForEntityName(Voting.ENTITY_NAME);
//	}
//	
//	// REVIEW
//	
//	public WOComponent queryReviewAction() {
//		return queryPageForEntityName(REVIEW);
//	}
//	
//	public WOComponent createReviewAction() {
//		return newObjectForEntityName(REVIEW);
//	}
	
	// GENERIC ACTIONS
	
    public WOComponent queryPageForEntityName(String entityName) {
        QueryPageInterface newQueryPage = D2W.factory().queryPageForEntityNamed(entityName, session());
        return (WOComponent) newQueryPage;
    }
    
    public WOComponent newObjectForEntityName(String entityName) {
        WOComponent nextPage = null;
        try {
            EditPageInterface epi = D2W.factory().editPageForNewObjectWithEntityNamed(entityName, session());
            epi.setNextPage(session().context().page());
            nextPage = (WOComponent) epi;
        } catch (IllegalArgumentException e) {
            ErrorPageInterface epf = D2W.factory().errorPage(session());
            epf.setMessage(e.toString());
            epf.setNextPage(session().context().page());
            nextPage = (WOComponent) epf;
        }
        return nextPage;
    }
    
    // ACCESSORS
    
    public Session session() {
		return _session;
	}

	public void setSession(Session s) {
		_session = s;
	}
}
