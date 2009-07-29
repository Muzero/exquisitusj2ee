package org.exquisitus.client.mvc;

/**
 * The Main application Controller 
 * 
 * @author muzero
 * 
 * */

import org.exquisitus.client.ApplicationEvents;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class AppController extends Controller {
		
	private AppView app = null;
	
	public AppController() {
		app = new AppView(this);
	
		registerEventTypes(ApplicationEvents.InitAppMenu);		
	}
	
	@Override
	protected void initialize() {
		super.initialize();	
	}
	
	@Override
	public void handleEvent(AppEvent event) {
		if (event.getType() == ApplicationEvents.InitAppMenu)
			forwardToView(app, event);
		
	}
}
