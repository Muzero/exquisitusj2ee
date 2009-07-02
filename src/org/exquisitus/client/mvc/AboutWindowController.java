package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class AboutWindowController extends Controller {

	private AboutWindowView aboutWindowView = null;

	public AboutWindowController() {
		aboutWindowView = new AboutWindowView(this);
		
		registerEventTypes(ApplicationEvents.ShowAboutWindowEvent);
	}

	@Override
	public void handleEvent(AppEvent event) {

		if (event.getType() == ApplicationEvents.ShowAboutWindowEvent)
			forwardToView(aboutWindowView, event);
	}

}
