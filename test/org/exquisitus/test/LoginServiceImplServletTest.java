package org.exquisitus.test;

import org.apache.cactus.ServletTestCase;
import org.exquisitus.server.action.LoginServiceHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginServiceImplServletTest extends ServletTestCase {

	@Before
	public void setUp() throws Exception {
		// something this is an example
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() {
		LoginServiceHandler loginService = new LoginServiceHandler();
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

}
