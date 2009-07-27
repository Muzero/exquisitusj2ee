package org.exquisitus.server.action.dispatcher;

import net.customware.gwt.dispatch.server.service.DispatchServiceServlet;

public final class ExquisitusDispatchServiceServlet extends DispatchServiceServlet {
	
	/**
	 * useless...
	 */
	private static final long serialVersionUID = 3678419672341965778L;

	public ExquisitusDispatchServiceServlet() {
	    super( DispathHelper.getDispatch() );
	  }
	
}
