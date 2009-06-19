package org.exquisitus.client.subview.ejb3example1;

import org.exquisitus.client.subview.AbstractSubPanelTemplate;
import org.exquisitus.client.subview.SubView;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

@SubView( panelName="EJB3 Stateless Example 1",
		  category="EJB Example",
		  subCategory="@Stateless" )
public class Ejb3Example1View extends AbstractSubPanelTemplate {
	
	@Override
	public void init() {
		
			new Ejb3Example1Controller(this);
		
			setHeading(getViewName());
			setLayout(new FitLayout());
			setFrame(true);
	
			VerticalPanel vp = new VerticalPanel();

			TextField<String> inputStr = new TextField<String>();
			inputStr.setFieldLabel("String to Reverse");
			
			TextField<String> resultStr = new TextField<String>();
			resultStr.setFieldLabel("Result");
			
			vp.add(inputStr);
			vp.add(new Button("Reverse the string!"));
			vp.add(resultStr);
			
			add(vp);
	}
	
	@Override
	public void dispose() {
		//
	}

	@Override
	public String getViewName() {
		return getClass().getName();
	}
}
