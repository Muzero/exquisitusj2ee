package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;
import org.exquisitus.client.services.GreetingServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.ButtonArrowAlign;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/*
 * “If builders built buildings the way programmers write programs, 
 * then the first woodpecker that came along would destroy civilization.”
 * Weinberg’s Second Law (born: 1933 age: 76)
 * */

public class AppView extends View {

	private final String PAGEHEADERTITLE = "ExquisitusJ2EE Explorer Showcase 同法》规定：企业在制定、修改有关规章制度时，应当经职工代表大会或者全体职工";

	private BorderLayout blayout = new BorderLayout();
	private HtmlContainer northPanel = null;
	private LayoutContainer centrePanel = null;

	private Button btnMainMenu = new Button();

	public AppView(Controller controller) {
		super(controller);
	}

	protected void createView() {
		Viewport vp = new Viewport();
		vp.setLayout(blayout);

		StringBuffer sb = new StringBuffer();
		sb
				.append("<div id='exquisitus-north-panel'></div><div id=demo-title><h3>"
						+ PAGEHEADERTITLE + "</h3></div>");

		northPanel = new HtmlContainer(sb.toString());
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
				 
		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH,33);

		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);

		vp.add(northPanel, northData);
		vp.add(centrePanel, centerData);		
		
		centrePanel.add(btnMainMenu);
		RootPanel.get().add(vp);
		
	}

	@Override
	protected void handleEvent(AppEvent event) {

		if (event.getType() == ApplicationEvents.InitAppMenu)
			createView();

	}

	private Menu createMenu() {
		Menu menu = new Menu();

		MenuItem itemLogin = new MenuItem("Login");
		itemLogin.addSelectionListener(new SelectionListener<MenuEvent>() {

			@Override
			public void componentSelected(MenuEvent ce) {
				Dispatcher.forwardEvent(ApplicationEvents.ShowLoginEvent);
			}
		});
		menu.add(itemLogin);

		MenuItem itemShowCase = new MenuItem("J2EE ShowCase");
		itemShowCase.addSelectionListener(new SelectionListener<MenuEvent>() {

			@Override
			public void componentSelected(MenuEvent ce) {
				Dispatcher.forwardEvent(ApplicationEvents.InitShowCaseEvent);
			}
		});
		menu.add(itemShowCase);

		MenuItem itemContact = new MenuItem("Contact");
		itemContact.addSelectionListener(new SelectionListener<MenuEvent>() {

			@Override
			public void componentSelected(MenuEvent ce) {

			}
		});
		menu.add(itemContact);

		MenuItem itemAbout = new MenuItem("About");
		itemAbout.addSelectionListener(new SelectionListener<MenuEvent>() {

			@Override
			public void componentSelected(MenuEvent ce) {

			}
		});
		menu.add(itemAbout);

		return menu;
	}

	@Override
	protected void initialize() {
		btnMainMenu = new Button("Hello J2EE Experiment!");

		btnMainMenu.setMenu(createMenu());
		btnMainMenu.setArrowAlign(ButtonArrowAlign.BOTTOM);
		btnMainMenu.setIconStyle("exec"); // TODO FIXME not working yet damn

		btnMainMenu.setShim(true);
		btnMainMenu.setShadow(true);
		btnMainMenu.setPosition(40, 40);

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
