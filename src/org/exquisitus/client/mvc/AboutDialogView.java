package org.exquisitus.client.mvc;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class AboutDialogView extends View {

	private Dialog aboutDialog = null;
	
	public AboutDialogView(Controller controller) {
		super(controller);	
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		
		aboutDialog = new Dialog();
		aboutDialog.setSize(600, 400);
		aboutDialog.setLayout(new FitLayout());
		
		HtmlContainer htmlCont = new HtmlContainer();
		
		//, selector)
		
		aboutDialog.add(new Button("CIAO"));
	}
	
	

	@Override
	protected void handleEvent(AppEvent event) {
		aboutDialog.show();
	}

	public void showDialog() {
		
		
	}
	
	

}
