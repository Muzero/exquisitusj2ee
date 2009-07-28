package org.exquisitus.client.services;

import org.exquisitus.client.model.valueobjects.UserVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class RegisterAction implements Action<UserVO> {

	private String name;
	private String pass;
	private String email;
	
	public RegisterAction() {
	}
	
	public RegisterAction(String username, String password,	String email) {
		setName(username);
		setPass(password);
		setEmail(email);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
