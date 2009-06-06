package org.exquisitus.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.exquisitus.client.model.User;
import org.exquisitus.client.services.LoginService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	private Logger log = Logger.getLogger(LoginServiceImpl.class.getName());

	private static final String SESSIONUSER = "CURRENTUSER";
	private static final String APPLICATIONUSERLIST = "APPLICATIONUSERLIST";
	
	private Map<String,String> mokmap = new HashMap<String,String>();
	
	private List<User> currentuserlist = null;
	private HttpSession session = null;
	
	public LoginServiceImpl() {
			
		ServletContext cxt = this.getServletContext();
		
		currentuserlist = (List<User>) cxt.getAttribute(APPLICATIONUSERLIST);
		
		if (currentuserlist == null)
		{
			cxt.setAttribute(APPLICATIONUSERLIST, new ArrayList<User>());
			currentuserlist = (List<User>) cxt.getAttribute(APPLICATIONUSERLIST);	
		}
	}
	
	@Override
	public String login(String username, String password) {  // TODO it's only a mock, replace with a REAL login system
		
		session = this.getThreadLocalRequest().getSession();
			
		log.info(session.getId());
		
		User user = (User) session.getAttribute(SESSIONUSER);
		
		if (user == null)
		{
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
			
			session.setAttribute(SESSIONUSER, user);
		}
		
		currentuserlist.add(user);
		
		log.info("User connected " + currentuserlist.size());
		
		return user.getUsername() + " " + user.getPassword();
	}

	@Override
	public String register(String user, String password, String email) {
			
		mokmap.put(user, password);
		
		return user + " " + password + " " + email;
	}

}
