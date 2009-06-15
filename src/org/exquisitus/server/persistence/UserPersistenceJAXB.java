package org.exquisitus.server.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
	public User autenticateUser(String username, String password) {
		
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
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
					return user;
		}
		
		// otherwise...
		return null;
	}

	@Override
	public void registerUser(String user, String password, String email) throws Exception {
		
		Unmarshaller un = cxt.createUnmarshaller();		
		
		Userslist userslist = (Userslist) un.unmarshal(xmlfile);
		
		List<User> ulist = userslist.getUser();
		
		User p = objFactory.createUser();
		
		p.setUsername(user);
		p.setPassword(password);
		p.setEmail(email);
		p.setRole("user");
		
		ulist.add(p);
		
		Marshaller marshaller = cxt.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(ulist, xmlfile);
		
		log.info("User " + user + "[" + email + "] Registered at " + Calendar.getInstance().getTimeZone());
		
	}

	@Override
	public List getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

}
