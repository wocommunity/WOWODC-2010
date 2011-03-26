package com.wowodc2010.helloD2W.delegates;

import webobjectsexamples.businesslogic.rentals.common.Customer;
import webobjectsexamples.businesslogic.rentals.common.Rental;
import webobjectsexamples.businesslogic.rentals.common.Unit;
import webobjectsexamples.businesslogic.rentals.common.User;
import webobjectsexamples.businesslogic.rentals.common.Video;

import com.webobjects.appserver.WOComponent;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.wowodc2010.helloD2W.Session;

import er.directtoweb.delegates.ERDBranchDelegate;
import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXQ;

public class ConfirmRentalDelegate extends ERDBranchDelegate {

	private WOComponent nextPage;
	private Video video;
	
	public ConfirmRentalDelegate(WOComponent nextPage, Video video) {
		this.nextPage = nextPage;
		this.video = video;
	}
	
	public WOComponent cancel(WOComponent sender) {
		return nextPage;
	}
	
	public WOComponent confirm(WOComponent sender) {
		Rental rental = ERXEOControlUtilities.createAndInsertObject(video.editingContext(), Rental.class);
		EOEditingContext ec = rental.editingContext();
		rental.setDateOut(new NSTimestamp());
		Session session = (Session)sender.session();
		Customer customer = (Customer)session.user().valueForKey(User.CustomerKey);
		customer = ERXEOControlUtilities.localInstanceOfObject(ec, customer);
		rental.takeValueForKey(customer, Rental.CustomerKey);
		
		//More logic that should be in the model
		NSArray<Unit> units = (NSArray<Unit>)video.valueForKey(Video.UnitsKey);
		units = EOQualifier.filteredArrayWithQualifier(units, ERXQ.isTrue("isAvailableForRent"));
		
		rental.takeValueForKey(units.lastObject(), Rental.UnitKey);
		ec.lock();
		try {
			ec.saveChanges();
		} finally {
			ec.unlock();
		}
		return nextPage;
	}
	
}
