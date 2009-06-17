package org.exquisitus.server.persistence;

import java.util.List;

import org.exquisitus.jaxb.generated.user.User;

public interface UserPersistence {

	public User findUser(String user, String password);
	
	public void registerUser(String user, String password, String email) throws Exception;
	
	public List getUserList();
}
