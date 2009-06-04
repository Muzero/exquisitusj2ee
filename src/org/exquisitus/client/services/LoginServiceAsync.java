package org.exquisitus.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	
	void login(String user, String password, AsyncCallback<String> callback);
	void register(String user, String password, String email, AsyncCallback<String> callback);
}

