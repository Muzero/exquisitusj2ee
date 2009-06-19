package org.exquisitus.client.subview;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public abstract class AbstractSubPanelTemplate extends ContentPanel implements ISubPanelInterface {
	
	protected boolean activated = false;
	
	public AbstractSubPanelTemplate() {
		initialize();
	}

	private void initialize() {
		
		setLayout(new FitLayout());
		setFrame(false);
		setHeaderVisible(true);
		setBodyStyle("fontSize: 16px;");
		
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
		// TODO Auto-generated method stub	
	}
}
