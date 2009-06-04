
package org.exquisitus.client;

import java.util.Map;

import com.extjs.gxt.ui.client.event.EventType;

public final class ApplicationEvents {

	// disallow Constructor
	private ApplicationEvents() {}

	enum EnumEvents {  // TODO put the event bill into the eventmap and create EnumEvent with Spring
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
	public static final EventType ShowAboutDialogEvent   = new EventType();
	
}
