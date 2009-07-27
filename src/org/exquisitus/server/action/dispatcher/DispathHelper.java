package org.exquisitus.server.action.dispatcher;

import org.exquisitus.server.action.Ejb3InvokeHandler;

import net.customware.gwt.dispatch.server.ActionHandlerRegistry;
import net.customware.gwt.dispatch.server.DefaultActionHandlerRegistry;
import net.customware.gwt.dispatch.server.DefaultDispatch;
import net.customware.gwt.dispatch.server.Dispatch;


public class DispathHelper {

	private static final ActionHandlerRegistry registry = new DefaultActionHandlerRegistry();
	private static final Dispatch disp = new DefaultDispatch(registry);
	
	static {
		
		registry.addHandler(new Ejb3InvokeHandler());
	}

	public static ActionHandlerRegistry getActionHandlerRegistry() {
		return registry;
	}

	public static Dispatch getDispatch() {
		return disp;
	}
}
