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
	
	private static final String SIMPLEEJB = "SimpleEjbBean";
	private static final String MAILEJB = "MailEjbBean";
	
	private static Logger log = Logger.getLogger(BusinessDelegate.class.getName());

	private ServiceLocator sl = null;
	
	public BusinessDelegate(ServiceLocator serviceLocator) {
		this.sl = serviceLocator;
	}
	
	public SimpleEjbLocal getLocalSimpleEJB() {
		return sl.lookupResource(SIMPLEEJB, ServiceLocatorContext.LOCAL);
	}

	public SimpleEjbRemote getRemoteSimpleEJB() {
		return sl.lookupResource(SIMPLEEJB, ServiceLocatorContext.REMOTE);
	}

	public MailEjbLocal getLocalMailEJB3() {
		
		return sl.lookupResource(MAILEJB, ServiceLocatorContext.LOCAL);
	}
	

}
