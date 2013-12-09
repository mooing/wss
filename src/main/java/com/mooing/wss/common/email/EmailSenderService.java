package com.mooing.wss.common.email;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mooing.wss.common.exception.ServiceException;
import com.mooing.wss.common.util.Configuration;

/**
 * @version $id$
 *
 */
@Service
public class EmailSenderService {
	private JavaMailSenderImpl mailSender;

	@Autowired
	public void setMailSender(JavaMailSenderImpl mailSender){
		this.mailSender = mailSender;
	}
	
	public void sendMail(String from, String[] to, String subject, String body) throws ServiceException {

		Configuration configuration = Configuration.getInstance("component.properties");
		String test_addr = configuration.getProperty("email_test_addr");
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		try {
			helper.setFrom(from);
			if (test_addr != null)
				helper.setTo(test_addr);
			else
				helper.setTo(to);
			mimeMessage.setSubject(subject, "UTF-8");
			mimeMessage.setContent(body, "text/html; charset=\"UTF-8\"");
			mimeMessage.setHeader("Content-Type", "text/html; charset=\"UTF-8\"");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new ServiceException("Send mail failed: from=" + from + ",to=" + to, e);
		}

	}
	
}
