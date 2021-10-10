package com.eniso.tama.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.eniso.tama.entity.Participant;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    public void sendmail(String email) throws AddressException, MessagingException, IOException;

    public void sendParticipantValidationMail(Participant participant) throws MessagingException;
}
