package com.eniso.tama.service;

import java.io.UnsupportedEncodingException;
import java.util.function.Function;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.eniso.tama.dto.Notification;
import com.eniso.tama.entity.MailTemplate;
import com.eniso.tama.repository.MailTemplateRepository;
import com.eniso.tama.util.TemplateFormatter;

@Service
public class MailTemplateServiceImpl implements MailTemplateService {
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
   MailTemplateRepository mailTemplateRepository;
	
	@Override
	public MailTemplate findMailTemplate(String templateId) {
		return mailTemplateRepository.findById(templateId).get();
	}

	@Override
	public void sendMailUsingTemplate(Notification notif) {
		Function<String, String> vars = (v) -> {
			switch (v) {
			case "login":
				return notif.getLogin();
			 case "password": return notif.getPassword();
			}
			return null;
		};

		System.out.println(vars);

		send(notif.getFrom(), notif.getTo(),"userValidation", vars);
		
	}


	private void send(String from, String to, String templateId, Function<String, String> vars) {
		MailTemplate t = findMailTemplate(templateId);
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true , "UTF-8");
			
			mimeMessageHelper.setSubject(TemplateFormatter.format(t.getSubject(), vars));
			mimeMessageHelper.setFrom(new InternetAddress(from, "Tama-Académie"));
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setText(TemplateFormatter.format(t.getBody(), vars));
			// emailRepo.save(mail);
			mailSender.send(mimeMessageHelper.getMimeMessage());

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
