// $LastChangedRevision: 4733 $ DO NOT EDIT.  Make changes to PosixAccount.java instead.
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
public abstract class _PosixAccount extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "PosixAccount";

  // Attribute Keys
  public static final ERXKey<String> CN = new ERXKey<String>("cn");
  public static final ERXKey<String> DESCRIPTION = new ERXKey<String>("description");
  public static final ERXKey<String> GECOS = new ERXKey<String>("gecos");
  public static final ERXKey<Integer> GID_NUMBER = new ERXKey<Integer>("gidNumber");
  public static final ERXKey<String> HOME_DIRECTORY = new ERXKey<String>("homeDirectory");
  public static final ERXKey<String> LOGIN_SHELL = new ERXKey<String>("loginShell");
  public static final ERXKey<String> UID = new ERXKey<String>("uid");
  public static final ERXKey<Integer> UID_NUMBER = new ERXKey<Integer>("uidNumber");
  public static final ERXKey<NSData> USER_PASSWORD = new ERXKey<NSData>("userPassword");
  // Relationship Keys

  // Attributes
  public static final String CN_KEY = CN.key();
  public static final String DESCRIPTION_KEY = DESCRIPTION.key();
  public static final String GECOS_KEY = GECOS.key();
  public static final String GID_NUMBER_KEY = GID_NUMBER.key();
  public static final String HOME_DIRECTORY_KEY = HOME_DIRECTORY.key();
  public static final String LOGIN_SHELL_KEY = LOGIN_SHELL.key();
  public static final String UID_KEY = UID.key();
  public static final String UID_NUMBER_KEY = UID_NUMBER.key();
  public static final String USER_PASSWORD_KEY = USER_PASSWORD.key();
  // Relationships

  private static Logger LOG = Logger.getLogger(_PosixAccount.class);

  public PosixAccount localInstanceIn(EOEditingContext editingContext) {
    PosixAccount localInstance = (PosixAccount)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String cn() {
    return (String) storedValueForKey("cn");
  }

  public void setCn(String value) {
    if (_PosixAccount.LOG.isDebugEnabled()) {
    	_PosixAccount.LOG.debug( "updating cn from " + cn() + " to " + value);
    }
    takeStoredValueForKey(value, "cn");
  }

  public String description() {
    return (String) storedValueForKey("description");
  }

  public void setDescription(String value) {
    if (_PosixAccount.LOG.isDebugEnabled()) {
    	_PosixAccount.LOG.debug( "updating description from " + description() + " to " + value);
    }
    takeStoredValueForKey(value, "description");
  }

  public String gecos() {
    return (String) storedValueForKey("gecos");
  }

  public void setGecos(String value) {
    if (_PosixAccount.LOG.isDebugEnabled()) {
    	_PosixAccount.LOG.debug( "updating gecos from " + gecos() + " to " + value);
    }
    takeStoredValueForKey(value, "gecos");
  }

  public Integer gidNumber() {
    return (Integer) storedValueForKey("gidNumber");
  }

  public void setGidNumber(Integer value) {
    if (_PosixAccount.LOG.isDebugEnabled()) {
    	_PosixAccount.LOG.debug( "updating gidNumber from " + gidNumber() + " to " + value);
    }
    takeStoredValueForKey(value, "gidNumber");
  }

  public String homeDirectory() {
    return (String) storedValueForKey("homeDirectory");
  }

  public void setHomeDirectory(String value) {
    if (_PosixAccount.LOG.isDebugEnabled()) {
    	_PosixAccount.LOG.debug( "updating homeDirectory from " + homeDirectory() + " to " + value);
    }
    takeStoredValueForKey(value, "homeDirectory");
  }

  public String loginShell() {
    return (String) storedValueForKey("loginShell");
  }

  public void setLoginShell(String value) {
    if (_PosixAccount.LOG.isDebugEnabled()) {
    	_PosixAccount.LOG.debug( "updating loginShell from " + loginShell() + " to " + value);
    }
    takeStoredValueForKey(value, "loginShell");
  }

  public String uid() {
    return (String) storedValueForKey("uid");
  }

  public void setUid(String value) {
    if (_PosixAccount.LOG.isDebugEnabled()) {
    	_PosixAccount.LOG.debug( "updating uid from " + uid() + " to " + value);
    }
    takeStoredValueForKey(value, "uid");
  }

  public Integer uidNumber() {
    return (Integer) storedValueForKey("uidNumber");
  }

  public void setUidNumber(Integer value) {
    if (_PosixAccount.LOG.isDebugEnabled()) {
    	_PosixAccount.LOG.debug( "updating uidNumber from " + uidNumber() + " to " + value);
    }
    takeStoredValueForKey(value, "uidNumber");
  }

  public NSData userPassword() {
    return (NSData) storedValueForKey("userPassword");
  }

  public void setUserPassword(NSData value) {
    if (_PosixAccount.LOG.isDebugEnabled()) {
    	_PosixAccount.LOG.debug( "updating userPassword from " + userPassword() + " to " + value);
    }
    takeStoredValueForKey(value, "userPassword");
  }


  public static PosixAccount createPosixAccount(EOEditingContext editingContext, String cn
, Integer gidNumber
, String homeDirectory
, String uid
, Integer uidNumber
) {
    PosixAccount eo = (PosixAccount) EOUtilities.createAndInsertInstance(editingContext, _PosixAccount.ENTITY_NAME);    
		eo.setCn(cn);
		eo.setGidNumber(gidNumber);
		eo.setHomeDirectory(homeDirectory);
		eo.setUid(uid);
		eo.setUidNumber(uidNumber);
    return eo;
  }

  public static NSArray<PosixAccount> fetchAllPosixAccounts(EOEditingContext editingContext) {
    return _PosixAccount.fetchAllPosixAccounts(editingContext, null);
  }

  public static NSArray<PosixAccount> fetchAllPosixAccounts(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _PosixAccount.fetchPosixAccounts(editingContext, null, sortOrderings);
  }

  public static NSArray<PosixAccount> fetchPosixAccounts(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_PosixAccount.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<PosixAccount> eoObjects = (NSArray<PosixAccount>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static PosixAccount fetchPosixAccount(EOEditingContext editingContext, String keyName, Object value) {
    return _PosixAccount.fetchPosixAccount(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static PosixAccount fetchPosixAccount(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<PosixAccount> eoObjects = _PosixAccount.fetchPosixAccounts(editingContext, qualifier, null);
    PosixAccount eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (PosixAccount)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one PosixAccount that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static PosixAccount fetchRequiredPosixAccount(EOEditingContext editingContext, String keyName, Object value) {
    return _PosixAccount.fetchRequiredPosixAccount(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static PosixAccount fetchRequiredPosixAccount(EOEditingContext editingContext, EOQualifier qualifier) {
    PosixAccount eoObject = _PosixAccount.fetchPosixAccount(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no PosixAccount that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static PosixAccount localInstanceIn(EOEditingContext editingContext, PosixAccount eo) {
    PosixAccount localInstance = (eo == null) ? null : (PosixAccount)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
