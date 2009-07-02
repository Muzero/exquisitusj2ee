package org.exquisitus.client.subview;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
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
	
	@Override // Lazy initialization of the panel at first refresh() call
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
	
	// this functions MUST be replaced by Annotations
	// TODO find a way to use Reflection on GWT client side
	@Deprecated abstract public String getPanelName();
	@Deprecated abstract public String getCategory();
	@Deprecated abstract public String getSubCategory();
	
	// utility function
	protected Html format(String text) {  
		return new Html("<span class='text' style='margin: 10px'>" + text + "</span>");  
	}  
		   
	protected Html formatHeader(String text) {  
		return new Html(  
				"<div class='text' style='width: 400px; background-color: #ddd;padding: 4px'><b>"  
		             + text + 
		             "</b></div>");  
	}  
}
