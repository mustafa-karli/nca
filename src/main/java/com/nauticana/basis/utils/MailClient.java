package com.nauticana.basis.utils;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailClient {

	public static final String smtpGmailHost = "smtp.gmail.com";
	public static final int smtpGmailPortSSL = 465;
	public static final int smtpGmailPortTLS = 587;
	public static final String smtpYahooHost =  "smtp.mail.yahoo.com";
	public static final int smtpYahooPort = 25; 

	public void send(String mailSender,
					 String senderPassword,
					 String smtpHost,
					 int smtpPort,
					 String subject, 
					 String messageText,
					 String[] mailTO,
					 String[] mailCC,
					 String[] mailBCC,
					 String[] attachments) throws MessagingException, AddressException {
		
		Properties props = System.getProperties();
		props.put("mail.smtp.user", mailSender);
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", smtpPort);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		MyAuthenticator authentication = new MyAuthenticator(mailSender, senderPassword);
		Session session = Session.getDefaultInstance(props, authentication);
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(messageText);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		
		for (int i = 0; i < attachments.length; i++) {
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attachments[i]);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(attachments[i]);
			multipart.addBodyPart(messageBodyPart);
		}
		
		message.setContent(multipart);
		message.setSubject(subject);
		message.setFrom(new InternetAddress(mailSender));

		for (int i = 0; i < mailTO.length; i++) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTO[i]));
		}
		
		for (int i = 0; i < mailCC.length; i++) {
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(mailCC[i]));
		}
		
		for (int i = 0; i < mailBCC.length; i++) {
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(mailBCC[i]));
		}

		Transport transport = session.getTransport("smtps");
		transport.connect(smtpHost, smtpPort, mailSender, senderPassword);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}

	private class MyAuthenticator extends javax.mail.Authenticator {
		String user;
		String password;

		public MyAuthenticator(String user, String password) {
			this.user = user;
			this.password = password;
		}

		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			return new javax.mail.PasswordAuthentication(user, password);
		}
	}
}
