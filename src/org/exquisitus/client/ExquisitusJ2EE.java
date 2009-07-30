package org.exquisitus.client;

/** 1 Jun 2009 London Gianluigi Davassi 
 * 
 * First snapshot of ExquisitusJ2EE , implementing mvc viewport root panel...
 * 
 * ExquisitusJ2EE is a collection of J2EE examples depicted in a eye-candy showcase developed with Ext-Gwt library.
 * The aim of this web application is to show examples and source code of some J2EE examples and share knowlegdge. 
 *
 * @author muzero
 */

import net.customware.gwt.dispatch.client.DefaultDispatchAsync;

import org.exquisitus.client.mvc.AboutWindowController;
import org.exquisitus.client.mvc.AppController;
import org.exquisitus.client.mvc.LoginDialogController;
import org.exquisitus.client.mvc.MessageDialogController;
import org.exquisitus.client.mvc.ShowCaseController;
import org.exquisitus.client.icons.ExquisitusIconBundle;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Theme;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;

public class ExquisitusJ2EE implements EntryPoint {
	
	public static final String ACTIONDISPATCHER = "ACTIONDISPATCHER";

	public static ExquisitusIconBundle EXQUISITUS_ICON_BUNDLE = GWT.create(ExquisitusIconBundle.class);

	private Dispatcher dispatcher = null;
	
	/* This is the entry point method. */
	public void onModuleLoad() {
			 
		/*try {
		      GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
		        public void onUncaughtException(Throwable e) {
		          e.printStackTrace();
		        }
		      });
		*/
		GXT.setDefaultTheme(Theme.BLUE, true);
		
		// register the action dispatcher
		Registry.register(ExquisitusJ2EE.ACTIONDISPATCHER, new DefaultDispatchAsync());
		
		// configure the mvc dispatcher 
		dispatcher = Dispatcher.get();
		dispatcher.addController(new AppController());
		dispatcher.addController(new LoginDialogController()); 
		dispatcher.addController(new ShowCaseController()); 
		dispatcher.addController(new AboutWindowController()); 
		dispatcher.addController(new MessageDialogController()); 
		
		dispatcher.dispatch(ApplicationEvents.InitAppMenu);
		
		// show the 'splash screen?
		Dispatcher.forwardEvent(ApplicationEvents.ShowAboutWindowEvent);
		
		GWT.log(this.getClass().getName() + " started at " + GXT.getUserAgent() + " on " + GXT.getVersion().getRelease(), null);
		
		//} catch (Exception e) { e.printStackTrace(); }	
	}
}

