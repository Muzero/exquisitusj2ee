package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class ShowCaseController extends Controller {
	
	private ShowCaseView showCaseView = null;

	public ShowCaseController() {
		showCaseView = new ShowCaseView(this);
		
		registerEventTypes(ApplicationEvents.InitShowCaseEvent);
	}

	@Override
	public void handleEvent(AppEvent event) {
	
		if (event.getType() == ApplicationEvents.InitShowCaseEvent)
			forwardToView(showCaseView, event);
	}

}
