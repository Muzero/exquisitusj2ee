package org.exquisitus.server.ejb3;

import javax.ejb.Local;

@Local
public interface SimpleEjbLocal {

	public String reverseString(String str);
}
