package org.exquisitus.server.ejb3;

import javax.ejb.Local;

@Local
public interface MailEjbLocal {

	public static final String SMTP_SERVER = "localhost"; // slicehost james server;
	
	public boolean sendMail(String to, String cc, String from, String subject, String body);
}
