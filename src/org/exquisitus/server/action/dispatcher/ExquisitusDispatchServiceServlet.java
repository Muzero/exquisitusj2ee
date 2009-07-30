package org.exquisitus.server.action.dispatcher;

import net.customware.gwt.dispatch.server.service.DispatchServiceServlet;

public final class ExquisitusDispatchServiceServlet extends DispatchServiceServlet {
		
	public ExquisitusDispatchServiceServlet() {
	    super( DispathHelper.getDispatch() );	
	}
	
	/*@Override
	public Result execute(Action<?> action) throws ActionException {
	
		ActionHandlerRegistry registry = DispathHelper.getActionHandlerRegistry()();
		
		if (registry.findHandler(action) == null)
	
			 try {
				 	Class<ActionHandler<?, ?>> t = (Class<ActionHandler<?, ?>>) 
				 		Class.forName("");
					registry.addHandler(t.newInstance());
					    
			    } catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			
			return super.execute(action);	
	}*/
}
