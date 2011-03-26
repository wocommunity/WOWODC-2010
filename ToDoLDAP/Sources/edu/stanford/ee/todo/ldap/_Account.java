// $LastChangedRevision: 4733 $ DO NOT EDIT.  Make changes to Account.java instead.
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
public abstract class _Account extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Account";

  // Attribute Keys
  public static final ERXKey<String> DESCRIPTION = new ERXKey<String>("description");
  public static final ERXKey<String> HOST = new ERXKey<String>("host");
  public static final ERXKey<String> L = new ERXKey<String>("l");
  public static final ERXKey<String> O = new ERXKey<String>("o");
  public static final ERXKey<String> OU = new ERXKey<String>("ou");
  public static final ERXKey<String> SEE_ALSO = new ERXKey<String>("seeAlso");
  public static final ERXKey<String> UID = new ERXKey<String>("uid");
  // Relationship Keys

  // Attributes
  public static final String DESCRIPTION_KEY = DESCRIPTION.key();
  public static final String HOST_KEY = HOST.key();
  public static final String L_KEY = L.key();
  public static final String O_KEY = O.key();
  public static final String OU_KEY = OU.key();
  public static final String SEE_ALSO_KEY = SEE_ALSO.key();
  public static final String UID_KEY = UID.key();
  // Relationships

  private static Logger LOG = Logger.getLogger(_Account.class);

  public Account localInstanceIn(EOEditingContext editingContext) {
    Account localInstance = (Account)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String description() {
    return (String) storedValueForKey("description");
  }

  public void setDescription(String value) {
    if (_Account.LOG.isDebugEnabled()) {
    	_Account.LOG.debug( "updating description from " + description() + " to " + value);
    }
    takeStoredValueForKey(value, "description");
  }

  public String host() {
    return (String) storedValueForKey("host");
  }

  public void setHost(String value) {
    if (_Account.LOG.isDebugEnabled()) {
    	_Account.LOG.debug( "updating host from " + host() + " to " + value);
    }
    takeStoredValueForKey(value, "host");
  }

  public String l() {
    return (String) storedValueForKey("l");
  }

  public void setL(String value) {
    if (_Account.LOG.isDebugEnabled()) {
    	_Account.LOG.debug( "updating l from " + l() + " to " + value);
    }
    takeStoredValueForKey(value, "l");
  }

  public String o() {
    return (String) storedValueForKey("o");
  }

  public void setO(String value) {
    if (_Account.LOG.isDebugEnabled()) {
    	_Account.LOG.debug( "updating o from " + o() + " to " + value);
    }
    takeStoredValueForKey(value, "o");
  }

  public String ou() {
    return (String) storedValueForKey("ou");
  }

  public void setOu(String value) {
    if (_Account.LOG.isDebugEnabled()) {
    	_Account.LOG.debug( "updating ou from " + ou() + " to " + value);
    }
    takeStoredValueForKey(value, "ou");
  }

  public String seeAlso() {
    return (String) storedValueForKey("seeAlso");
  }

  public void setSeeAlso(String value) {
    if (_Account.LOG.isDebugEnabled()) {
    	_Account.LOG.debug( "updating seeAlso from " + seeAlso() + " to " + value);
    }
    takeStoredValueForKey(value, "seeAlso");
  }

  public String uid() {
    return (String) storedValueForKey("uid");
  }

  public void setUid(String value) {
    if (_Account.LOG.isDebugEnabled()) {
    	_Account.LOG.debug( "updating uid from " + uid() + " to " + value);
    }
    takeStoredValueForKey(value, "uid");
  }


  public static Account createAccount(EOEditingContext editingContext, String uid
) {
    Account eo = (Account) EOUtilities.createAndInsertInstance(editingContext, _Account.ENTITY_NAME);    
		eo.setUid(uid);
    return eo;
  }

  public static NSArray<Account> fetchAllAccounts(EOEditingContext editingContext) {
    return _Account.fetchAllAccounts(editingContext, null);
  }

  public static NSArray<Account> fetchAllAccounts(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Account.fetchAccounts(editingContext, null, sortOrderings);
  }

  public static NSArray<Account> fetchAccounts(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Account.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Account> eoObjects = (NSArray<Account>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Account fetchAccount(EOEditingContext editingContext, String keyName, Object value) {
    return _Account.fetchAccount(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Account fetchAccount(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Account> eoObjects = _Account.fetchAccounts(editingContext, qualifier, null);
    Account eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Account)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Account that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Account fetchRequiredAccount(EOEditingContext editingContext, String keyName, Object value) {
    return _Account.fetchRequiredAccount(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Account fetchRequiredAccount(EOEditingContext editingContext, EOQualifier qualifier) {
    Account eoObject = _Account.fetchAccount(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Account that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Account localInstanceIn(EOEditingContext editingContext, Account eo) {
    Account localInstance = (eo == null) ? null : (Account)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
