package org.exquisitus.client.mvc;

/**
 * This is the View Layer of ShowCaseView Dialog
 * 
 * @author muzero
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.exquisitus.client.ApplicationEvents;
import org.exquisitus.client.ExquisitusJ2EE;
import org.exquisitus.client.icons.ExquisitusIconBundle;
import org.exquisitus.client.subview.AbstractSubPanelTemplate;
import org.exquisitus.client.subview.ISubPanelInterface;
import org.exquisitus.client.subview.PanelData;
import org.exquisitus.client.subview.ejb3example1.Ejb3Example1View;
import org.exquisitus.client.subview.ejb3stateful.Ejb3ShoppingCartView;
import org.exquisitus.client.subview.spring.aop.SpringAopSimpleExampleView;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.TabPanel.TabPosition;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanelSelectionModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.ui.Widget;

public class ShowCaseView extends View {

	private static final int XWIN = 1024;
	private static final int YWIN = 600;

	private Window mainwindow = null;

	private ContentPanel currentPanel = null;

	private Map<String, AbstractSubPanelTemplate> cachepanelmap = null;
	private Map<String, TabPanel>    cachesourcemap = null;

	private BorderLayoutData centerData;

	public ShowCaseView(Controller controller) {
		super(controller);
	}
	
	private TabItem demoPanelTab;
	private TabItem sourcePanelTab;
	private TabPanel mainTabPanel;
	
	private void asyncLoadPanels() { 
		// FIXME this is only a stub, the item must
		// be loaded with Annotation Reflection from server
		// for now we shall use some deprecated method instead
		
		// start bill
		insertPanelIntoShowCase( new Ejb3Example1View() );		
		insertPanelIntoShowCase( new SpringAopSimpleExampleView() );		
		insertPanelIntoShowCase( new Ejb3ShoppingCartView() );		
		
	}
	
	private void insertPanelIntoShowCase(AbstractSubPanelTemplate as) {
		cachepanelmap.put(as.getPanelName(), as);
	}

	@Override
	protected void initialize() {
		super.initialize();

		cachepanelmap = new LinkedHashMap<String, AbstractSubPanelTemplate>();
		cachesourcemap = new HashMap<String, TabPanel>();
			
		asyncLoadPanels();

		currentPanel = createIntroPanel();

		mainwindow = new Window();
		mainwindow.setSize(XWIN, YWIN);
		mainwindow.setModal(false);
		mainwindow.setHeading("ExquisitusJ2EE Showcase");
		mainwindow.setLayout(new FitLayout());
		mainwindow.setStyleAttribute("backgroundColor", "white");
		mainwindow.setCollapsible(true);
		mainwindow.setMaximizable(true);
		mainwindow.hide();
		
		Portlet contentShowCasePanel = new Portlet();
		contentShowCasePanel.setHeading("Example");
		contentShowCasePanel.setCollapsible(false);
		contentShowCasePanel.setAnimCollapse(false);
		contentShowCasePanel.setLayout(new FitLayout());
		contentShowCasePanel.setPinned(true);
	
		contentShowCasePanel.setFrame(true);

		contentShowCasePanel.setBodyStyle("backgroundColor: white;"); // padding: 10px; 
		
		ContentPanel cp = new ContentPanel();
		cp.setBodyStyle("backgroundColor: white;");
		//c.setBodyStyle("background: #000000 image(../images/icons/gb.gif) repeat-x");
		cp.setLayout(new BorderLayout());
		cp.setHeaderVisible(false);
		
		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 200);
		westData.setSplit(false);
		westData.setCollapsible(false);
		westData.setMargins(new Margins(10));
		
		centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setMargins(new Margins(10));
		
		cp.add(createMenuShowCase(),westData);
		cp.add(contentShowCasePanel,centerData);
		
		contentShowCasePanel.add( createTabFolder() );
		
		mainwindow.add(cp);
	}

	private TabPanel createTabFolder() { // add tabs to contentShowCasePanel
		
		mainTabPanel = new TabPanel();  
		//folder.setAutoHeight(true);  
		mainTabPanel.setAnimScroll(true);
		mainTabPanel.setTabPosition(TabPosition.BOTTOM);
		
		demoPanelTab = new TabItem("Demo");
		demoPanelTab.add(currentPanel);
		demoPanelTab.setScrollMode(Scroll.AUTOY);
		
		mainTabPanel.add(demoPanelTab);
		
		sourcePanelTab = new TabItem("Source");
		sourcePanelTab.setScrollMode(Scroll.AUTOY);
		
		/*sourcePanelTab.add(createSourceFolder(
				new String[] {"org.exquisitus.server.ServiceLocator" ,"org.exquisitus.client.mvc.ShowCaseView"
		}));*/
		
		mainTabPanel.add(sourcePanelTab);
		
		return mainTabPanel;
	}
	
	private TabPanel createSourceFolder(String[] classes) {
		
		TabPanel folder = new TabPanel();
		folder.setAnimScroll(true);
		folder.setTabPosition(TabPosition.TOP);
		folder.setTabScroll(true);
		
		for (String mclass : classes) {
			
			TabItem sourceTab = new TabItem(mclass);
			sourceTab.setLayout(new FlowLayout());
			sourceTab.setScrollMode(Scroll.ALWAYS);
			sourceTab.setHeight(600); // TODO FIXME
			sourceTab.setAutoWidth(true);
		
			// servlet source code loader from package name
			sourceTab.setAutoLoad(
					new RequestBuilder(RequestBuilder.GET, 
					GWT.getHostPageBaseURL() + "exquisitusj2ee/depict?file=" + mclass));

			folder.add(sourceTab);	
		}
		
		return folder;
	}

	// TODO 
	private Widget createMenuShowCase() {
		
		Portlet menuShowCase = new Portlet();
		menuShowCase.setHeading("ShowCase Selection");
		menuShowCase.setCollapsible(false);
		menuShowCase.setAnimCollapse(false);
		menuShowCase.setLayout(new AccordionLayout());
		menuShowCase.setPinned(true);
		menuShowCase.setIcon(GXT.IMAGES.checked());
		
		Collection<AbstractSubPanelTemplate> cas = cachepanelmap.values();
		
		for (AbstractSubPanelTemplate subPanel : cas)
		{
			createMenuCategory(menuShowCase, subPanel);
		}
		
		return menuShowCase;
	}
	
	
	private Map<String, ContentPanel> cacheCategory = new HashMap<String, ContentPanel>();
	private Map<String, TreePanel<PanelData>> cacheSubCategory = new HashMap<String, TreePanel<PanelData>>();
	private Map<String, PanelData> cachePanelData = new HashMap<String, PanelData>();
	
	private void createMenuCategory(Portlet menuShowCase, AbstractSubPanelTemplate subPanel) {
		
		/*SubView annotations = subPanel.getClass().getAnnotation(SubView.class);
		
		String categoryName = annotations.category();
		String subCategoryName = annotations.subCategory();
		String panelName = annotations.panelName();*/
		
		// FIXME replace it with an annotation/reflection engine!
		String categoryName = subPanel.getCategory();
		String subCategoryName = subPanel.getSubCategory();
		String panelName = subPanel.getPanelName();
		
		ContentPanel cp = cacheCategory.get(categoryName);
		
		if (cp == null) {
			
			cp = new ContentPanel();
			cp.setHeading(categoryName);
			cp.setLayout(new FitLayout());
			
			cacheCategory.put(categoryName, cp);
			
			menuShowCase.add(cp);
		}
	
		TreePanel<PanelData> tree = cacheSubCategory.get(subCategoryName);
		
		if (tree == null) 
		{ 
			tree = createSubCategory(subPanel);
			cacheSubCategory.put(subCategoryName, tree);
			cp.add(tree);
		}
		
		PanelData pd = cachePanelData.get(panelName);
		tree.getStore().add(pd, newItem(panelName, "icon1"), false); // FIXME use different icons!
	}
	
	private TreePanel<PanelData> createSubCategory(AbstractSubPanelTemplate subPanel) {
		
		TreeStore<PanelData> store = new TreeStore<PanelData>();  
		TreePanel<PanelData> tree = new TreePanel<PanelData>(store);
		tree.getStyle().setLeafIcon(GXT.IMAGES.editor_link());
		tree.setDisplayProperty(PanelData.NAME);
		
		PanelData m = newItem(subPanel.getSubCategory(), null);  
		store.add(m, false);  
		
		cachePanelData.put(subPanel.getPanelName(), m);
		
		tree.setExpanded(m, true); 
	
		TreePanelSelectionModel<ModelData> ts = new TreePanelSelectionModel<ModelData>();
		ts.bindTree(tree);
		
		ts.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				
				String selectedView = se.getSelectedItem().get(PanelData.NAME);
	
				Dispatcher.forwardEvent(ApplicationEvents.SelectSubViewEvent, selectedView);
			}
		});
		
		return tree;
	}
	
	private PanelData newItem(String name, String iconStyle) {  
		
		PanelData m = new PanelData();  
	    m.setName(name);
	    m.setIcon(iconStyle);
	    // m.setView(cachepanelmap.get(name)); // not used now
	 
	    return m;  
	}  

	@Override
	protected void handleEvent(AppEvent event) {

		if (event.getType() == ApplicationEvents.InitShowCaseEvent)
			mainwindow.show();

		if (event.getType() == ApplicationEvents.CloseShowCaseEvent)
			mainwindow.hide();

		if (event.getType() == ApplicationEvents.SelectSubViewEvent)
			onSubViewSelection(event);

	}

	private void onSubViewSelection(AppEvent event) {

		String selectedPanel = event.getData().toString();
		
		shiftPanelView(selectedPanel);
		shiftPanelSource(selectedPanel);
		
		mainTabPanel.setSelection(demoPanelTab);
	}
	
	private void shiftPanelView(String selectedPanel) {
		
		demoPanelTab.remove(currentPanel);
		
		currentPanel = !cachepanelmap.containsKey(selectedPanel) ? 
					currentPanel // getErrorPanel(selectedPanel)
				: 
					cachepanelmap.get(selectedPanel);

		ISubPanelInterface subpanel = (ISubPanelInterface) currentPanel;	
		subpanel.refresh(); // view is LAZY initialized at first refresh() call.

		demoPanelTab.add(currentPanel);
		demoPanelTab.layout();
	}

	private void shiftPanelSource(String selectedPanel) {
		
		TabPanel tabsource = cachesourcemap.get(selectedPanel);
		sourcePanelTab.setEnabled(true);
		sourcePanelTab.removeAll();
		if (tabsource == null)
		{
			String [] sp = ((AbstractSubPanelTemplate) currentPanel).getSourceCodeClasses();
			
			if (sp == null) {
				sourcePanelTab.setEnabled(false);
				return;
			}
			
			tabsource = createSourceFolder(sp);
			cachesourcemap.put(selectedPanel, tabsource);
		}
		
		sourcePanelTab.add(tabsource);	
	}

	private AbstractSubPanelTemplate createIntroPanel() {
		
		return new IntroSubViewPanel("IntroPanel");
	}

}

class IntroSubViewPanel extends AbstractSubPanelTemplate implements ISubPanelInterface {

	private static final String INTROPANEL = "INTRO PANEL";
	
	private String selectedPanel = null;
	
	public IntroSubViewPanel(String selectedPanel) { this.selectedPanel = selectedPanel; refresh(); }
	
	@Override
	public String getViewName() {
		return INTROPANEL;
	}

	@Override
	public void init() {
	
		setHeading(getViewName() + " " + selectedPanel);
		//setBodyStyle("fontSize: 18px; color: blue; background-color: #bbeeff; text-align:center; ");
		
		setBodyStyle("fontSize: 18px; color: blue; background-image:url('" + ExquisitusJ2EE.EXQUISITUS_ICON_BUNDLE.softgradient().getHTML() + "'); background-repeat:repeat-x; text-align:center; ");
		
		String content = "<div style='width: 100%; height: 100%;'>Select an item on the tree menu on the left to show the example!</div>";
		GWT.log(ExquisitusJ2EE.EXQUISITUS_ICON_BUNDLE.softgradient().getHTML(), null);
		Html html = new Html(content);
		add(html);
		setFrame(false);
		setHeaderVisible(false);
	}

	@Override
	public String getCategory() {
		return null;
	}

	@Override
	public String getPanelName() {
		return null;
	}

	@Override
	public String getSubCategory() {
		return null;
	}
}

