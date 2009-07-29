package org.exquisitus.server.action;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.exquisitus.client.model.valueobjects.UserVO;
import org.exquisitus.client.services.LoginAction;
import org.exquisitus.server.persistence.UserPersistence;
import org.exquisitus.server.persistence.UserPersistenceJAXB;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class LoginServiceHandler implements ActionHandler<LoginAction, UserVO>{
	
	private static Logger log = Logger.getLogger(LoginServiceHandler.class.getName());
	
	private UserPersistence userPersistence = null;
	
	private static List<UserVO> userlist = null;

	public LoginServiceHandler() {

		userPersistence = new UserPersistenceJAXB(); // use an AbstractFactory!!!!
	
		//ServletContext cxt = GreetingServiceImpl.getSc();
		//cxt.setAttribute("usrlst", new ArrayList<UserVO>());
		
		userlist = new ArrayList<UserVO>();
	}

	public static List<UserVO> getUserlist() {
		return userlist;
	}
	
	@Override
	public UserVO execute(LoginAction action, ExecutionContext cxt)
			throws ActionException {
		
		String username = action.getName();
		String password = action.getPassword();
		
		UserVO user = userPersistence.findUser(username, password);
		
		if (user == null)
			throw new ActionException("User does not exist or Bad Password");
		
		if (user != null)
		{
			synchronized (userlist) {

				userlist.add(user);
				log.info("User connected " + userlist.size());			
			}
		}
		
		return user;
	}

	@Override
	public Class<LoginAction> getActionType() {
		return LoginAction.class;
	}

	@Override
	public void rollback(LoginAction arg0, UserVO arg1, ExecutionContext arg2)
			throws ActionException {
		// no rollback
	}

}
