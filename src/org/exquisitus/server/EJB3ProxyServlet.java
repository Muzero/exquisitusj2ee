package org.exquisitus.server;

import org.exquisitus.client.services.EJB3ProxyService;

import org.exquisitus.server.ejb3.SimpleEjbLocal;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class EJB3ProxyServlet extends RemoteServiceServlet implements EJB3ProxyService {

	
	
	@Override
	public String EJB3StringReverse(String str) {
		
		BusinessDelegate businessDelegate = BusinessDelegateFactory.getBusinessDelegate();
		SimpleEjbLocal ejb = businessDelegate.getLocalSimpleEJB();

		// Thanks business delegate! ;-)
		ejb.reverseString(str);

		return null;
	}

}
