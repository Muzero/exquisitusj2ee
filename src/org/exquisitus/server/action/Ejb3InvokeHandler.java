package org.exquisitus.server.action;

import java.util.logging.Logger;

import org.exquisitus.client.subview.ejb3example1.Ejb3InvokeAction;
import org.exquisitus.client.subview.ejb3example1.Ejb3InvokeResult;
import org.exquisitus.server.BusinessDelegate;
import org.exquisitus.server.BusinessDelegateFactory;
import org.exquisitus.server.ejb3.SimpleEjbLocal;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class Ejb3InvokeHandler implements ActionHandler<Ejb3InvokeAction, Ejb3InvokeResult> {
	
	private static Logger log = Logger.getLogger(Ejb3InvokeHandler.class.getName());
	
	public Ejb3InvokeHandler() {}

	@Override
	public Ejb3InvokeResult execute(Ejb3InvokeAction action, ExecutionContext arg1)
			throws ActionException {
		
		String str = action.getStrParam();
		
		if (str == null) throw new ActionException("Null Input Error");
		
		BusinessDelegate businessDelegate = BusinessDelegateFactory.getBusinessDelegate();
		SimpleEjbLocal ejb = businessDelegate.getLocalSimpleEJB();

		String result = ejb.reverseString(str);
		
		log.info("inforesult: " + result);
		
		// Thanks business delegate! ;-)
		return new Ejb3InvokeResult( result );
	}

	@Override
	public Class<Ejb3InvokeAction> getActionType() {
		
		return Ejb3InvokeAction.class;
	}

	@Override
	public void rollback(Ejb3InvokeAction arg0, Ejb3InvokeResult arg1,
			ExecutionContext arg2) throws ActionException {
		// no rollback
		
	}

}
