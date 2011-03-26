// $LastChangedRevision: 4733 $ DO NOT EDIT.  Make changes to Todo.java instead.
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
public abstract class _Todo extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Todo";

  // Attribute Keys
  public static final ERXKey<Boolean> COMPLETED = new ERXKey<Boolean>("completed");
  public static final ERXKey<NSTimestamp> DATE_CREATED = new ERXKey<NSTimestamp>("dateCreated");
  public static final ERXKey<NSTimestamp> DUE_DATE = new ERXKey<NSTimestamp>("dueDate");
  public static final ERXKey<String> TASK = new ERXKey<String>("task");
  // Relationship Keys
  public static final ERXKey<edu.stanford.ee.todo.eo.FileAttachment> ASSOCIATED_NOTES = new ERXKey<edu.stanford.ee.todo.eo.FileAttachment>("associatedNotes");
  public static final ERXKey<edu.stanford.ee.todo.eo.UserAccount> OWNER = new ERXKey<edu.stanford.ee.todo.eo.UserAccount>("owner");

  // Attributes
  public static final String COMPLETED_KEY = COMPLETED.key();
  public static final String DATE_CREATED_KEY = DATE_CREATED.key();
  public static final String DUE_DATE_KEY = DUE_DATE.key();
  public static final String TASK_KEY = TASK.key();
  // Relationships
  public static final String ASSOCIATED_NOTES_KEY = ASSOCIATED_NOTES.key();
  public static final String OWNER_KEY = OWNER.key();

  private static Logger LOG = Logger.getLogger(_Todo.class);

  public Todo localInstanceIn(EOEditingContext editingContext) {
    Todo localInstance = (Todo)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Boolean completed() {
    return (Boolean) storedValueForKey("completed");
  }

  public void setCompleted(Boolean value) {
    if (_Todo.LOG.isDebugEnabled()) {
    	_Todo.LOG.debug( "updating completed from " + completed() + " to " + value);
    }
    takeStoredValueForKey(value, "completed");
  }

  public NSTimestamp dateCreated() {
    return (NSTimestamp) storedValueForKey("dateCreated");
  }

  public void setDateCreated(NSTimestamp value) {
    if (_Todo.LOG.isDebugEnabled()) {
    	_Todo.LOG.debug( "updating dateCreated from " + dateCreated() + " to " + value);
    }
    takeStoredValueForKey(value, "dateCreated");
  }

  public NSTimestamp dueDate() {
    return (NSTimestamp) storedValueForKey("dueDate");
  }

  public void setDueDate(NSTimestamp value) {
    if (_Todo.LOG.isDebugEnabled()) {
    	_Todo.LOG.debug( "updating dueDate from " + dueDate() + " to " + value);
    }
    takeStoredValueForKey(value, "dueDate");
  }

  public String task() {
    return (String) storedValueForKey("task");
  }

  public void setTask(String value) {
    if (_Todo.LOG.isDebugEnabled()) {
    	_Todo.LOG.debug( "updating task from " + task() + " to " + value);
    }
    takeStoredValueForKey(value, "task");
  }

  public edu.stanford.ee.todo.eo.UserAccount owner() {
    return (edu.stanford.ee.todo.eo.UserAccount)storedValueForKey("owner");
  }
  
  public void setOwner(edu.stanford.ee.todo.eo.UserAccount value) {
    takeStoredValueForKey(value, "owner");
  }

  public void setOwnerRelationship(edu.stanford.ee.todo.eo.UserAccount value) {
    if (_Todo.LOG.isDebugEnabled()) {
      _Todo.LOG.debug("updating owner from " + owner() + " to " + value);
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setOwner(value);
    }
    else if (value == null) {
    	edu.stanford.ee.todo.eo.UserAccount oldValue = owner();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "owner");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "owner");
    }
  }
  
  public NSArray<edu.stanford.ee.todo.eo.FileAttachment> associatedNotes() {
    return (NSArray<edu.stanford.ee.todo.eo.FileAttachment>)storedValueForKey("associatedNotes");
  }

  public NSArray<edu.stanford.ee.todo.eo.FileAttachment> associatedNotes(EOQualifier qualifier) {
    return associatedNotes(qualifier, null);
  }

  public NSArray<edu.stanford.ee.todo.eo.FileAttachment> associatedNotes(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<edu.stanford.ee.todo.eo.FileAttachment> results;
      results = associatedNotes();
      if (qualifier != null) {
        results = (NSArray<edu.stanford.ee.todo.eo.FileAttachment>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<edu.stanford.ee.todo.eo.FileAttachment>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToAssociatedNotes(edu.stanford.ee.todo.eo.FileAttachment object) {
    includeObjectIntoPropertyWithKey(object, "associatedNotes");
  }

  public void removeFromAssociatedNotes(edu.stanford.ee.todo.eo.FileAttachment object) {
    excludeObjectFromPropertyWithKey(object, "associatedNotes");
  }

  public void addToAssociatedNotesRelationship(edu.stanford.ee.todo.eo.FileAttachment object) {
    if (_Todo.LOG.isDebugEnabled()) {
      _Todo.LOG.debug("adding " + object + " to associatedNotes relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToAssociatedNotes(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, "associatedNotes");
    }
  }

  public void removeFromAssociatedNotesRelationship(edu.stanford.ee.todo.eo.FileAttachment object) {
    if (_Todo.LOG.isDebugEnabled()) {
      _Todo.LOG.debug("removing " + object + " from associatedNotes relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromAssociatedNotes(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, "associatedNotes");
    }
  }

  public edu.stanford.ee.todo.eo.FileAttachment createAssociatedNotesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("FileAttachment");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "associatedNotes");
    return (edu.stanford.ee.todo.eo.FileAttachment) eo;
  }

  public void deleteAssociatedNotesRelationship(edu.stanford.ee.todo.eo.FileAttachment object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "associatedNotes");
    editingContext().deleteObject(object);
  }

  public void deleteAllAssociatedNotesRelationships() {
    Enumeration objects = associatedNotes().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteAssociatedNotesRelationship((edu.stanford.ee.todo.eo.FileAttachment)objects.nextElement());
    }
  }


  public static Todo createTodo(EOEditingContext editingContext, String task
, edu.stanford.ee.todo.eo.UserAccount owner) {
    Todo eo = (Todo) EOUtilities.createAndInsertInstance(editingContext, _Todo.ENTITY_NAME);    
		eo.setTask(task);
    eo.setOwnerRelationship(owner);
    return eo;
  }

  public static NSArray<Todo> fetchAllTodos(EOEditingContext editingContext) {
    return _Todo.fetchAllTodos(editingContext, null);
  }

  public static NSArray<Todo> fetchAllTodos(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Todo.fetchTodos(editingContext, null, sortOrderings);
  }

  public static NSArray<Todo> fetchTodos(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Todo.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Todo> eoObjects = (NSArray<Todo>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Todo fetchTodo(EOEditingContext editingContext, String keyName, Object value) {
    return _Todo.fetchTodo(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Todo fetchTodo(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Todo> eoObjects = _Todo.fetchTodos(editingContext, qualifier, null);
    Todo eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Todo)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Todo that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Todo fetchRequiredTodo(EOEditingContext editingContext, String keyName, Object value) {
    return _Todo.fetchRequiredTodo(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Todo fetchRequiredTodo(EOEditingContext editingContext, EOQualifier qualifier) {
    Todo eoObject = _Todo.fetchTodo(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Todo that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Todo localInstanceIn(EOEditingContext editingContext, Todo eo) {
    Todo localInstance = (eo == null) ? null : (Todo)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
