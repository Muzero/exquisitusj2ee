
package org.exquisitus.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserVO implements IsSerializable{

	private boolean auth;
	private String email;
	private String password;
	private String role;
	private String username;

	public UserVO() {}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
