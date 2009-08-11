package org.exquisitus.server;

/**
 * Business Delegate
 * 
 * @author muzero
 */

import java.util.logging.Logger;

import org.exquisitus.server.ServiceLocator.ServiceLocatorContext;
import org.exquisitus.server.ejb3.MailEjbLocal;
import org.exquisitus.server.ejb3.SimpleEjbLocal;
import org.exquisitus.server.ejb3.SimpleEjbRemote;

public class BusinessDelegate {
	
	private static final String SIMPLEEJBLOCAL = "SimpleEjbBeanLocal";
	private static final String SIMPLEEJBREMOTE = "SimpleEjbBeanRemote";
	
	private static final String MAILEJBLOCAL = "MailEjbBeanLocal";
	
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

	public MailEjbLocal getLocalMailEJB3() {
		
		return sl.lookupResource(MAILEJBLOCAL, ServiceLocatorContext.LOCAL);
	}
	

}
