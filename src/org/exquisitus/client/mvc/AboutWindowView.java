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
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;

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
		
		RequestBuilder rbIntro = new RequestBuilder(RequestBuilder.GET, GWT.getHostPageBaseURL() +"intro");
		
		TabItem introItem = new TabItem("Intro");  
		introItem.addStyleName("pad-text");  
		introItem.setAutoLoad(rbIntro);
		folder.add(introItem);  
		
		//RequestBuilder rbDesign = new RequestBuilder(RequestBuilder.GET, "http://cloud.github.com/downloads/Muzero/exquisitusj2ee/ExquisitusJ2EE.jpg" ); //GWT.getHostPageBaseURL() +"design");
		
		TabItem designItem = new TabItem("Design");  
		designItem.addStyleName("pad-text");
		designItem.setLayout(new FitLayout());
		designItem.setScrollMode(Scroll.AUTO);
		designItem.add(new HtmlContainer("<a href='http://cloud.github.com/downloads/Muzero/exquisitusj2ee/ExquisitusJ2EE.jpg'>" +
				"<img style='height: 100%; width: 100%;' src='http://cloud.github.com/downloads/Muzero/exquisitusj2ee/ExquisitusJ2EE.jpg' //><//a>"));
		//designItem.setAutoLoad(rbDesign);
		folder.add(designItem); 
		
		RequestBuilder rbAbout = new RequestBuilder(RequestBuilder.GET, GWT.getHostPageBaseURL() +"about");
		
		TabItem aboutItem = new TabItem("About");  
		aboutItem.addStyleName("pad-text");
		aboutItem.setAutoLoad(rbAbout);
		aboutItem.setScrollMode(Scroll.AUTO);  
		aboutItem.setEnabled(false);
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
