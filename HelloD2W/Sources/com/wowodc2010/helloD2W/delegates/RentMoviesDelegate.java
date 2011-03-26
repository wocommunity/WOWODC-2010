package com.wowodc2010.helloD2W.delegates;

import webobjectsexamples.businesslogic.rentals.common.Video;

import com.webobjects.appserver.WOComponent;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.D2WPage;
import com.webobjects.directtoweb.ERD2WUtilities;
import com.webobjects.directtoweb.NextPageDelegate;
import com.webobjects.directtoweb.SelectPageInterface;

import er.directtoweb.interfaces.ERDMessagePageInterface;

public class RentMoviesDelegate implements NextPageDelegate {

	public WOComponent nextPage(WOComponent sender) {
		SelectPageInterface selectPage = sender instanceof SelectPageInterface?
				(SelectPageInterface)sender:ERD2WUtilities.parentSelectPage(sender);
		Video selection = (Video)selectPage.selectedObject();
		if(selection != null) {
			D2WPage d2wPage = (D2WPage)selectPage;
			//Message* produces a message page. *Customer produces a Customer navigation state.
			ERDMessagePageInterface page = (ERDMessagePageInterface)D2W.factory().pageForConfigurationNamed("MessageCustomer", sender.session());
			page.setMessage("Rent " + selection.valueForKeyPath("movie.title").toString() + "?");
			//Setting a branch delegate to handle branch choices on the confirm page.
			page.setNextPageDelegate(new ConfirmRentalDelegate(d2wPage, selection));
			return (WOComponent)page;
		}
		return null;
	}

}
