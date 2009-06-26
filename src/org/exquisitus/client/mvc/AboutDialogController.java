package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class AboutDialogController extends Controller {

	private AboutDialogView aboutDialogView = null;

	public AboutDialogController() {
		aboutDialogView = new AboutDialogView(this);
		
		registerEventTypes(ApplicationEvents.ShowAboutDialogEvent);
	}

	@Override
	public void handleEvent(AppEvent event) {

		if (event.getType() == ApplicationEvents.ShowAboutDialogEvent)
			aboutDialogView.showDialog();
	}

}
