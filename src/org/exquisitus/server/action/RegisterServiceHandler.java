package org.exquisitus.server.action;

/**
 * ActionHandler server side implementation of Registration
 * 
 * @author muzero
 */

import java.util.logging.Logger;

import org.exquisitus.client.model.valueobjects.UserVO;
import org.exquisitus.client.services.RegisterAction;
import org.exquisitus.server.persistence.UserPersistence;
import org.exquisitus.server.persistence.UserPersistenceJAXB;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class RegisterServiceHandler implements
		ActionHandler<RegisterAction, UserVO> {
	
	private Logger log = Logger.getLogger(RegisterServiceHandler.class.getName());
	
	private UserPersistence userPersistence = null;
	
	public RegisterServiceHandler() {
		setUserPersistence(new UserPersistenceJAXB()); // change it, @Iniect please
	}
	
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

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
		
		log.info("user " + username + "registered");

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
