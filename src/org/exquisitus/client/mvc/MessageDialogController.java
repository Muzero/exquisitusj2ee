package org.exquisitus.client.mvc;

/**
 * Message Dialog Controller
 */

import org.exquisitus.client.ApplicationEvents;
import org.exquisitus.client.ExquisitusJ2EE;
import org.exquisitus.client.services.MessageAction;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.shared.SimpleResult;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MessageDialogController extends Controller {

	private DispatchAsync dispatchAsync = null;
	
	private MessageDialogView msgDialogView = null;
	
	public MessageDialogController() {
		msgDialogView = new MessageDialogView(this);

		registerEventTypes(ApplicationEvents.ShowMessageDialogEvent);
		registerEventTypes(ApplicationEvents.HideMessageDialogEvent);
	}
	
	@Override
	public void handleEvent(AppEvent event) {
		if (event.getType() == ApplicationEvents.ShowMessageDialogEvent)
			forwardToView(msgDialogView, event);
		if (event.getType() == ApplicationEvents.HideMessageDialogEvent)
			forwardToView(msgDialogView, event);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		
		dispatchAsync = Registry.get(ExquisitusJ2EE.ACTIONDISPATCHER);
		
		msgDialogView.getBtnSend().addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				
				String username = msgDialogView.getUserName().getValue();
				
				if (username == null) {
					LoginDialogView.dialogUtilShow("Error: Username is a required field.", "MESSAGE ERROR", true, true);
					return;	
				}
				
				String email = msgDialogView.getEmail().getValue();
				
				if (email == null) {
					LoginDialogView.dialogUtilShow("Error: email is a required field.", "MESSAGE ERROR", true, true);
					return;	
				}
				
				MessageAction msgAction = new MessageAction();
				msgAction.setUsername(username);
				msgAction.setEmail(email);
				msgAction.setType(msgDialogView.getMsgTypeCombo().getValue().get("name").toString());
				msgAction.setTitle(msgDialogView.getTxtTitle().getValue());
				msgAction.setMessage(msgDialogView.getMessageBody().getValue());
				msgAction.setHumor(msgDialogView.getHumor());
				
				dispatchAsync.execute(msgAction, new AsyncCallback<SimpleResult<String>>() {

					@Override
					public void onFailure(Throwable caught) {
						LoginDialogView.dialogUtilShow("Error... " + caught.getMessage() + "", "MESSAGE ERROR", true, true);
						
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(SimpleResult<String> result) {
						
						LoginDialogView.dialogUtilShow("Thanks! " + result.get() + " was sent. <br\\><br\\> If you inserted a valid email you'll get a quick response from developers :-)", "MESSAGE SENT", true, false);
						Dispatcher.forwardEvent(ApplicationEvents.HideMessageDialogEvent);
						
					}
					
				});
			}
			
		});
	}
}
