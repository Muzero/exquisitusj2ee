package org.exquisitus.client.subview.ejb3stateful;

import org.exquisitus.client.subview.AbstractSubPanelTemplate;
import org.exquisitus.client.subview.SubView;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

@SubView(panelName = "EJB3 Stateful Ex1", 
		category = "EJB Example", 
		subCategory = "@Stateful")
public class Ejb3ShoppingCartView extends AbstractSubPanelTemplate {

	private TreePanel<ModelData> tree;

	public Ejb3ShoppingCartView() {}

	@Override
	public void init() {
		
		LayoutContainer container = new LayoutContainer();  
		container.setStyleAttribute("margin", "10px");  
		container.setSize(650, 300);  
		container.setBorders(true);  
		container.setLayout(new BorderLayout()); 
		
		ContentPanel est = new ContentPanel();  
		est.setHeading("My Shopping Cart");  
				
		Text title = new Text("Happy Shopping with EJB3 Stateful SessionBean!");
		add(title,new RowData(1, -1, new Margins(4)));
		
		TreeStore<ModelData> treeStore = new TreeStore<ModelData>();  
		   
		tree = new TreePanel<ModelData>(treeStore);  
		tree.setDisplayProperty("name");  
		   
		tree.getStyle().setLeafIcon(IconHelper.create("user"));  
		tree.getStyle().setNodeCloseIcon(IconHelper.create("icon-album"));  
		tree.getStyle().setNodeOpenIcon(IconHelper.create("icon-album"));  
		est.add(tree);  
		   
		//treeStore.add(createAlbum("Album 1"), false);  
		tree.setLeaf(treeStore.getRootItems().get(0), false); 
		
		
		add(container);

		new Ejb3ShoppingCartController(this);		
	}

	@Override
	public void dispose() {
		//
	}

	@Override
	public String getViewName() {
		return getClass().getName();
	}

	@Override
	public String[] getSourceCodeClasses() {
	
		return new String[] {
				"org.exquisitus.server.ServiceLocator"
				,"org.exquisitus.client.mvc.ShowCaseView"
				,"org.exquisitus.client.mvc.AppView"
				,"org.exquisitus.client.mvc.LoginDialogController"
		};
	}

	@Override @Deprecated
	public String getCategory() {
		return "EJB Example";
	}

	@Override @Deprecated
	public String getSubCategory() {
		return "EJB3 @Stateful";
	}
	
	@Override @Deprecated
	public String getPanelName() {
		return "Shopping Cart";
	}
}
