package com.eniso.tama.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;

@Service
public interface MailService {
	public void sendmail(String email) throws AddressException, MessagingException, IOException;
}
