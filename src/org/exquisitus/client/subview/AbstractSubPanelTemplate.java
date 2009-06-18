package org.exquisitus.client.subview;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public abstract class AbstractSubPanelTemplate extends ContentPanel {
	
	protected boolean activated = false;
	
	public AbstractSubPanelTemplate() {
		initialize();
	}

	private void initialize() {
		
		setLayout(new FitLayout());
		setFrame(false);
		setHeading("${FIXME}");
		setHeaderVisible(true);
		setStyleAttribute("margin", "10 10 10 10px");
	}
}
