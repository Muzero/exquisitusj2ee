package org.exquisitus.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	
	/**
	 * Login the selected User
	 * 
	 * @param user the user name
	 * @param password the user password
	 * @return the result
	 */
	public String login(String user,String password);
	
	/**
	 * Register a new user
	 * 
	 * @param user
	 * @param password
	 * @param email
	 * @return
	 */
	public String register(String user, String password, String email);
}
