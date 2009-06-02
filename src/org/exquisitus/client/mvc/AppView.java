package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;
import org.exquisitus.client.GreetingService;
import org.exquisitus.client.GreetingServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/*
 * “If builders built buildings the way programmers write programs, 
 * then the first woodpecker that came along would destroy civilization.”
 * Weinberg’s Second Law (born: 1933 age: 76)
 * */

public class AppView extends View {
	
	private Button btn = new Button();

	public AppView(Controller controller) {
		super(controller);
	}

	@Override
	protected void handleEvent(AppEvent event) {
	
		if (event.getType() == ApplicationEvents.InitAppMenu)
			createView();
		
	}
	
	@Override
	protected void initialize() {
		btn = new Button("Hello J2EE Experiment!");
	
		btn.addSelectionListener(new SelectionListener<ButtonEvent>(){

			@Override
			public void componentSelected(ButtonEvent ce) {
				GreetingServiceAsync gs = (GreetingServiceAsync)Registry.get("GREET");
				gs.greetServer(RootPanel.get().getTitle(), new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("RPC Call error", null);
					}

					@Override
					public void onSuccess(String result) {
						
						GWT.log("SUCCESS! " + result, null);
					}
					
				});
				
			}
			
		});
	}

	protected void createView() {
		Viewport vp = new Viewport();
		vp.setAutoHeight(true);
		vp.setAutoWidth(true);
		ContentPanel cp = new ContentPanel();
		cp.setWidth(400);
		cp.setHeight(400);
		
		/*cp.sinkEvents(Event.ONMOUSEOVER);
		
		cp.addListener(new EventType(Event.ONMOUSEOVER), new Listener<ComponentEvent>() {

			@Override
			public void handleEvent(ComponentEvent be) {
				GWT.log(be.getXY().x + " " + be.getXY().y, null);
				
			}
			
		});*/
		
		cp.add(btn);
		vp.add(cp);
		RootPanel.get().add(vp);
		GWT.log("bel messaggio del.. cavolo", null);
	}

}
