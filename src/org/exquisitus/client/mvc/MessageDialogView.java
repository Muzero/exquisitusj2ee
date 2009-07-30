package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Dialog;

public class MessageDialogView extends View {
	
	private static final int YWINLOGIN = 480;
	private static final int XWINLOGIN = 640;

	private Dialog msgDialog = null;

	public MessageDialogView(Controller controller) {
		super(controller);
		
	}
	
	@Override
	protected void initialize() {
		super.initialize();
	
		msgDialog = new Dialog();
		msgDialog.setBodyBorder(true);
		msgDialog.setHeading("Exquisitus J2EE Message Dialog");
		msgDialog.setWidth(XWINLOGIN);
		msgDialog.setHeight(YWINLOGIN);
		msgDialog.setButtons(Dialog.CANCEL);
		msgDialog.setHideOnButtonClick(true);
		msgDialog.setShadow(true);
		msgDialog.setResizable(false);
		
		msgDialog.hide();
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == ApplicationEvents.ShowMessageDialogEvent) {
			msgDialog.show();
		}
	}

}
