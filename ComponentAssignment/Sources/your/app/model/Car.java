package your.app.model;

import org.apache.log4j.Logger;

import your.app.enums.Color;

import com.webobjects.foundation.NSArray;

public class Car extends _Car {
  private static Logger log = Logger.getLogger(Car.class);
  
  public NSArray<Color> availableColors() {
	  return new NSArray<Color>(Color.values());
  }
}
