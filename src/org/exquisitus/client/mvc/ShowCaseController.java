package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;

public class ShowCaseController extends Controller {
	
	private ShowCaseView showCaseView = null;
	// private LoadDialogView = null; TODO
	
	// services to implement
	// Loader of showcase
	// loader of ContentPanels view
	// loader of source code!!!
	
	public ShowCaseController() {
		showCaseView = new ShowCaseView(this);
		
		registerEventTypes(ApplicationEvents.InitShowCaseEvent);
		registerEventTypes(ApplicationEvents.SelectSubViewEvent);
	}

	@Override
	public void handleEvent(AppEvent event) {
	
		if (event.getType() == ApplicationEvents.InitShowCaseEvent)
			forwardToView(showCaseView, event);
	
		if (event.getType() == ApplicationEvents.SelectSubViewEvent)
			forwardToView(showCaseView, event);
	}

}
