package org.exquisitus.client.subview.ejb3stateful;

import org.exquisitus.client.subview.AbstractSubPanelTemplate;
import org.exquisitus.client.subview.SubView;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.RowData;


@SubView(panelName = "EJB3 Stateful Ex1", 
		category = "EJB Example", 
		subCategory = "@Stateful")
public class Ejb3ShoppingCartView extends AbstractSubPanelTemplate {

	private Button btnReverse = null;
	private TextField<String> inputStr = null;
	private TextField<String> resultStr = null;

	public Ejb3ShoppingCartView() {}

	@Override
	public void init() {
		
		ContentPanel panel = new ContentPanel();  
		panel.setHeading("Shopping Cart");  
		//panel.setLayout(new RowLayout(Orientation.VERTICAL));  
		panel.setSize(400, 250);  
		panel.setFrame(true);  
		panel.setCollapsible(true);

		VerticalPanel vp = new VerticalPanel();

		inputStr = new TextField<String>();
		inputStr.setValue("Example...");
		inputStr.setFieldLabel("String to Reverse");

		resultStr = new TextField<String>();
		resultStr.setFieldLabel("Result");

		vp.add(inputStr);
		vp.add(btnReverse = new Button("Reverse the string!"));
		vp.add(resultStr);

		// btnReverse event defined into the controller
		panel.add(vp);
		
		Text title = new Text("EJB3 Stateful Session Bean Shopping Cart");
		add(title,new RowData(1, -1, new Margins(4)));
		
		add(panel);

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

	public Button getBtnReverse() {
		return btnReverse;
	}

	public TextField<String> getInputStr() {
		return inputStr;
	}

	public TextField<String> getResultStr() {

		return resultStr;
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
