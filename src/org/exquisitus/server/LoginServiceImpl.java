package org.exquisitus.server;

import java.util.HashMap;
import java.util.Map;

import org.exquisitus.client.services.LoginService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	private Map<String,String> mokmap = new HashMap<String,String>();
	
	@Override
	public String login(String user, String password) {  // TODO it's only a mock, replace with a REAL login system
		
		return password.equals(mokmap.get("user")) ? "FAILED" : "SUCCESS";
	}

	@Override
	public String register(String user, String password, String email) {
		
		mokmap.put(user, password);
		
		return user + " " + password + " " + email;
	}

}
