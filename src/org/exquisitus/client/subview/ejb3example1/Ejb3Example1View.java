package org.exquisitus.client.subview.ejb3example1;

import org.exquisitus.client.subview.AbstractSubPanelTemplate;
import org.exquisitus.client.subview.ISubPanelInterface;
import org.exquisitus.client.subview.SubView;

import com.extjs.gxt.ui.client.widget.button.Button;

@SubView( panelName="EJB3 Stateless Example 1",
		  category="EJB Example",
		  subCategory="@Stateless" )
public class Ejb3Example1View extends AbstractSubPanelTemplate implements ISubPanelInterface {

	@Override
	public void init() {
		
		add(new Button("CIAO"));
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
	public void refresh() {
	
	}

}
