package org.exquisitus.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EJB3ProxyServiceAsync {

	public void EJB3StringReverse(String str, AsyncCallback<String> callback); 
}
