package edu.stanford.ee.todohybrid;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import com.webobjects.foundation.NSLog;

public class KerberosAuth {

	static final String krbPath = "/Library/Preferences/edu.mit.Kerberos";
	public static final boolean KerberosAuthenticate (String userid, char[] password)
	{
		System.setProperty("java.security.krb5.conf", krbPath);
		System.setProperty("java.security.auth.login.config", KerberosAuth.class.getResource("/kerberos.conf").toExternalForm());
		try {
			LoginContext lc = new LoginContext("primaryLoginContext", new UserNamePasswordCallbackHandler(userid, password));
			lc.login();
			}
			catch (LoginException e) {
				// e.printStackTrace();
				return false; // Consider all failures as equal
		    }
		return true;

	}
	
	public static class UserNamePasswordCallbackHandler implements CallbackHandler {
	
		private String _userName;
		private char[] _password;
	
		public UserNamePasswordCallbackHandler(String userName, char[] password) {
			_userName = userName;
			_password = password;
		}
	
	    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
	    	for (Callback callback : callbacks) {
	    		if (callback instanceof NameCallback && _userName != null) {
	    			((NameCallback) callback).setName(_userName);
	    		}
	    		else if (callback instanceof PasswordCallback && _password != null) {
	    			((PasswordCallback) callback).setPassword(_password);
	    		}
	    	}
	    }
	}
	
}
