package org.exquisitus.client.subview;

// This annotation is useful for create the tree of subViews

public @interface SubView {
	
	String panelName();

	String category();

	String subCategory();

}
