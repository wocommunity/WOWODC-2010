package your.app.assignments;

import com.webobjects.directtoweb.D2WContext;
import com.webobjects.eoaccess.EOAttribute;
import com.webobjects.eocontrol.EOKeyValueUnarchiver;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation._NSUtilities;

import er.directtoweb.assignments.ERDAssignment;

public class EnumAssignment extends ERDAssignment {

	public EnumAssignment(EOKeyValueUnarchiver u) {
		super(u);
	}

	public EnumAssignment(String key, Object value) {
		super(key, value);
	}
	
	public static Object decodeWithKeyValueUnarchiver(EOKeyValueUnarchiver unarchiver)  {
		return new EnumAssignment(unarchiver);
	}
	
	public NSArray<String> dependentKeys(String keyPath) {
		return new NSArray<String>("smartAttribute");
	}

	public Object enumChoices(D2WContext c) {
		EOAttribute attr = (EOAttribute)c.valueForKey("smartAttribute");
		if(attr != null) {
			Class<?> clazz = _NSUtilities.classWithName(attr.className());
			if(Enum.class.isAssignableFrom(clazz)) {
				return new NSArray<Object>(clazz.getEnumConstants());
			}
		}
		return NSArray.emptyArray();
	}
}
