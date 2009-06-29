package org.exquisitus.client.subview;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;

public abstract class AbstractSubPanelTemplate extends ContentPanel implements ISubPanelInterface {
	
	protected boolean activated = false;
	
	public AbstractSubPanelTemplate() {
		initialize();
	}

	private void initialize() {
		
		setHeaderVisible(false);
		setBodyStyle("padding: 12px; fontSize: 16px;");
		setFrame(false);
		setHeading(getViewName());
		setLayout(new RowLayout());
		setScrollMode(Scroll.AUTOY);
		
	}
	
	@Override
	public void refresh() {
		
		if (activated == false) {
			init();
			activated = true;
		}
	}
	
	@Override
	public void dispose() {
		// dispose?
	}
	
	public String[] getSourceCodeClasses() {
		return null;
	}
}
