package org.exquisitus.client.subview.ejb3example1;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class Ejb3InvokeResult implements Result {

	private String result;
	
	private Ejb3InvokeResult() {
		// now the compiler is happy
	}
	
	public Ejb3InvokeResult(String str) {
		this.result = str;
	}

	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
}
