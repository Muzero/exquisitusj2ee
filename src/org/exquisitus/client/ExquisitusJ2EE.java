package org.exquisitus.client;


import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * First release of ExquisitusJ2EE , implementing mvc viewport root panel...
 */
public class ExquisitusJ2EE implements EntryPoint {
	
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	

		RootPanel.get().add(new Button("Hello J2EE Experiment!"));
	}
}
