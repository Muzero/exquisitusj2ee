package org.exquisitus.client.mvc;

/**
 * Message Dialog Controller
 */

import org.exquisitus.client.ApplicationEvents;
import org.exquisitus.client.ExquisitusJ2EE;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class MessageDialogController extends Controller {

	private DispatchAsync dispatchAsync = null;
	
	private MessageDialogView msgDialogView = null;
	
	public MessageDialogController() {
		msgDialogView = new MessageDialogView(this);

		registerEventTypes(ApplicationEvents.ShowMessageDialogEvent);
	}
	
	@Override
	public void handleEvent(AppEvent event) {
		if (event.getType() == ApplicationEvents.ShowMessageDialogEvent)
			forwardToView(msgDialogView, event);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		
		dispatchAsync = Registry.get(ExquisitusJ2EE.ACTIONDISPATCHER);
	}

}
