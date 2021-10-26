package com.eniso.tama.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.web.bind.annotation.RequestParam;

import com.eniso.tama.entity.Trainer;

public interface TrainerService {
	public List<Trainer> findAll();

	public Trainer findById(long theId);

	public void save(Trainer thetrainer);

	public Trainer updateTrainer(Trainer theTrainer);

	public void deleteById(long theId);

	public void sendValidationMail(Trainer t) throws AddressException, MessagingException, IOException;

	public void validateTrainer(long id) throws AddressException, MessagingException, IOException;
	
	public Trainer refuseTrainer(Trainer trainer) ;

	
    public List<Trainer> findTrainersBySpecialization(String specialization);

}