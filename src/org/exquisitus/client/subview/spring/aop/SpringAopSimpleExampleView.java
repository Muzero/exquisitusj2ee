package org.exquisitus.client.subview.spring.aop;

import org.exquisitus.client.subview.AbstractSubPanelTemplate;
import org.exquisitus.client.subview.SubView;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.RowData;

@SubView(panelName = "Spring Simple AOP 1", 
		category = "Spring Example", 
		subCategory = "AOP")
public class SpringAopSimpleExampleView extends AbstractSubPanelTemplate {

	@Override
	public String getCategory() {
		return "Spring Example";
	}

	@Override
	public String getPanelName() {
		return "Spring Simple AOP 1";
	}

	@Override
	public String getSubCategory() {
		return "Spring AOP";
	}

	@Override
	public String getViewName() {
		return getPanelName();
	}

	@Override
	public void init() {
	
		ContentPanel panel = new ContentPanel();  
		panel.setHeading("Invoke Spring AOP");  
		//panel.setLayout(new RowLayout(Orientation.VERTICAL));  
		panel.setSize(400, 250);  
		panel.setFrame(true);  
		panel.setCollapsible(true);

		VerticalPanel vp = new VerticalPanel();

		/*inputStr = new TextField<String>();
		inputStr.setValue("Example...");
		inputStr.setFieldLabel("String to Reverse");

		resultStr = new TextField<String>();
		resultStr.setFieldLabel("Result");

		vp.add(inputStr);
		vp.add(btnReverse = new Button("Reverse the string!"));
		vp.add(resultStr);*/

		// btnReverse event defined into the controller
		panel.add(vp);
		
		Text title = new Text("EJB3 Stateless Session Bean Invocation 1");
		formatHeader(title.getText());
		//add(title,new RowData(1, -1, new Margins(4)));
		
		add(panel);

		new SpringAopSimpleExampleController(this);	
	}

	@Override
	public String[] getSourceCodeClasses() {
		// TODO Auto-generated method stub
		return new String[] {
				"org.exquisitus.client.subview.spring.aop.SpringAopSimpleExampleView",
				"org.exquisitus.client.subview.spring.aop.SpringAopSimpleExampleController"
		};
	}

}
