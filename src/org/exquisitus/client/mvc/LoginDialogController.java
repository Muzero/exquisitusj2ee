package org.exquisitus.client.mvc;

/**
 * The Login Dialog Controller, perform login with LoginAction
 * 
 * @author muzero
 * 
 * */

import net.customware.gwt.dispatch.client.DispatchAsync;

import org.exquisitus.client.ApplicationEvents;
import org.exquisitus.client.ExquisitusJ2EE;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class LoginDialogController extends Controller {
	
	private DispatchAsync dispatchAsync = null;
	
	private LoginDialogView loginDialogView = null;

	public LoginDialogController() {
		loginDialogView = new LoginDialogView(this);
		
		registerEventTypes(ApplicationEvents.ShowLoginEvent);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		
		dispatchAsync = Registry.get(ExquisitusJ2EE.ACTIONDISPATCHER);
	}

	@Override
	public void handleEvent(AppEvent event) {
		
		if (event.getType() == ApplicationEvents.ShowLoginEvent)
			forwardToView(loginDialogView, event);

	}

}
