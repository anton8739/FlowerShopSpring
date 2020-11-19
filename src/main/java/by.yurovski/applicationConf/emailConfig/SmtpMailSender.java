package by.yurovski.applicationConf.emailConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Component
public class SmtpMailSender {

	@Autowired
	@Qualifier("javaMail")
	private JavaMailSender javaMailSender;
	@Autowired
	@Qualifier("mailTemplateEngine")
	private SpringTemplateEngine thymeleafTemplateEngine;


	public void sendHtmlMessage(String to, String subject, String body) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);
		
		javaMailSender.send(message);
		
		
	}


	public void sendMessageUsingThymeleafTemplate(
			String to, String subject, Map<String, Object> templateModel, String template)
			throws MessagingException {

		Context thymeleafContext = new Context();
		thymeleafContext.setVariables(templateModel);
		String htmlBody = thymeleafTemplateEngine.process(template, thymeleafContext);

		sendHtmlMessage(to, subject, htmlBody);
	}


}
