// $LastChangedRevision: 5810 $ DO NOT EDIT.  Make changes to Car.java instead.
package your.app.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _Car extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Car";

	// Attributes
	public static final String COLOR_KEY = "color";
	public static final String MAKE_KEY = "make";

	// Relationships

  private static Logger LOG = Logger.getLogger(_Car.class);

  public Car localInstanceIn(EOEditingContext editingContext) {
    Car localInstance = (Car)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public your.app.enums.Color color() {
    return (your.app.enums.Color) storedValueForKey("color");
  }

  public void setColor(your.app.enums.Color value) {
    if (_Car.LOG.isDebugEnabled()) {
    	_Car.LOG.debug( "updating color from " + color() + " to " + value);
    }
    takeStoredValueForKey(value, "color");
  }

  public String make() {
    return (String) storedValueForKey("make");
  }

  public void setMake(String value) {
    if (_Car.LOG.isDebugEnabled()) {
    	_Car.LOG.debug( "updating make from " + make() + " to " + value);
    }
    takeStoredValueForKey(value, "make");
  }


  public static Car createCar(EOEditingContext editingContext, your.app.enums.Color color
, String make
) {
    Car eo = (Car) EOUtilities.createAndInsertInstance(editingContext, _Car.ENTITY_NAME);    
		eo.setColor(color);
		eo.setMake(make);
    return eo;
  }

  public static NSArray<Car> fetchAllCars(EOEditingContext editingContext) {
    return _Car.fetchAllCars(editingContext, null);
  }

  public static NSArray<Car> fetchAllCars(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Car.fetchCars(editingContext, null, sortOrderings);
  }

  public static NSArray<Car> fetchCars(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Car.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Car> eoObjects = (NSArray<Car>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Car fetchCar(EOEditingContext editingContext, String keyName, Object value) {
    return _Car.fetchCar(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Car fetchCar(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Car> eoObjects = _Car.fetchCars(editingContext, qualifier, null);
    Car eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Car)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Car that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Car fetchRequiredCar(EOEditingContext editingContext, String keyName, Object value) {
    return _Car.fetchRequiredCar(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Car fetchRequiredCar(EOEditingContext editingContext, EOQualifier qualifier) {
    Car eoObject = _Car.fetchCar(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Car that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Car localInstanceIn(EOEditingContext editingContext, Car eo) {
    Car localInstance = (eo == null) ? null : (Car)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
