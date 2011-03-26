package com.wowodc2010.helloD2W.delegates;

import webobjectsexamples.businesslogic.rentals.common.Rental;

import com.webobjects.appserver.WOComponent;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSTimestamp;

import er.directtoweb.delegates.ERDBranchDelegate;

public class ListRentalDelegate extends ERDBranchDelegate {
	public WOComponent returnVideo(WOComponent sender) {
		D2WContext c = d2wContext(sender);
		Rental r = (Rental)c.valueForKey("object");
		if(r.dateReturned() == null) {
			EOEditingContext ec = r.editingContext();
			r.setDateReturned(new NSTimestamp());
			try {
				ec.saveChanges();
			} catch(Exception e) {
				//I know... "Don't swallow" :) Exercise left to the reader.
			}
		}
		return null;
	}
}
