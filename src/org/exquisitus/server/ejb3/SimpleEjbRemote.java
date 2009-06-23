package org.exquisitus.server.ejb3;

import javax.ejb.Remote;

@Remote
public interface SimpleEjbRemote {

	public String reverseString(String str);
}
