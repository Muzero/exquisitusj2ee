package org.exquisitus.server.action;

import org.exquisitus.client.model.valueobjects.UserVO;
import org.exquisitus.client.services.RegisterAction;
import org.exquisitus.server.persistence.UserPersistence;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class RegisterServiceHandler implements
		ActionHandler<RegisterAction, UserVO> {
	
	private UserPersistence userPersistence = null;

	@Override
	public UserVO execute(RegisterAction registerAction, ExecutionContext arg1)
			throws ActionException {

		String username = registerAction.getName();
		String password = registerAction.getPass();
		String email = registerAction.getEmail();
		
		try {
			userPersistence.registerUser(username, password, email);
		} catch (Exception e) {
			throw new ActionException(e.getMessage());
		}

		// for now it's ok
		return userPersistence.findUser(username, password);
	}

	@Override
	public Class<RegisterAction> getActionType() {
		return RegisterAction.class;
	}

	@Override
	public void rollback(RegisterAction arg0, UserVO arg1, ExecutionContext arg2)
			throws ActionException {
		// null
	}

}
