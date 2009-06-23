package org.exquisitus.server.ejb3;

import javax.ejb.Stateless;

@Stateless
public class SimpleEjbBean implements SimpleEjbLocal, SimpleEjbRemote {

	@Override
	public String reverseString(String str) {
		
		return new StringBuffer(str).reverse().toString();
	}

}
