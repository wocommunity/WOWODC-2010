package edu.stanford.ee.todobasic.app;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WORequest;
import com.webobjects.directtoweb.D2W;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSForwardException;
import com.webobjects.foundation.NSLog;

import er.directtoweb.ERD2WDirectAction;
import er.extensions.eof.ERXEC;
import er.extensions.foundation.ERXStringUtilities;
import edu.stanford.ee.todo.eo.UserAccount;
import edu.stanford.ee.todobasic.ui.components.Main;


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
        
        WOComponent nextPage = null;
        String username = request().stringFormValueForKey("username");
        String password = request().stringFormValueForKey("password");;
        String errorMessage = null;
        EOQualifier qual = null;
        
        if (isEmpty(username) || isEmpty(password)) {
                errorMessage = "Please enter a username and password.";
        } else {
                try {
                		if (KerberosAuth.KerberosAuthenticate(username, password.toCharArray()))
                			{
                			qual = UserAccount.USERNAME.eq(username);
                			NSLog.out.appendln("Kerberos authenticated: " + username);
                			}
                		else if (LDAPAuth.LDAPAuthenticate(username, password))
                		{
                			qual = UserAccount.USERNAME.eq(username);
                			NSLog.out.appendln("LDAP authenticated: " + username);
                		}
                		else qual = UserAccount.USERNAME.eq(username).and(UserAccount.PASSWORD.eq(digestedString(password))); // use with db-based password
                		
                		UserAccount user = UserAccount.fetchRequiredUserAccount(ERXEC.newEditingContext(), qual);
                        ((Session)session()).setCurrentUser(user);
                        if (((Session)session()).currentUser() != null)
                        {
                               nextPage = D2W.factory().defaultPage(session());
                        }
                        
                } catch (NoSuchElementException e) {
                        errorMessage = "No User found for those credentials";
                } catch (Exception e) {
                        // Something bad happened
                		NSLog.err.appendln(e);
                		errorMessage = "Unknown error has occurred";
                }
        }
        
        if (notEmpty(errorMessage)) {
                nextPage = pageWithName(Main.class.getName());
                nextPage.takeValueForKey(errorMessage, "errorMessage");
                nextPage.takeValueForKey(username, "username");
                nextPage.takeValueForKey(password, "password");
        }
        
        return nextPage;
    }
    
    public boolean isEmpty(String s) {
        return ERXStringUtilities.stringIsNullOrEmpty(s);
}

    public boolean notEmpty(String s) {
        return ! ERXStringUtilities.stringIsNullOrEmpty(s);
}

    public String digestedString(String aString) {
	  String digestedString;
	  
	  try {
		  MessageDigest md = MessageDigest.getInstance("SHA-256");
		  md.reset();
		  digestedString = new sun.misc.BASE64Encoder().encode (md.digest(aString.getBytes("UTF-8")));
	  }
	  catch (NoSuchAlgorithmException  e) {
		  throw new NSForwardException(e);
	  }
	  catch (UnsupportedEncodingException e){
		  throw new NSForwardException(e);
	  }
	  return digestedString;
}


	
}
