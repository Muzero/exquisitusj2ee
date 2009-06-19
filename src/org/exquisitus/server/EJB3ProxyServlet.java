package org.exquisitus.server;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.exquisitus.client.services.EJB3ProxyService;

import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class EJB3ProxyServlet extends RemoteServiceServlet implements EJB3ProxyService {

	@Override
	public String EJB3StringReverse(String str) {
		
		try {
					
		InitialContext ctx = null;
		RPCRequest rpcReq = null;
		
		ctx = getJBOSSInitialContext();
		
		}
		catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private InitialContext getJBOSSInitialContext() throws NamingException {
		
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		env.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		
		return new InitialContext(env);
	}
	
	private InitialContext getInitialContext() throws NamingException {
	
		return new InitialContext();
		
	}

}
