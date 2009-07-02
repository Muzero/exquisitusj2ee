package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.TabPanel.TabPosition;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class AboutWindowView extends View {

	private static final int XWINLOGIN = 640;
	private static final int YWINLOGIN = 480;
	
	private Window aboutWindow = null;
	
	public AboutWindowView(Controller controller) {
		super(controller);	
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		
		aboutWindow = new Window();
		aboutWindow.setBodyBorder(true);
		aboutWindow.setHeading("Exquisitus J2EE About Dialog");
		aboutWindow.setWidth(XWINLOGIN);
		aboutWindow.setHeight(YWINLOGIN);
		
		//aboutDialog.s
		//aboutDialog.setHideOnButtonClick(true);
		aboutWindow.setShadow(true);
		aboutWindow.setResizable(false);
		//	aboutDialog.setFrame(false);
		aboutWindow.setLayout(new FitLayout());
		
		// put 3 tab panels 
		// intro , design , info
		
		TabPanel folder = new TabPanel();  
		folder.setTabPosition(TabPosition.BOTTOM);
		
		TabItem introItem = new TabItem("Intro");  
		introItem.addStyleName("pad-text");  
		folder.add(introItem);  
		
		TabItem designItem = new TabItem("Design");  
		designItem.addStyleName("pad-text"); 
		folder.add(designItem); 
		
		TabItem aboutItem = new TabItem("About");  
		aboutItem.addStyleName("pad-text");
		aboutItem.setScrollMode(Scroll.AUTO);  
		folder.add(aboutItem); 
		
		
		HtmlContainer c = new HtmlContainer();
		c.setHtml(createDialogContent());
		
		aboutWindow.add(folder);
		aboutWindow.hide();
	}

	private String createDialogContent() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("");
		
		return sb.toString();
	}

	public void showWindow() {
		
		aboutWindow.show();
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == ApplicationEvents.ShowAboutWindowEvent)
			showWindow();
		
	}
	

}
