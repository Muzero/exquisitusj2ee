package org.exquisitus.client.services;

/**
 * Message Response
 * 
 * @author muzero
 */

import net.customware.gwt.dispatch.shared.SimpleResult;

@SuppressWarnings("serial")
public class MessageResponse implements SimpleResult<String> {

	private String response = new String();
	
	public String getResponse() {
		return response;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	
	public MessageResponse() {
	}
	
	public MessageResponse(String response) {
		this.response = response;
	}	
	
	@Override
	public String get() {
		return response;
	}
}