package org.exquisitus.client.subview;

// every subview *must* implements that interface @muzero

public interface ISubPanelInterface {

	public void init();
	public void refresh();
	public void dispose();
	
	public String getViewName();
}
