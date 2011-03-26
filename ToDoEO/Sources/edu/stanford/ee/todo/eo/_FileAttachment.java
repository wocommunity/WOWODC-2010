// $LastChangedRevision: 4733 $ DO NOT EDIT.  Make changes to FileAttachment.java instead.
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
public abstract class _FileAttachment extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "FileAttachment";

  // Attribute Keys
  public static final ERXKey<String> DESCRIPTION = new ERXKey<String>("description");
  // Relationship Keys
  public static final ERXKey<er.attachment.model.ERAttachment> ATTACHMENT = new ERXKey<er.attachment.model.ERAttachment>("attachment");
  public static final ERXKey<edu.stanford.ee.todo.eo.Todo> TODOS = new ERXKey<edu.stanford.ee.todo.eo.Todo>("todos");

  // Attributes
  public static final String DESCRIPTION_KEY = DESCRIPTION.key();
  // Relationships
  public static final String ATTACHMENT_KEY = ATTACHMENT.key();
  public static final String TODOS_KEY = TODOS.key();

  private static Logger LOG = Logger.getLogger(_FileAttachment.class);

  public FileAttachment localInstanceIn(EOEditingContext editingContext) {
    FileAttachment localInstance = (FileAttachment)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String description() {
    return (String) storedValueForKey("description");
  }

  public void setDescription(String value) {
    if (_FileAttachment.LOG.isDebugEnabled()) {
    	_FileAttachment.LOG.debug( "updating description from " + description() + " to " + value);
    }
    takeStoredValueForKey(value, "description");
  }

  public er.attachment.model.ERAttachment attachment() {
    return (er.attachment.model.ERAttachment)storedValueForKey("attachment");
  }
  
  public void setAttachment(er.attachment.model.ERAttachment value) {
    takeStoredValueForKey(value, "attachment");
  }

  public void setAttachmentRelationship(er.attachment.model.ERAttachment value) {
    if (_FileAttachment.LOG.isDebugEnabled()) {
      _FileAttachment.LOG.debug("updating attachment from " + attachment() + " to " + value);
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setAttachment(value);
    }
    else if (value == null) {
    	er.attachment.model.ERAttachment oldValue = attachment();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "attachment");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "attachment");
    }
  }
  
  public NSArray<edu.stanford.ee.todo.eo.Todo> todos() {
    return (NSArray<edu.stanford.ee.todo.eo.Todo>)storedValueForKey("todos");
  }

  public NSArray<edu.stanford.ee.todo.eo.Todo> todos(EOQualifier qualifier) {
    return todos(qualifier, null);
  }

  public NSArray<edu.stanford.ee.todo.eo.Todo> todos(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<edu.stanford.ee.todo.eo.Todo> results;
      results = todos();
      if (qualifier != null) {
        results = (NSArray<edu.stanford.ee.todo.eo.Todo>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<edu.stanford.ee.todo.eo.Todo>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
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
    if (_FileAttachment.LOG.isDebugEnabled()) {
      _FileAttachment.LOG.debug("adding " + object + " to todos relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToTodos(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, "todos");
    }
  }

  public void removeFromTodosRelationship(edu.stanford.ee.todo.eo.Todo object) {
    if (_FileAttachment.LOG.isDebugEnabled()) {
      _FileAttachment.LOG.debug("removing " + object + " from todos relationship");
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


  public static FileAttachment createFileAttachment(EOEditingContext editingContext, er.attachment.model.ERAttachment attachment) {
    FileAttachment eo = (FileAttachment) EOUtilities.createAndInsertInstance(editingContext, _FileAttachment.ENTITY_NAME);    
    eo.setAttachmentRelationship(attachment);
    return eo;
  }

  public static NSArray<FileAttachment> fetchAllFileAttachments(EOEditingContext editingContext) {
    return _FileAttachment.fetchAllFileAttachments(editingContext, null);
  }

  public static NSArray<FileAttachment> fetchAllFileAttachments(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _FileAttachment.fetchFileAttachments(editingContext, null, sortOrderings);
  }

  public static NSArray<FileAttachment> fetchFileAttachments(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_FileAttachment.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<FileAttachment> eoObjects = (NSArray<FileAttachment>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static FileAttachment fetchFileAttachment(EOEditingContext editingContext, String keyName, Object value) {
    return _FileAttachment.fetchFileAttachment(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static FileAttachment fetchFileAttachment(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<FileAttachment> eoObjects = _FileAttachment.fetchFileAttachments(editingContext, qualifier, null);
    FileAttachment eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (FileAttachment)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one FileAttachment that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static FileAttachment fetchRequiredFileAttachment(EOEditingContext editingContext, String keyName, Object value) {
    return _FileAttachment.fetchRequiredFileAttachment(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static FileAttachment fetchRequiredFileAttachment(EOEditingContext editingContext, EOQualifier qualifier) {
    FileAttachment eoObject = _FileAttachment.fetchFileAttachment(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no FileAttachment that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static FileAttachment localInstanceIn(EOEditingContext editingContext, FileAttachment eo) {
    FileAttachment localInstance = (eo == null) ? null : (FileAttachment)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
