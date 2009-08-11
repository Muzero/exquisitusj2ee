package org.exquisitus.server.ejb3;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.exquisitus.server.ejb3.MailEjbLocal;

public @Stateless class MailEjbBean implements MailEjbLocal {

	@Override
	public boolean sendMail(String to, String cc,
			String from, String subject, String body) {
		try {
			//java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

			Properties props = System.getProperties();
			
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.starttls.enable","true");
			props.put("mail.smtp.auth", "false");
			props.put("mail.smtp.host", SMTP_SERVER);
			
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getDefaultInstance(props, null);// auth);
			session.setDebug(false);
			
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			
			if (cc != null)
				msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));
			
			msg.setSubject(subject);
			msg.setText(body);
			
			msg.setHeader("X-Mailer", "http://www.exquisitusj2ee.tk");
			msg.setSentDate(new java.util.Date());
			msg.setContent(body, "text/plain");

			Transport.send(msg);
			System.out.println("Message sent OK.");
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	private static class SMTPAuthenticator extends Authenticator {
		 
		public PasswordAuthentication getPasswordAuthentication() {
			String username = "";
			String password = "";
			return new PasswordAuthentication(username, password);
		}
	}
}
