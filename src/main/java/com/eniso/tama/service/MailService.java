package com.eniso.tama.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.eniso.tama.entity.Participant;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface MailService {
    public void sendmail(String email) throws AddressException, MessagingException, IOException;

    public void sendParticipantValidationMail(Participant participant) throws MessagingException;
    public void sendmailAlert(long id) throws AddressException, MessagingException, IOException;
}
