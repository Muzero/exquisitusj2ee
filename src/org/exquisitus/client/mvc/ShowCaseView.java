package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;

public class ShowCaseView extends View {
	
	private Window mainwindow = null;

	public ShowCaseView(Controller controller) {
		super(controller);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		
		mainwindow = new Window();
		mainwindow.setSize(1024, 768);
		mainwindow.setModal(false);
		mainwindow.setHeading("ExquisitusJ2EE Showcase");
		mainwindow.setLayout(new BorderLayout()); 
		mainwindow.hide();
		
		ContentPanel west = createMenuPanel();
		ContentPanel center = new ContentPanel();  
		
		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 250);  
		westData.setSplit(false);
		westData.setCollapsible(false);  
		
		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		
		mainwindow.add(west, westData);
		mainwindow.add(center,centerData);
	}
	
	private ContentPanel createMenuPanel() {
		ContentPanel panel = new ContentPanel();
		panel.setFrame(false);
		return panel;
	}

	@Override
	protected void handleEvent(AppEvent event) {
		
		if (event.getType() == ApplicationEvents.InitShowCaseEvent)
			mainwindow.show();
	}

}
