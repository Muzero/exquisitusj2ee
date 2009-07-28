package org.exquisitus.server.persistence;

import java.util.ArrayList;
import java.util.List;

import org.exquisitus.client.model.valueobjects.UserVO;

public class UserPersistenceMock implements UserPersistence {

	private static List<UserVO> mockusers = new ArrayList<UserVO>();

	public UserPersistenceMock() {

		if (mockusers.isEmpty()) {
			UserVO u1 = new UserVO();
			u1.setUsername("Hey");
			u1.setEmail("email@email.it");
			u1.setPassword("nicepass");
			u1.setRole("");
			mockusers.add(u1);

			UserVO u2 = new UserVO();
			u2.setUsername("Pippo");
			u2.setEmail("topolino@toto.it");
			u2.setPassword("uffa");
			u2.setRole("");
			mockusers.add(u2);
		}
	}

	@Override
	public UserVO findUser(String username, String password) {
		
		UserVO user = new UserVO();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail("mock@mock.it");
		return user; // user always authenticated!
	}

	@Override
	public List<UserVO> getUserList() {

		return mockusers;
	}

	@Override
	public void registerUser(String username, String password, String email) {

		UserVO user = new UserVO();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setAuth(false);
		user.setRole("");

		mockusers.add(user);
	}

}
