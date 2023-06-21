package lib.commons.notification;

import java.util.Base64;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/***
 * Send mail notification using outlook smtp standard, with standard RPA mails
 * 
 * @author Kris
 *
 */

public class MailNotification {

	private static final Logger log = LoggerFactory.getLogger(MailNotification.class);
	private static final String BREAK_LINE = "<br />";

	/***
	 * 
	 * send mail using smpt, receiving the needed data as parameters
	 * 
	 * @param robotName
	 * @param messageContent
	 * @param executionStatus
	 * @param mailTo
	 * @param ccEmails
	 */
	public static void sendMessage(String robotName, String messageContent, String executionStatus, String mailTo, List<String> ccEmails) {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp-mail.outlook.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		
		try {
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
							"kris.rpa@gmail.com",
							new String(Base64.getDecoder().decode(System.getenv("Pass")))
					);
				}
			};
			
			
			MimeMessage message = new MimeMessage(Session.getInstance(properties, auth));
			
			message.setFrom(new InternetAddress("kris.rpa@gmail.com", "SQUAD RPA"));
			message.setSubject("RPA - "+ robotName +" - "+ executionStatus);
			message.setContent(messageContent.toString().replace("BREAK_LINE", BREAK_LINE), "text/html;charset=UTF-8");
			message.setRecipients(Message.RecipientType.TO, mailTo);
			
			if(!ccEmails.isEmpty()) {
				ccEmails.forEach(email -> {
					try {
						message.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
					} catch (MessagingException e) {
						log.error(e.getMessage());
					}
				});
			}
			
			Transport.send(message);
			
			log.info("Mail message sent with success!");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
