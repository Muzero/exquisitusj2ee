package org.exquisitus.server.action.dispatcher;

import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.exquisitus.client.model.valueobjects.UserVO;

import net.customware.gwt.dispatch.server.service.DispatchServiceServlet;

public final class ExquisitusDispatchServiceServlet extends DispatchServiceServlet {
	
	public static final String APPLICATIONUSERLIST = "APPLICATIONUSERLIST";
	
//	public static ServletContext servletContext = null;
	
	public ExquisitusDispatchServiceServlet() {
	    super( DispathHelper.getDispatch() );
	    
	//	servletContext = this.getServletContext();
		
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

//	public ServletContext getServletContext() {
	//	return getThreadLocalRequest().getSession().getServletContext();
//	}
}
