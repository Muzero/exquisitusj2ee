
package org.exquisitus.client.model.valueobjects;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class UserVO implements Result {

	private boolean auth;
	private String email;
	private String password;
	private String role;
	private String username;

	public UserVO() {} // necessary to avoid compiler error, even private

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
