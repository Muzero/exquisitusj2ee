package org.exquisitus.client;

/** 1 Jun 2009 London Gianluigi Davassi (Muzero)
 * 
 * First snapshot of ExquisitusJ2EE , implementing mvc viewport root panel...
 * 
 * ExquisitusJ2EE is a collection of J2EE examples depicted in a eye-candy showcase developed with Ext-Gwt library.
 * The aim of this web application is to show examples and source code of some J2EE examples and share knowlegdge. 
 *
 */

import org.exquisitus.client.mvc.AppController;
import org.exquisitus.client.mvc.LoginDialogController;
import org.exquisitus.client.mvc.ShowCaseController;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Theme;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;

public class ExquisitusJ2EE implements EntryPoint {

	private Dispatcher dispatcher = null;

	/* This is the entry point method. */
	public void onModuleLoad() {
		 
		try {
		      GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
		        public void onUncaughtException(Throwable e) {
		          e.printStackTrace();
		        }
		      });
		
		GXT.setDefaultTheme(Theme.BLUE, true);
		
		dispatcher = Dispatcher.get();
		dispatcher.addController(new AppController());
		dispatcher.addController(new LoginDialogController()); 
		dispatcher.addController(new ShowCaseController()); 
		//dispatcher.addController(new MessageDialogController()); TODO
		//dispatcher.addController(new AboutDialogController()); TODO
		
		
		dispatcher.dispatch(ApplicationEvents.InitAppMenu);
		
		GWT.log(GXT.getUserAgent(),null);
	
		} catch (Exception e) { e.printStackTrace(); }	
	}
}

