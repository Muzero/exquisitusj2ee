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

public class ServiceLocatorOPENEJB extends ServiceLocator {
	
	private static Logger log = Logger.getLogger(ServiceLocatorOPENEJB.class.getName());
	
	private static final String REMOTE_EJB_CONTAINER = "127.0.0.1:8080";

	static {
		
		try {
			setInstance(new ServiceLocatorOPENEJB());
			cache = Collections.synchronizedMap(new HashMap<String, Object>());
			
		} catch (NamingException e) {
			
			log.log(Level.SEVERE, e.getMessage());
			e.printStackTrace(System.err);
		}
		
	};
	
	private ServiceLocatorOPENEJB() throws NamingException {
		
		initLocalServiceLocator();
		initRemoteServiceLocator(REMOTE_EJB_CONTAINER,"system","admin"); // TODO
	}
	
	private void initLocalServiceLocator() throws NamingException { // get Abstract!
		Properties properties = new Properties();
		properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
		
		ctx = new InitialContext(properties);
		
		log.log(Level.INFO, "Local Initial Context ServiceLocator created.");
	}
	
	private void initRemoteServiceLocator(String url, String user, String password) throws NamingException {
		Properties properties = new Properties();
		properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
		properties.put(Context.PROVIDER_URL, "http://" + url + "/openejb/ejb");
		// p.put("java.naming.security.principal", "myuser");
		// p.put("java.naming.security.credentials", "mypass");
		
		ctxRemote = new InitialContext(properties);
		
		log.log(Level.INFO, "Remote Initial Context ServiceLocator created.");
	}
	
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
		
			T ref = (T) ctx.lookup(idResource); // es "SimpleEjbBeanLocal"
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
			
			T ref = (T) ctxRemote.lookup(idResource); 
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
