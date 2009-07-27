package org.exquisitus.client.subview.ejb3example1;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class Ejb3InvokeAction implements Action<Ejb3InvokeResult> {

	private String strParam;
	
	public void setStrParam(String strParam) {
		this.strParam = strParam;
	}
	
	public String getStrParam() {
		return strParam;
	}
}
