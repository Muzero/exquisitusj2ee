package org.exquisitus.server.persistence;

import java.util.List;

import org.exquisitus.client.model.UserVO;

public interface UserPersistence {

	public UserVO findUser(String user, String password);
	
	public void registerUser(String user, String password, String email) throws Exception;
	
	public List<UserVO> getUserList();
}
