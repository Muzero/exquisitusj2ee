package org.exquisitus.client.services;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.SimpleResult;

/**
 * Message Action 
 * 
 * @author muzero
 *
 */

@SuppressWarnings("serial")
public class MessageAction implements Action<SimpleResult<String>> {

	private String username;
	private String email;
	private String title;
	private String humor;
	private String type;
	private String message;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHumor() {
		return humor;
	}
	public void setHumor(String humor) {
		this.humor = humor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
}
