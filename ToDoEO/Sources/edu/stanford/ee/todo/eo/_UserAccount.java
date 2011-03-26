// $LastChangedRevision: 4733 $ DO NOT EDIT.  Make changes to UserAccount.java instead.
package edu.stanford.ee.todo.eo;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

import er.extensions.eof.*;
import er.extensions.foundation.*;

@SuppressWarnings("all")
public abstract class _UserAccount extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "UserAccount";

  // Attribute Keys
  public static final ERXKey<String> EMAIL = new ERXKey<String>("email");
  public static final ERXKey<String> FULL_NAME = new ERXKey<String>("fullName");
  public static final ERXKey<String> PASSWORD = new ERXKey<String>("password");
  public static final ERXKey<String> USERNAME = new ERXKey<String>("username");
  // Relationship Keys
  public static final ERXKey<edu.stanford.ee.todo.eo.Todo> TODOS = new ERXKey<edu.stanford.ee.todo.eo.Todo>("todos");

  // Attributes
  public static final String EMAIL_KEY = EMAIL.key();
  public static final String FULL_NAME_KEY = FULL_NAME.key();
  public static final String PASSWORD_KEY = PASSWORD.key();
  public static final String USERNAME_KEY = USERNAME.key();
  // Relationships
  public static final String TODOS_KEY = TODOS.key();

  private static Logger LOG = Logger.getLogger(_UserAccount.class);

  public UserAccount localInstanceIn(EOEditingContext editingContext) {
    UserAccount localInstance = (UserAccount)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String email() {
    return (String) storedValueForKey("email");
  }

  public void setEmail(String value) {
    if (_UserAccount.LOG.isDebugEnabled()) {
    	_UserAccount.LOG.debug( "updating email from " + email() + " to " + value);
    }
    takeStoredValueForKey(value, "email");
  }

  public String fullName() {
    return (String) storedValueForKey("fullName");
  }

  public void setFullName(String value) {
    if (_UserAccount.LOG.isDebugEnabled()) {
    	_UserAccount.LOG.debug( "updating fullName from " + fullName() + " to " + value);
    }
    takeStoredValueForKey(value, "fullName");
  }

  public String password() {
    return (String) storedValueForKey("password");
  }

  public void setPassword(String value) {
    if (_UserAccount.LOG.isDebugEnabled()) {
    	_UserAccount.LOG.debug( "updating password from " + password() + " to " + value);
    }
    takeStoredValueForKey(value, "password");
  }

  public String username() {
    return (String) storedValueForKey("username");
  }

  public void setUsername(String value) {
    if (_UserAccount.LOG.isDebugEnabled()) {
    	_UserAccount.LOG.debug( "updating username from " + username() + " to " + value);
    }
    takeStoredValueForKey(value, "username");
  }

  public NSArray<edu.stanford.ee.todo.eo.Todo> todos() {
    return (NSArray<edu.stanford.ee.todo.eo.Todo>)storedValueForKey("todos");
  }

  public NSArray<edu.stanford.ee.todo.eo.Todo> todos(EOQualifier qualifier) {
    return todos(qualifier, null, false);
  }

  public NSArray<edu.stanford.ee.todo.eo.Todo> todos(EOQualifier qualifier, boolean fetch) {
    return todos(qualifier, null, fetch);
  }

  public NSArray<edu.stanford.ee.todo.eo.Todo> todos(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<edu.stanford.ee.todo.eo.Todo> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(edu.stanford.ee.todo.eo.Todo.OWNER_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = edu.stanford.ee.todo.eo.Todo.fetchTodos(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = todos();
      if (qualifier != null) {
        results = (NSArray<edu.stanford.ee.todo.eo.Todo>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<edu.stanford.ee.todo.eo.Todo>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTodos(edu.stanford.ee.todo.eo.Todo object) {
    includeObjectIntoPropertyWithKey(object, "todos");
  }

  public void removeFromTodos(edu.stanford.ee.todo.eo.Todo object) {
    excludeObjectFromPropertyWithKey(object, "todos");
  }

  public void addToTodosRelationship(edu.stanford.ee.todo.eo.Todo object) {
    if (_UserAccount.LOG.isDebugEnabled()) {
      _UserAccount.LOG.debug("adding " + object + " to todos relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToTodos(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, "todos");
    }
  }

  public void removeFromTodosRelationship(edu.stanford.ee.todo.eo.Todo object) {
    if (_UserAccount.LOG.isDebugEnabled()) {
      _UserAccount.LOG.debug("removing " + object + " from todos relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromTodos(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, "todos");
    }
  }

  public edu.stanford.ee.todo.eo.Todo createTodosRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Todo");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "todos");
    return (edu.stanford.ee.todo.eo.Todo) eo;
  }

  public void deleteTodosRelationship(edu.stanford.ee.todo.eo.Todo object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "todos");
    editingContext().deleteObject(object);
  }

  public void deleteAllTodosRelationships() {
    Enumeration objects = todos().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTodosRelationship((edu.stanford.ee.todo.eo.Todo)objects.nextElement());
    }
  }


  public static UserAccount createUserAccount(EOEditingContext editingContext, String fullName
, String username
) {
    UserAccount eo = (UserAccount) EOUtilities.createAndInsertInstance(editingContext, _UserAccount.ENTITY_NAME);    
		eo.setFullName(fullName);
		eo.setUsername(username);
    return eo;
  }

  public static NSArray<UserAccount> fetchAllUserAccounts(EOEditingContext editingContext) {
    return _UserAccount.fetchAllUserAccounts(editingContext, null);
  }

  public static NSArray<UserAccount> fetchAllUserAccounts(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _UserAccount.fetchUserAccounts(editingContext, null, sortOrderings);
  }

  public static NSArray<UserAccount> fetchUserAccounts(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_UserAccount.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<UserAccount> eoObjects = (NSArray<UserAccount>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static UserAccount fetchUserAccount(EOEditingContext editingContext, String keyName, Object value) {
    return _UserAccount.fetchUserAccount(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static UserAccount fetchUserAccount(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<UserAccount> eoObjects = _UserAccount.fetchUserAccounts(editingContext, qualifier, null);
    UserAccount eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (UserAccount)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one UserAccount that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static UserAccount fetchRequiredUserAccount(EOEditingContext editingContext, String keyName, Object value) {
    return _UserAccount.fetchRequiredUserAccount(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static UserAccount fetchRequiredUserAccount(EOEditingContext editingContext, EOQualifier qualifier) {
    UserAccount eoObject = _UserAccount.fetchUserAccount(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no UserAccount that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static UserAccount localInstanceIn(EOEditingContext editingContext, UserAccount eo) {
    UserAccount localInstance = (eo == null) ? null : (UserAccount)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
