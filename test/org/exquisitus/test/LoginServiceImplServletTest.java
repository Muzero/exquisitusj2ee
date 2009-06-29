package org.exquisitus.test;

import org.apache.cactus.ServletTestCase;
import org.exquisitus.server.LoginServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginServiceImplServletTest extends ServletTestCase {

	@Before
	public void setUp() throws Exception {
		// something
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() {
		LoginServiceImpl loginService = new LoginServiceImpl();
		loginService.login("USER", "PASSWORD");
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

}
