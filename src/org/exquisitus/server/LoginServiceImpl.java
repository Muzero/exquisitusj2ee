package org.exquisitus.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.exquisitus.client.model.valueobjects.UserVO;
import org.exquisitus.jaxb.generated.user.User;
import org.exquisitus.server.persistence.UserPersistence;
import org.exquisitus.server.persistence.UserPersistenceJAXB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Deprecated
public class LoginServiceImpl extends RemoteServiceServlet {

	private Logger log = Logger.getLogger(LoginServiceImpl.class.getName());

	private static final String SESSIONUSER = "CURRENTUSER";
	private static final String APPLICATIONUSERLIST = "APPLICATIONUSERLIST";

	private List<UserVO> currentuserlist = null;
	private HttpSession session = null;

	private UserPersistence userPersistence = null;

	public LoginServiceImpl() {
	}

	@Override
	public void init() throws ServletException {
		super.init();

		ServletContext cxt = this.getServletContext();

		currentuserlist = (List<UserVO>) cxt.getAttribute(APPLICATIONUSERLIST);

		if (currentuserlist == null) {
			cxt.setAttribute(APPLICATIONUSERLIST, new ArrayList<User>());
			
			currentuserlist = (List<UserVO>) cxt.getAttribute(APPLICATIONUSERLIST);
		}

		userPersistence = new UserPersistenceJAXB(); // use an
													 // AbstractFactory!!!!
	}

	public String login(String username, String password) { // TODO it's only a
															// mock, replace
															// with a REAL login
															// system
		session = this.getThreadLocalRequest().getSession();

		log.info(session.getId());
	
		UserVO user = userPersistence.findUser(username, password);
		// TODO: extract login logic here

		if (user != null)
		{
			if (session.getAttribute(SESSIONUSER) != null)
				session.removeAttribute(SESSIONUSER);

			session.setAttribute(SESSIONUSER, user);
			
			currentuserlist.add(user);
			
			log.info("User connected " + currentuserlist.size());
		}
		
		return user != null ? user.getUsername() : null;
	}

	
	public String register(String username, String password, String email) {

		session = this.getThreadLocalRequest().getSession();

		// session.removeAttribute(SESSIONUSER);

		// log.info(session.getId());
		
		try {
			userPersistence.registerUser(username, password, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// for now it's ok
		userPersistence.findUser(username, password);

		return username;
	}

}
