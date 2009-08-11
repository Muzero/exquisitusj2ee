package org.exquisitus.server.action;

import org.exquisitus.client.services.MessageAction;
import org.exquisitus.client.services.MessageResponse;
import org.exquisitus.server.BusinessDelegate;
import org.exquisitus.server.BusinessDelegateFactory;
import org.exquisitus.server.ejb3.MailEjbLocal;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.SimpleResult;

public class MessageServiceHandler implements
		ActionHandler<MessageAction, SimpleResult<String>> {

	public final static String TOEMAIL = "j2ee-experiments@googlegroups.com";
	public final static String CCEMAIL = null; //"davassi@yahoo.it";
	
	@Override
	public SimpleResult<String> execute(MessageAction msgAction,
			ExecutionContext arg1) throws ActionException {

		String username = msgAction.getUsername();
		String email = msgAction.getEmail();
		String type = msgAction.getType();
		String title = msgAction.getTitle();
		String humor = msgAction.getHumor();
		String message = msgAction.getMessage();

		StringBuffer sb = new StringBuffer();
		sb.append("Message from <b>");
		sb.append(username);
		sb.append("</b> email[ ");
		sb.append(email);
		sb.append(" ] title:' ");
		sb.append(title);
		sb.append(" '");
		
		StringBuffer sc = new StringBuffer(sb);
		sc.append("\n Type: " + type);
		sc.append("\n Humor: " + humor);
		sc.append("\n Message: " + message);

		System.out.println(sc.toString());
		
		String subject = "[EXQUISITUSJ2EE Message System] " + sb.toString();
		
		BusinessDelegate businessDelegate = BusinessDelegateFactory.getBusinessDelegate();
		MailEjbLocal mailEjb = businessDelegate.getLocalMailEJB3();

		if (mailEjb.sendMail(TOEMAIL, CCEMAIL, email, subject, sc.toString()) == false)
			throw new ActionException("Internal Server Mail Error");
		
		return new MessageResponse(sb.toString());		
	}

	@Override
	public Class<MessageAction> getActionType() {
		return MessageAction.class;
	}

	@Override
	public void rollback(MessageAction arg0, SimpleResult<String> arg1,
			ExecutionContext arg2) throws ActionException {
		// no rollback now
	}

}
