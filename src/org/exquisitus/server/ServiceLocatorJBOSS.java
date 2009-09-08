package org.exquisitus.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/*
 * EJB3 Service Locator
 * Old fashion way to discover jndi services for Business Delegate
 * TODO: must be abstract and implemented Vendor Specific Service Locator (JBoss, WebSphere, OpenEJB,...)
 * 
 * @author davassi
 */

public class ServiceLocatorJBOSS extends ServiceLocator  {
	
	static {
		
		try {
			setInstance(new ServiceLocatorJBOSS());
			cache = Collections.synchronizedMap(new HashMap<String, Object>());
			
		} catch (NamingException e) {
			
			log.log(Level.SEVERE, e.getMessage());
			e.printStackTrace(System.err);
		}
		
	};
	
	private static final String REMOTE_EJB_CONTAINER = "127.0.0.1:8080";
	
	public ServiceLocatorJBOSS() throws NamingException {
		
		initLocalServiceLocator();
		initRemoteServiceLocator(REMOTE_EJB_CONTAINER,"system","admin"); // TODO
	}
	
	private void initLocalServiceLocator() throws NamingException { // get Abstract!
		Properties properties = new Properties();

		ctx = new InitialContext(properties);
		
		log.log(Level.INFO, "Local Initial Context ServiceLocator created.");
	}
	
	private void initRemoteServiceLocator(String url, String user, String password) throws NamingException {
		Properties properties = new Properties();
		
		ctxRemote = new InitialContext(properties);
		
		log.log(Level.INFO, "Remote Initial Context ServiceLocator created.");
	}
	
	/* (non-Javadoc)
	 * @see org.exquisitus.server.ServiceLocator#lookupResource(java.lang.String, org.exquisitus.server.ServiceLocatorJBOSS.ServiceLocatorContext)
	 */
	@SuppressWarnings("unchecked")
	public <T> T lookupResource(String idResource, ServiceLocatorContext slc) {

		if (cache.containsKey(idResource)) // TODO check Local or Remote? 
			return (T) cache.get(idResource);
		
		return (T) ((slc == ServiceLocatorContext.LOCAL) ?
				
				lookupLocalResource(idResource)
			:
				lookupRemoteResource(idResource));	
	}

	@SuppressWarnings("unchecked")
	private <T> T lookupLocalResource(String idResource) {
	
		try {
			log.log(Level.INFO, "OK... let's lookup Local (" + idResource + ") !");
		
			T ref = (T) ctx.lookup(idResource + "/local"); // es "SimpleEjbBeanLocal"
			cache.put(idResource, ref);
			
			return ref;
			
		} catch (NamingException e) {
	
			log.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} 

		return null;	
	}
	
	@SuppressWarnings("unchecked")
	private <T> T lookupRemoteResource(String idResource) {
		
		try {
			log.log(Level.INFO, "OK... let's lookup Remote (" + idResource + ") !");
			
			T ref = (T) ctxRemote.lookup(idResource+"/remote"); 
			T ejb = (T)PortableRemoteObject.narrow(ref,Object.class);
			cache.put(idResource, ejb);
			
			return ref;
			
		} catch (NamingException e) {
	
			log.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} 
	
		return null;	
	}
	
}
