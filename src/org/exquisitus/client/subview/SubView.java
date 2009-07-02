package org.exquisitus.client.subview;

// This annotation is useful for create the tree of subViews

public @interface SubView {
	
	public String panelName();

	public String category();

	public String subCategory();

}
