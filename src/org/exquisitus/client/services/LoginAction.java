package org.exquisitus.client.services;

/**
 * Login Action 
 * 
 * @author muzero
 */

import org.exquisitus.client.model.valueobjects.UserVO;
import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class LoginAction implements Action<UserVO> {

	public LoginAction() {}
	
	public LoginAction(String name, String pass) {
		setName(name);
		setPassword(pass);
	}
	
	private String name;
	private String password;
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
