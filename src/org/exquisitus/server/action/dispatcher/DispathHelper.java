package org.exquisitus.server.action.dispatcher;

/**
 * Dispatch Helper
 * 
 * @author muzero 
 */

import org.exquisitus.server.action.Ejb3InvokeHandler;
import org.exquisitus.server.action.LoginServiceHandler;
import org.exquisitus.server.action.MessageServiceHandler;
import org.exquisitus.server.action.RegisterServiceHandler;

import net.customware.gwt.dispatch.server.ActionHandlerRegistry;
import net.customware.gwt.dispatch.server.DefaultActionHandlerRegistry;
import net.customware.gwt.dispatch.server.DefaultDispatch;
import net.customware.gwt.dispatch.server.Dispatch;


public class DispathHelper {

	private static final ActionHandlerRegistry registry = new DefaultActionHandlerRegistry();
	private static final Dispatch disp = new DefaultDispatch(registry);
	
	static {
		
		registry.addHandler(new Ejb3InvokeHandler());
		registry.addHandler(new LoginServiceHandler());
		registry.addHandler(new RegisterServiceHandler());
		registry.addHandler(new MessageServiceHandler());
	}

	public static ActionHandlerRegistry getActionHandlerRegistry() {
		return registry;
	}

	public static Dispatch getDispatch() {
		return disp;
	}
}
