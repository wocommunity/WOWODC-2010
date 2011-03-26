// $LastChangedRevision: 4733 $ DO NOT EDIT.  Make changes to Person.java instead.
package edu.stanford.ee.todo.ldap;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

import er.extensions.eof.*;
import er.extensions.foundation.*;

@SuppressWarnings("all")
public abstract class _Person extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Person";

  // Attribute Keys
  public static final ERXKey<String> CN = new ERXKey<String>("cn");
  public static final ERXKey<String> DESCRIPTION = new ERXKey<String>("description");
  public static final ERXKey<String> SEE_ALSO = new ERXKey<String>("seeAlso");
  public static final ERXKey<String> SN = new ERXKey<String>("sn");
  public static final ERXKey<String> TELEPHONE_NUMBER = new ERXKey<String>("telephoneNumber");
  public static final ERXKey<NSData> USER_PASSWORD = new ERXKey<NSData>("userPassword");
  // Relationship Keys

  // Attributes
  public static final String CN_KEY = CN.key();
  public static final String DESCRIPTION_KEY = DESCRIPTION.key();
  public static final String SEE_ALSO_KEY = SEE_ALSO.key();
  public static final String SN_KEY = SN.key();
  public static final String TELEPHONE_NUMBER_KEY = TELEPHONE_NUMBER.key();
  public static final String USER_PASSWORD_KEY = USER_PASSWORD.key();
  // Relationships

  private static Logger LOG = Logger.getLogger(_Person.class);

  public Person localInstanceIn(EOEditingContext editingContext) {
    Person localInstance = (Person)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String cn() {
    return (String) storedValueForKey("cn");
  }

  public void setCn(String value) {
    if (_Person.LOG.isDebugEnabled()) {
    	_Person.LOG.debug( "updating cn from " + cn() + " to " + value);
    }
    takeStoredValueForKey(value, "cn");
  }

  public String description() {
    return (String) storedValueForKey("description");
  }

  public void setDescription(String value) {
    if (_Person.LOG.isDebugEnabled()) {
    	_Person.LOG.debug( "updating description from " + description() + " to " + value);
    }
    takeStoredValueForKey(value, "description");
  }

  public String seeAlso() {
    return (String) storedValueForKey("seeAlso");
  }

  public void setSeeAlso(String value) {
    if (_Person.LOG.isDebugEnabled()) {
    	_Person.LOG.debug( "updating seeAlso from " + seeAlso() + " to " + value);
    }
    takeStoredValueForKey(value, "seeAlso");
  }

  public String sn() {
    return (String) storedValueForKey("sn");
  }

  public void setSn(String value) {
    if (_Person.LOG.isDebugEnabled()) {
    	_Person.LOG.debug( "updating sn from " + sn() + " to " + value);
    }
    takeStoredValueForKey(value, "sn");
  }

  public String telephoneNumber() {
    return (String) storedValueForKey("telephoneNumber");
  }

  public void setTelephoneNumber(String value) {
    if (_Person.LOG.isDebugEnabled()) {
    	_Person.LOG.debug( "updating telephoneNumber from " + telephoneNumber() + " to " + value);
    }
    takeStoredValueForKey(value, "telephoneNumber");
  }

  public NSData userPassword() {
    return (NSData) storedValueForKey("userPassword");
  }

  public void setUserPassword(NSData value) {
    if (_Person.LOG.isDebugEnabled()) {
    	_Person.LOG.debug( "updating userPassword from " + userPassword() + " to " + value);
    }
    takeStoredValueForKey(value, "userPassword");
  }


  public static Person createPerson(EOEditingContext editingContext, String cn
, String sn
) {
    Person eo = (Person) EOUtilities.createAndInsertInstance(editingContext, _Person.ENTITY_NAME);    
		eo.setCn(cn);
		eo.setSn(sn);
    return eo;
  }

  public static NSArray<Person> fetchAllPersons(EOEditingContext editingContext) {
    return _Person.fetchAllPersons(editingContext, null);
  }

  public static NSArray<Person> fetchAllPersons(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Person.fetchPersons(editingContext, null, sortOrderings);
  }

  public static NSArray<Person> fetchPersons(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Person.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Person> eoObjects = (NSArray<Person>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Person fetchPerson(EOEditingContext editingContext, String keyName, Object value) {
    return _Person.fetchPerson(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Person fetchPerson(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Person> eoObjects = _Person.fetchPersons(editingContext, qualifier, null);
    Person eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Person)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Person that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Person fetchRequiredPerson(EOEditingContext editingContext, String keyName, Object value) {
    return _Person.fetchRequiredPerson(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Person fetchRequiredPerson(EOEditingContext editingContext, EOQualifier qualifier) {
    Person eoObject = _Person.fetchPerson(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Person that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Person localInstanceIn(EOEditingContext editingContext, Person eo) {
    Person localInstance = (eo == null) ? null : (Person)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
