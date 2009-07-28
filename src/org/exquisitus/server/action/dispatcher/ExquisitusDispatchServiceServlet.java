package org.exquisitus.server.action.dispatcher;

import net.customware.gwt.dispatch.server.service.DispatchServiceServlet;

public final class ExquisitusDispatchServiceServlet extends DispatchServiceServlet {

	public ExquisitusDispatchServiceServlet() {
	    super( DispathHelper.getDispatch() );
	  }
	
}
