package org.exquisitus.server;

import java.util.logging.Logger;

import org.exquisitus.server.ServiceLocator.ServiceLocatorContext;
import org.exquisitus.server.ejb3.SimpleEjbLocal;
import org.exquisitus.server.ejb3.SimpleEjbRemote;

public class BusinessDelegate {
	
	private static final String SIMPLEEJBLOCAL = "SimpleEjbBeanLocal";
	private static final String SIMPLEEJBREMOTE = "SimpleEjbBeanRemote";
	
	private static Logger log = Logger.getLogger(BusinessDelegate.class.getName());

	private ServiceLocator sl = null;
	
	public BusinessDelegate(ServiceLocator serviceLocator) {
		this.sl = serviceLocator;
	}
	
	public SimpleEjbLocal getLocalSimpleEJB() {
		return sl.lookupResource(SIMPLEEJBLOCAL, ServiceLocatorContext.LOCAL);
	}

	public SimpleEjbRemote getRemoteSimpleEJB() {
		return sl.lookupResource(SIMPLEEJBREMOTE, ServiceLocatorContext.REMOTE);
	}
	

}
