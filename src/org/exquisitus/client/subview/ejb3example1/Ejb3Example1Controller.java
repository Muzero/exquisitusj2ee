package org.exquisitus.client.subview.ejb3example1;

import net.customware.gwt.dispatch.client.DispatchAsync;

import org.exquisitus.client.ExquisitusJ2EE;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class Ejb3Example1Controller {
		
	private DispatchAsync dispatchAsync = null;

	private Ejb3Example1View view = null;
	
	public Ejb3Example1Controller(Ejb3Example1View view) { 
		
		this.view = view;
		
		init();
		dispatchAsync = Registry.get(ExquisitusJ2EE.ACTIONDISPATCHER);
	}
	
	public void init() {

		view.getBtnReverse().addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				Info.display("Ejb3","Ejb3 Invoke!");

				final long init = System.currentTimeMillis();
				
				final String strToReverse = view.getInputStr().getValue();
				
				Ejb3InvokeAction action = new Ejb3InvokeAction();
				action.setStrParam(strToReverse);
		
				dispatchAsync.execute(action, new SimpleAsyncCallBack<Ejb3InvokeResult>() {

					@Override
					public void onSuccess(Ejb3InvokeResult result) {
						
						long stop = System.currentTimeMillis();
						
						view.getResultStr().setValue(result.getResult());		
						Info.display("" + strToReverse,result.getResult());
						
						StringBuffer sb = new StringBuffer()
							.append("RPC call takes ")
							.append(stop-init)
							.append(" milliseconds");
						
						view.getResultText().setText(sb.toString());
						
						view.getResultText().setVisible(true);
					}			 
					
				});
				
				view.layout();
				action = null;
			}
			
		});

		
	}

}

abstract class SimpleAsyncCallBack<T> implements AsyncCallback<T> {

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