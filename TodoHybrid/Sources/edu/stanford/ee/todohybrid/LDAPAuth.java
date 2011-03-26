package edu.stanford.ee.todohybrid;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import com.webobjects.foundation.NSLog;





public class LDAPAuth {
	public static final boolean LDAPAuthenticate (String userid, String password)
	{
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://172.16.113.129:389/dc=example,dc=com");

		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=" + userid + ", ou=People, dc=example, dc=com");
		env.put(Context.SECURITY_CREDENTIALS, password);

		// Create the initial context
		try {
			DirContext ctx = new InitialDirContext(env);
		} catch (NamingException e) {
			return false; // Failed to auth
			//e.printStackTrace();
		}
		
		return true;

	}
}
