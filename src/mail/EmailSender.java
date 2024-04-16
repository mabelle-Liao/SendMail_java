package mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
* Java Program to send text mail using default SMTP server and without authentication.
* You need mail.jar, smtp.jar and activation.jar to run this program.
*
* @author Javin Paul
*/

public class EmailSender{
	// -------------- Basic Info -------------- //
	private String userMail = "miniokla@gmail.com";   
	private String pwd = "gpdpewvyialouhbv";    
	private String receiver = "anda_lai@foxlink.com";            
	private String subject = "Test send mail (Mabelle)";         
	private String txt = "I am currently working as an  automation tester at HCL where I test internal software applications as well as write automation code on a daily basis.";       

	
	
	public void SendMail() {
		Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.host", "smtp.gmail.com");
        
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");  // �ݭn����
        prop.put("mail.smtp.socketFactory.class", "com.sun.mail.util.MailSSLSocketFactory"); // ��@ssl�s�u
        prop.put("mail.smtp.socketFactory.port", "465");
        
        // �z�L�ΦW���O���o
        Session session=Session.getDefaultInstance(prop, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(userMail,pwd);
			}});
        MimeMessage message = new MimeMessage(session);
        
        try {
        	// �H���
			message.setSender(new InternetAddress(userMail));
			// �����
			message.setRecipient(RecipientType.TO, new InternetAddress(receiver));
			// ���D
			message.setSubject(subject);
			// ���e
			message.setContent(txt, "text/html;charset=UTF-8");
			
			Transport transport= session.getTransport();
			transport.send(message);
			transport.close();
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	

}