package org.exquisitus.client.mvc;

/**
 * The Login Dialog Controller
 * 
 * @author muzero
 * 
 * */

import org.exquisitus.client.ApplicationEvents;
import org.exquisitus.client.services.LoginService;
import org.exquisitus.client.services.LoginServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.google.gwt.core.client.GWT;

public class LoginDialogController extends Controller {
	
	public static final String LOGINMOCKSERVICE = "LOGIN";
	
	private final LoginServiceAsync loginService = (LoginServiceAsync) GWT.create(LoginService.class);
	
	private LoginDialogView loginDialogView = null;

	public LoginDialogController() {
		loginDialogView = new LoginDialogView(this);
		
		registerEventTypes(ApplicationEvents.ShowLoginEvent);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		
		Registry.register(LOGINMOCKSERVICE, loginService);
	}

	@Override
	public void handleEvent(AppEvent event) {
		
		if (event.getType() == ApplicationEvents.ShowLoginEvent)
			forwardToView(loginDialogView, event);

	}

}
