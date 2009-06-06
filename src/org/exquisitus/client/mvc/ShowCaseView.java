package org.exquisitus.client.mvc;

import java.util.HashMap;
import java.util.Map;

import org.exquisitus.client.ApplicationEvents;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreeEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;

public class ShowCaseView extends View {

	private static final int XWIN = 1024;
	private static final int YWIN = 600;

	private Window mainwindow = null;

	private ContentPanel currentPanel = null;

	private Map<String, ContentPanel> cachepanelmap = null;
	private BorderLayoutData centerData;

	public ShowCaseView(Controller controller) {
		super(controller);
	}

	private void asyncLoadPanels() { // FIXME this is only a stub, the item must
										// be loaded from server
		cachepanelmap.put("Item 0", createMockPanel("A first Panel..."));
		cachepanelmap.put("Item 1", createMockPanel("A second Panel..."));
		cachepanelmap.put("Item 2", createMockPanel("A third Panel..."));
		cachepanelmap.put("Item 3", createMockPanel("A 4 Panel..."));
	}

	private ContentPanel createMockPanel(String strdes) {
		ContentPanel panel = new ContentPanel();
		panel.setLayout(new FitLayout());
		panel.setHeading("PANEL " + strdes);
		panel.addText("strdes");
		panel.addText("Panel " + strdes + " now showing!");

		return panel;
	}

	@Override
	protected void initialize() {
		super.initialize();

		cachepanelmap = new HashMap<String, ContentPanel>();

		asyncLoadPanels();

		currentPanel = cachepanelmap.get("Item 0"); // replace with
													// createDefaultPanel();

		mainwindow = new Window();
		mainwindow.setSize(XWIN, YWIN);
		mainwindow.setModal(false);
		mainwindow.setHeading("ExquisitusJ2EE Showcase");
		mainwindow.setLayout(new BorderLayout());
		mainwindow.hide();

		ContentPanel west = createMenuPanel();

		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 250);
		westData.setSplit(false);
		westData.setCollapsible(false);

		centerData = new BorderLayoutData(LayoutRegion.CENTER);

		mainwindow.add(west, westData);
		mainwindow.add(currentPanel, centerData);
	}

	private ContentPanel createMenuPanel() {
		ContentPanel panel = new ContentPanel();
		panel.setFrame(false);
		panel.setLayout(new FitLayout());

		Tree tree = new Tree();
		// create the TREE from a service call that load the examples from
		// server!!! :-) TODO FIXME

		TreeItem item = new TreeItem("ShowCase");
		item.setLeaf(false);
		tree.getRootItem().add(item);

		tree.addListener(Events.OnClick, new Listener<TreeEvent>() {

			public void handleEvent(TreeEvent be) {

				String viewSelected = be.getItem().getText();
				Dispatcher.forwardEvent(ApplicationEvents.SelectSubViewEvent,
						viewSelected);
			}
		});

		for (int i = 0; i < 20; i++) {
			TreeItem sub = new TreeItem("Item " + (i));
			sub.setLeaf(true);

			item.add(sub);
		}

		tree.setSelectionMode(SelectionMode.SINGLE);
		tree.expandAll();

		panel.add(tree);
		return panel;
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

		mainwindow.remove(currentPanel);

		String selectedPanel = event.getData().toString();
		currentPanel = cachepanelmap.get(selectedPanel) == null ? getErrorPanel(selectedPanel)
				: cachepanelmap.get(selectedPanel);

		// ISubPanelInterface subpanel = (ISubPanelInterface)currentPanel;
		// subpanel.refresh();

		mainwindow.add(currentPanel, centerData);
		mainwindow.layout();
	}

	private ContentPanel getErrorPanel(String selectedPanel) {

		ContentPanel panel = new ContentPanel();
		panel.setLayout(new FitLayout());
		panel.setHeading("ERROR");
		panel.addText("INTERNAL ERROR");
		panel.addText("Panel " + selectedPanel + "does not exist!");

		return panel;
	}

}
