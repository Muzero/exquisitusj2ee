package org.exquisitus.client.subview.ejb3example1;

import org.exquisitus.client.services.EJB3ProxyService;
import org.exquisitus.client.services.EJB3ProxyServiceAsync;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class Ejb3Example1Controller {
	
public static final String LOGINMOCKSERVICE = "LOGIN";
	
	private final EJB3ProxyServiceAsync ejbProxyService = (EJB3ProxyServiceAsync) GWT.create(EJB3ProxyService.class);
	
	private Ejb3Example1View view = null;
	
	public Ejb3Example1Controller(Ejb3Example1View view) { 
		
		this.view = view;
		
		init();
	}
	
	public void init() {
		
		view.getBtnReverse().addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				Info.display("Ejb3","Ejb3 Invoke!");
				
				final String strToReverse = view.getInputStr().getValue();
				
				ejbProxyService.EJB3StringReverse(strToReverse, new SimpleCallBack(){

					@Override
					public void onSuccess(String result) {
						Info.display("" + strToReverse,result);
						view.getResultStr().setValue(result);						
					}
				});
				
				view.layout();
				
			}
			
		});

		
		
	}

}

abstract class SimpleCallBack implements AsyncCallback<String> {

	@Override
	public void onFailure(Throwable caught) {
		MessageBox mb = new MessageBox();
		mb.setMessage("Error... " + caught.getMessage() + "");
		mb.setTitle("ERROR");
		mb.setModal(true);
		mb.setIcon(MessageBox.ERROR);
		mb.show();	
	}	
}