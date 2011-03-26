package your.app.assignments;

import com.webobjects.directtoweb.D2WContext;
import com.webobjects.eocontrol.EOKeyValueUnarchiver;
import com.webobjects.foundation.NSArray;

import er.directtoweb.assignments.ERDAssignment;

public class MyAssignment extends ERDAssignment {
	public MyAssignment(EOKeyValueUnarchiver u) {
		super(u);
	}
	public MyAssignment(String key, Object value) {
		super(key, value);
	}
	public static Object decodeWithKeyValueUnarchiver(EOKeyValueUnarchiver unarchiver)  {
        return new MyAssignment(unarchiver);
    }
	public NSArray<String> dependentKeys(String keyPath) {
		return NSArray.emptyArray();
	}
	public Object rhsKey(D2WContext c) {
		//TODO return the value to be assigned to keyPath()
		return null;
	}
}
