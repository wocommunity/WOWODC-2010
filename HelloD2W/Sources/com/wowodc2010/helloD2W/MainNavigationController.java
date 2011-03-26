package com.wowodc2010.helloD2W;

import webobjectsexamples.businesslogic.movies.common.Movie;
import webobjectsexamples.businesslogic.rentals.common.Customer;
import webobjectsexamples.businesslogic.rentals.common.User;
import webobjectsexamples.businesslogic.rentals.common.Video;

import com.webobjects.appserver.WOComponent;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.D2WPage;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.ErrorPageInterface;
import com.webobjects.directtoweb.InspectPageInterface;
import com.webobjects.directtoweb.ListPageInterface;
import com.webobjects.directtoweb.QueryPageInterface;
import com.webobjects.eoaccess.EODatabase;
import com.webobjects.eoaccess.EODatabaseContext;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.eoaccess.EOEntity;
import com.webobjects.eoaccess.EOModel;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOArrayDataSource;
import com.webobjects.eocontrol.EODetailDataSource;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOGlobalID;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.wowodc2010.helloD2W.delegates.RentMoviesDelegate;

import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXKey;
import er.extensions.eof.ERXQ;
import er.extensions.foundation.ERXArrayUtilities;

public class MainNavigationController {

	private Session _session;

	public MainNavigationController(Session s) {
		super();
		_session = s;
	}

	// NAV ACTIONS
	
	public WOComponent homeAction() {
        return D2W.factory().defaultPage(session());
    }
	
	public WOComponent listMovieAction() {
		ListPageInterface component = D2W.factory().listPageForEntityNamed(Movie.ENTITY_NAME, session());
		component.setDataSource(new EODatabaseDataSource(session().defaultEditingContext(), Movie.ENTITY_NAME));
		if(component instanceof D2WPage) {
			D2WPage page = (D2WPage)component;
			page.d2wContext().takeValueForKey("Movie", "navigationState");
		}
		return (WOComponent)component;
	}
	
	public WOComponent inspectTabCustomerAction() {
		InspectPageInterface component = (InspectPageInterface)D2W.factory().pageForConfigurationNamed("InspectTabCustomer", session());
		component.setObject((Customer)session().user().valueForKey(User.CustomerKey));
		if(component instanceof D2WPage) {
			D2WPage page = (D2WPage)component;
			page.d2wContext().takeValueForKey("Customer", "navigationState");
		}
		return (WOComponent)component;
	}
	
	public EditPageInterface createMovieAction() {
		EditPageInterface component = D2W.factory().editPageForNewObjectWithConfigurationNamed("CreateMovie", session());
		component.setNextPage(session().context().page());
		if(component instanceof D2WPage) {
			D2WPage page = (D2WPage)component;
			page.d2wContext().takeValueForKey("Movie.CreateMovie", "navigationState");
		}
		return component;
	}
	
	public ListPageInterface rentalHistoryAction() {
		ListPageInterface component = D2W.factory().listPageForEntityNamed("Rental", session());
		Customer c = (Customer)session().user().valueForKey("customer");
		EODetailDataSource dds = new EODetailDataSource(c.classDescription(), "rentals");
		dds.qualifyWithRelationshipKey("rentals", c);
		component.setDataSource(dds);
		//Force refresh
		clearSnapshotForRelationshipNamed(c, "rentals");
		component.setNextPage(session().context().page());
		if(component instanceof D2WPage) {
			D2WPage page = (D2WPage)component;
			page.d2wContext().takeValueForKey("Customer.RentalHistory", "navigationState");
		}
		return component;
	}
	
	public static void clearSnapshotForRelationshipNamed(EOEnterpriseObject eo, String relationshipName) {
		EOEditingContext ec = eo.editingContext();
		EOModel model = EOUtilities.entityForObject(ec, eo).model();
		EOGlobalID gid = ec.globalIDForObject(eo);
		EODatabaseContext dbc = EODatabaseContext.registeredDatabaseContextForModel(model, ec);
		EODatabase database = dbc.database();
		ERXEOControlUtilities.clearSnapshotForRelationshipNamedInDatabase(eo, relationshipName, database);
	}
	
	public ListPageInterface rentMoviesAction() {
		ListPageInterface component = (ListPageInterface)D2W.factory().pageForConfigurationNamed("SelectVideo", session());
		
		//Most of this probably belongs as model logic, but here simply for demonstration
		component.setNextPage(session().context().page());
		EOEditingContext ec = ERXEC.newEditingContext();
		EOEntity e = EOUtilities.entityNamed(ec, "Video");
		EOArrayDataSource ds = new EOArrayDataSource(e.classDescriptionForInstances(), ec);
		EOFetchSpecification fs = new EOFetchSpecification("Unit", null, null);
		fs.setPrefetchingRelationshipKeyPaths(new NSArray<String>("video"));
		NSArray units = ec.objectsWithFetchSpecification(fs);
		EOQualifier q = ERXQ.isTrue("isAvailableForRent");
		units = EOQualifier.filteredArrayWithQualifier(units, q);
		ERXKey<Video> videoKey = new ERXKey<Video>("video");
		NSArray<Video> videos = videoKey.arrayValueInObject(units);
		
		//Finally we can create our datasource
		videos = ERXArrayUtilities.distinct(videos);
		ds.setArray(videos);
		component.setDataSource(ds);
		if(component instanceof D2WPage) {
			D2WPage page = (D2WPage)component;
			page.d2wContext().takeValueForKey("Customer.RentMovies", "navigationState");
			//Setting a nextPageDelegate to handle video selection. This could also be
			//loaded via the rule system with the ERDDelayedObjectCreationAssignment
			page.setNextPageDelegate(new RentMoviesDelegate());
		}
		return component;
	}
		
	// GENERIC ACTIONS
	
    public WOComponent queryPageForEntityName(String entityName) {
        QueryPageInterface newQueryPage = D2W.factory().queryPageForEntityNamed(entityName, session());
        return (WOComponent) newQueryPage;
    }
    
    public WOComponent newObjectForEntityName(String entityName) {
        WOComponent nextPage = null;
        try {
            EditPageInterface epi = D2W.factory().editPageForNewObjectWithEntityNamed(entityName, session());
            epi.setNextPage(session().context().page());
            nextPage = (WOComponent) epi;
        } catch (IllegalArgumentException e) {
            ErrorPageInterface epf = D2W.factory().errorPage(session());
            epf.setMessage(e.toString());
            epf.setNextPage(session().context().page());
            nextPage = (WOComponent) epf;
        }
        return nextPage;
    }
    
    // ACCESSORS
    
    public Session session() {
		return _session;
	}

	public void setSession(Session s) {
		_session = s;
	}
}
