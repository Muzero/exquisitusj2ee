package org.exquisitus.server.action;

import org.exquisitus.client.services.MessageAction;
import org.exquisitus.client.services.MessageResponse;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.SimpleResult;


public class MessageServiceHandler implements ActionHandler<MessageAction, SimpleResult<String>>{
	
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
