
package org.exquisitus.client;

/**
 * ApplicationEvent class store the Event bill of the ExquisitusJ2EE MVC implementation
 * 
 * @author muzero
 */

import java.util.Map;

import com.extjs.gxt.ui.client.event.EventType;

public final class ApplicationEvents {

	// disallow Constructor
	private ApplicationEvents() {}

	enum EnumEvents {  // TODO put the event bill into the eventmap and create EnumEvent with Guice
		INITAPPMENU, 
		ANOTHEREVENT,
		SHOWLOGINEVENT // ...
	} ;
	
	public Map<EnumEvents, EventType> eventmap = null;
	
	public static final EventType InitAppMenu            = new EventType();
	public static final EventType AnotherEvent           = new EventType();
	public static final EventType ShowLoginEvent         = new EventType();
	public static final EventType InitShowCaseEvent      = new EventType();
	public static final EventType ShowMessageDialogEvent = new EventType();
	public static final EventType HideMessageDialogEvent = new EventType();
	public static final EventType ShowAboutWindowEvent   = new EventType();

	public static final EventType SelectSubViewEvent = new EventType();

	public static final EventType CloseShowCaseEvent = new EventType();
	
}
