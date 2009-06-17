package org.exquisitus.server.persistence;

import java.util.List;

import org.exquisitus.jaxb.generated.user.User;

public class UserPersistenceHibernate implements UserPersistence {

	@Override
	public User findUser(String user, String password) {
		// TODO
		return null;
	
	}

	@Override
	public void registerUser(String user, String password, String email) {
		

	}

	@Override
	public List getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

}
