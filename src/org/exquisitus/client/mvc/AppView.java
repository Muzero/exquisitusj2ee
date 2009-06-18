package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.ButtonArrowAlign;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.user.client.ui.RootPanel;

/*
 * “If builders built buildings the way programmers write programs, 
 * then the first woodpecker that came along would destroy civilization.”
 * Weinberg’s Second Law (born: 1933 age: 76)
 * */

public class AppView extends View {

	private final static String PAGEHEADERTITLE = "ExquisitusJ2EE Explorer Showcase ";
	
	public final static String MAINAPPVIEWPORT = "MAINAPPVIEWPORT";
	
	public static String createUpperBoundPanelContent(String info) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='exquisitus-north-panel'></div><div id=demo-title><h3>");
		sb.append(PAGEHEADERTITLE);
		sb.append(info);
		sb.append("</h3></div>");
		
		return sb.toString();
	}
	
	private BorderLayout blayout = new BorderLayout();
	private HtmlContainer northPanel = null;
	private LayoutContainer centrePanel = null;

	private Button btnMainMenu = new Button();

	public AppView(Controller controller) {
		super(controller);
	}
	
	public void setApplicationTitle(String additionalInfo) {
		northPanel.setHtml(createUpperBoundPanelContent(additionalInfo));
	}

	protected void createView() {
		Viewport vp = new Viewport();
		vp.setLayout(blayout);

		northPanel = new HtmlContainer(createUpperBoundPanelContent(" - Anonymous User"));

		northPanel.setBorders(false);
		northPanel.setEnableState(false);
		northPanel.setId("exquisitus-header");
		northPanel.setStyleAttribute("background", "#1E4176");
		northPanel.setStyleAttribute("border", "0pt none");		
		northPanel.setStyleAttribute("color", "white");
		//northPanel.setStyleAttribute("font", "18px Arial, Helvetica, sans-serif");
		northPanel.setStyleAttribute("font", "16px tahoma, arial, sans-serif");
		northPanel.setStyleAttribute("padding", "6 0 8 6px");

		centrePanel = new LayoutContainer();
		centrePanel.setBorders(false);
		centrePanel.setStyleName("background-panel");
		centrePanel.setLayout(new FitLayout());
		
		LayoutContainer secondlayerPanel = new LayoutContainer();
		secondlayerPanel.setStyleName("secondlayer-panel");
		centrePanel.add(secondlayerPanel);
		
		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH,33);

		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);

		vp.add(northPanel, northData);
		vp.add(centrePanel, centerData);		
		
		secondlayerPanel.add(btnMainMenu);
		RootPanel.get().add(vp);

		Registry.register(MAINAPPVIEWPORT, this);
	}

	@Override
	protected void handleEvent(AppEvent event) {

		if (event.getType() == ApplicationEvents.InitAppMenu)
			createView();

	}

	private Menu createMenu() {
		Menu menu = new Menu();

		MenuItem itemLogin = new MenuItem("Login");
		itemLogin.setIcon(GXT.IMAGES.editor_link());
		itemLogin.addSelectionListener(new SelectionListener<MenuEvent>() {

			@Override
			public void componentSelected(MenuEvent ce) {
				Dispatcher.forwardEvent(ApplicationEvents.ShowLoginEvent);
			
			}
		});
		menu.add(itemLogin);

		MenuItem itemShowCase = new MenuItem("J2EE ShowCase");
		itemShowCase.setIcon(GXT.IMAGES.editor_source());
		itemShowCase.addSelectionListener(new SelectionListener<MenuEvent>() {

			@Override
			public void componentSelected(MenuEvent ce) {
				Dispatcher.forwardEvent(ApplicationEvents.InitShowCaseEvent);
			}
		});
		menu.add(itemShowCase);

		MenuItem itemContact = new MenuItem("Contact");
		itemContact.setEnabled(false);
		itemContact.setIcon(GXT.IMAGES.checked());
		itemContact.addSelectionListener(new SelectionListener<MenuEvent>() {

			@Override
			public void componentSelected(MenuEvent ce) {

			}
		});
		menu.add(itemContact);

		MenuItem itemAbout = new MenuItem("About");
		itemAbout.setEnabled(false);
		itemAbout.setIcon(GXT.IMAGES.field_invalid());
		itemAbout.addSelectionListener(new SelectionListener<MenuEvent>() {

			@Override
			public void componentSelected(MenuEvent ce) {

			}
		});
		menu.add(itemAbout);
		
		menu.add(new SeparatorMenuItem());

		MenuItem itemUsers = new MenuItem("User Online");
		itemUsers.setIcon(GXT.IMAGES.tree_folder());
		menu.add(itemUsers);
		itemUsers.setEnabled(false);
		return menu;
	}

	@Override
	protected void initialize() {
		btnMainMenu = new Button(/*"Hello J2EE Experiment!"*/);

		btnMainMenu.setMenu(createMenu());
		btnMainMenu.setArrowAlign(ButtonArrowAlign.BOTTOM);
		btnMainMenu.setIcon(IconHelper.create("/resources/images/icons/network.png",64,64));
		btnMainMenu.setHeight(70);
		btnMainMenu.setWidth(70);
		btnMainMenu.setIconAlign(IconAlign.BOTTOM); 
		
		btnMainMenu.setShim(true);
		btnMainMenu.setShadow(true);
		btnMainMenu.setPosition(40, 40);
		
		ToolTipConfig ttc = new ToolTipConfig();
		ttc.setShowDelay(500);
		ttc.setTitle("Exquisitus J2EE Experiments");
		ttc.setTrackMouse(true);
		ttc.setText("Click on the button to discover the ExquisitusJ2EE Menu");
		btnMainMenu.setToolTip(ttc);  

	}

	/*
	 * cp.sinkEvents(Event.ONMOUSEOVER);
	 * 
	 * cp.addListener(new EventType(Event.ONMOUSEOVER), new
	 * Listener<ComponentEvent>() {
	 * 
	 * @Override public void handleEvent(ComponentEvent be) {
	 * GWT.log(be.getXY().x + " " + be.getXY().y, null);
	 * 
	 * }
	 * 
	 * });
	 */

}
