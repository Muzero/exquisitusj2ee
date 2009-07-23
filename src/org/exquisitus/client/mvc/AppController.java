package org.exquisitus.client.mvc;

/**
 * The Main application Controller 
 * 
 * @author muzero
 * 
 * */

import org.exquisitus.client.ApplicationEvents;
import org.exquisitus.client.services.GreetingService;
import org.exquisitus.client.services.GreetingServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.google.gwt.core.client.GWT;

public class AppController extends Controller {
	
	public static final String GREETMOCKSERVICE = "GREET";
	
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	private AppView app = null;
	
	public AppController() {
		app = new AppView(this);
	
		registerEventTypes(ApplicationEvents.InitAppMenu);		
	}
	
	@Override
	protected void initialize() {
		super.initialize();
	
		Registry.register(AppController.GREETMOCKSERVICE, greetingService);
	}
	
	@Override
	public void handleEvent(AppEvent event) {
		if (event.getType() == ApplicationEvents.InitAppMenu)
			forwardToView(app, event);
		
	}
	
	


}
