package org.exquisitus.server.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.exquisitus.client.model.valueobjects.UserVO;
import org.exquisitus.jaxb.generated.user.ObjectFactory;
import org.exquisitus.jaxb.generated.user.User;
import org.exquisitus.jaxb.generated.user.Userslist;

public class UserPersistenceJAXB implements UserPersistence {
	
	private static final Logger log = Logger.getLogger(UserPersistenceJAXB.class.getName());

	private JAXBContext cxt = null;
	
	private ObjectFactory objFactory = new ObjectFactory();

	private File xmlfile = null;

	public UserPersistenceJAXB() {
	
		try {
			xmlfile = (new File("xml/users.xml"));
	
			cxt = JAXBContext.newInstance("org.exquisitus.jaxb.generated.user");
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public UserVO findUser(String username, String password) {
		
		// TODO improve autentication!
		
		Unmarshaller un = null;
		Userslist userslist = null;
		
		try {
			un = cxt.createUnmarshaller();
			userslist = (Userslist) un.unmarshal(xmlfile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}		
		
		
		List<User> ulist = userslist.getUser();
		
		for (User user : ulist) {
			if (user.getUsername().trim().toLowerCase().equals(username.trim().toLowerCase()) 
					&& user.getPassword().trim().toLowerCase().equals(password.trim().toLowerCase()))
					return convert2VO(user);
		}
		
		// otherwise...
		return null;
	}

	@Override
	public void registerUser(String user, String password, String email) throws Exception {
		
		Unmarshaller un = cxt.createUnmarshaller();		
		
		xmlfile = (new File("xml/users.xml"));
		Userslist userslist = (Userslist) un.unmarshal(xmlfile);
		
		User newUser = objFactory.createUser();
		
		newUser.setUsername(user);
		newUser.setPassword(password);
		newUser.setEmail(email);
		newUser.setRole("user");
		newUser.setAuth(false);
		
		userslist.getUser().add(newUser);
		
		Marshaller marshaller = cxt.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(userslist, xmlfile);
		
		log.info("User " + user + "[" + email + "] Registered ");
		
	}

	@Override
	public List<UserVO> getUserList() {
		Unmarshaller un = null;
		Userslist userslist = null;
		
		try {
			un = cxt.createUnmarshaller();
			userslist = (Userslist) un.unmarshal(xmlfile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}		
		
		List<User> xmllist = userslist.getUser();
		
		List<UserVO> uservolist = new ArrayList<UserVO>();
		
		for (User u : xmllist)
			uservolist.add(convert2VO(u));
		
		return uservolist;
	}

	
	private static UserVO convert2VO(User xmluser)  {
		UserVO us = new UserVO();
		
		us.setUsername(xmluser.getUsername());
		us.setEmail(xmluser.getEmail());
		us.setPassword(xmluser.getPassword());
		us.setRole(xmluser.getRole());
		us.setAuth(xmluser.isAuth());
		
		return us;
	}

}
