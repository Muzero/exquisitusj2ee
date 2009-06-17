package org.exquisitus.server.persistence;

import java.util.ArrayList;
import java.util.List;

import org.exquisitus.jaxb.generated.user.User;

public class UserPersistenceMock implements UserPersistence {

	private static List<User> mockusers = new ArrayList<User>();

	public UserPersistenceMock() {

		if (mockusers.isEmpty()) {
			User u1 = new User();
			u1.setUsername("Hey");
			u1.setEmail("email@email.it");
			u1.setPassword("nicepass");
			u1.setRole("");
			mockusers.add(u1);

			User u2 = new User();
			u2.setUsername("Pippo");
			u2.setEmail("topolino@toto.it");
			u2.setPassword("uffa");
			u2.setRole("");
			mockusers.add(u2);
		}
	}

	@Override
	public User findUser(String username, String password) {
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail("mock@mock.it");
		return user; // user always authenticated!
	}

	@Override
	public List<?> getUserList() {

		return mockusers;
	}

	@Override
	public void registerUser(String username, String password, String email) {

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);

		mockusers.add(user);
	}

}
