package org.exquisitus.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ejb3proxy")
public interface EJB3ProxyService extends RemoteService {
	
	public String EJB3StringReverse(String str); 

}
