package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;
import org.exquisitus.client.GreetingService;
import org.exquisitus.client.GreetingServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.google.gwt.core.client.GWT;

public class AppController extends Controller {
	
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	private AppView app = null;
	
	public AppController() {
		app = new AppView(this);
	
		registerEventTypes(ApplicationEvents.InitAppMenu);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
	
		Registry.register("GREET", greetingService);
	}
	
	@Override
	public void handleEvent(AppEvent event) {
		if (event.getType() == ApplicationEvents.InitAppMenu)
			forwardToView(app, event);
	}

}
