package your.app.assignments;

import com.webobjects.directtoweb.D2WContext;
import com.webobjects.eocontrol.EOKeyValueUnarchiver;
import com.webobjects.foundation.NSArray;

import er.directtoweb.assignments.ERDComputingAssignmentInterface;
import er.directtoweb.assignments.delayed.ERDDelayedAssignment;

public class MyDelayedAssignment extends ERDDelayedAssignment 
		implements ERDComputingAssignmentInterface {
	public MyDelayedAssignment(EOKeyValueUnarchiver u) {
		super(u);
	}
	public MyDelayedAssignment(String key, Object value) {
		super(key, value);
	}
	public static Object decodeWithKeyValueUnarchiver(EOKeyValueUnarchiver unarchiver)  {
		return new MyDelayedAssignment(unarchiver);
	}
	public NSArray<String> dependentKeys(String keyPath) {
		return NSArray.emptyArray();
	}
	public Object fireNow(D2WContext c) {
		// TODO Return the value to be assigned to keyPath()
		return null;
	}
}
