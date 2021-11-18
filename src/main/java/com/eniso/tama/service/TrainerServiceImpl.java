package com.eniso.tama.service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.entity.User;
import com.eniso.tama.helpers.RandomPasswordGenerator;
import com.eniso.tama.repository.TrainerRepository;
import com.eniso.tama.repository.UserRepository;
import com.eniso.tama.repository.SessionRepository;

@Service
@ComponentScan(basePackageClasses = TrainerRepository.class)
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;

	@Autowired
	private MailTemplateService mailtemplateservice;

	@Value("${spring.mail.username}")
	private String email;

	@Value("${password}")
	private String password;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RandomPasswordGenerator randomPassword;

	@Autowired
	SessionService sessionService;

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	public TrainerServiceImpl() {
	}

	public TrainerServiceImpl(TrainerRepository theTrainerRepository) {
		trainerRepository = theTrainerRepository;
	}

	@Override
	public List<Trainer> findAll() {
		return trainerRepository.findAll();
	}

	@Override
	public Trainer findById(long theId) {
		Optional<Trainer> result = trainerRepository.findById(theId);

		Trainer theControl = null;

		if (result.isPresent()) {
			theControl = result.get();
		} else {
			// we didn't find the trainer
			throw new RuntimeException("Did not find trainer id - " + theId);
		}

		return theControl;
	}

	@Override
	public void save(Trainer trainer) {
		trainerRepository.save(trainer);

	}

	@Override
	public void deleteById(long theId) {
		trainerRepository.deleteById(theId);

	}

	@Override
	public Trainer updateTrainer(Trainer theTrainer) {
		Trainer newTrainer = findById(theTrainer.getId());
		newTrainer.setEmail(theTrainer.getEmail());
		newTrainer.setCity(theTrainer.getCity());
		newTrainer.setStreet(theTrainer.getStreet());
		newTrainer.setPhoneNumber(theTrainer.getPhoneNumber());
		newTrainer.setPostalCode(theTrainer.getPostalCode());
		newTrainer.setFirstName(theTrainer.getFirstName());
		newTrainer.setLastName(theTrainer.getLastName());
		newTrainer.setSpecifications(theTrainer.getSpecifications());
		newTrainer.setDisponibilityDays(theTrainer.getDisponibilityDays());
		newTrainer.setFees(theTrainer.getFees());
		save(newTrainer);
		return newTrainer;
	}

	@Override
	public void sendValidationMail(Trainer t) throws AddressException, MessagingException, IOException {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreplybaeldung@gmail.com", "0000*admin");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("noreplybaeldung@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(t.getEmail()));
		msg.setSubject("Votre Compte Tama ");
		msg.setContent("Votre compte sur la plateforme Tama est créé avec succès. \n"
				+ "Vous pouvez vous connecter en utilisant " + ":\n" +

				"E-mail:" + t.getEmail() + "\n" + "" + "" + "Mot de passe:" + t.getPhoneNumber() + "", "text/html");
		msg.setSentDate(new Date(0));

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Votre compte sur la plateforme Tama est créé avec succès. \n"
				+ "Vous pouvez vous connecter en utilisant " + ":\n" +

				"E-mail:" + t.getEmail() + "\n" + "" + "" + "Mot de passe:" + t.getPhoneNumber() + "", "text/html");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		// MimeBodyPart attachPart = new MimeBodyPart();

		// attachPart.attachFile("/var/tmp/image19.png");
		// multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		Transport.send(msg);

	}

	@Transactional
	@Override
	public void validateTrainer(long id) throws AddressException, MessagingException, IOException {
		Trainer t = findById(id);
		t.setValidated(true);
		// t.setPassword(encoder.encode(password));
		save(t);
		// mailtemplateservice.sendUserValidation( email ,t.getEmail() );

	}

	@Override
	public List<Trainer> findTrainersBySpecialization(String specialization) {
		List<Trainer> trainersList = new ArrayList<Trainer>();
		for (Long trainerId : trainerRepository.findTrainersBySpecialization(specialization)) {
			Trainer trainer = findById(trainerId);
			trainersList.add(trainer);

		}
		return trainersList;
	}

	@Override

	public void resetPassword(long id, String newPassword) {

		Trainer t = this.findById(id);

		t.setPassword(encoder.encode(newPassword));

		this.save(t);

	}

	@Override
	public void resetPasswordAutomatically(long id) {

		Trainer t = this.findById(id);

		t.setPassword(encoder.encode(randomPassword.generateSecureRandomPassword()));

		this.save(t);

	}

	public Trainer refuseTrainer(Trainer trainer) {
		Trainer t = findById(trainer.getId());
		if (t != null) {
			t.setValidated(false);
			save(t);
		}
		return t;

	}

	@Transactional
	@Override
	public void deleteTrainer(long id) {

		Trainer t = findById(id);
		List<com.eniso.tama.entity.Session> sessionsList = sessionRepository.findByTrainer(t);
		if (sessionsList != null) {
			for (com.eniso.tama.entity.Session session : sessionsList) {
				session.setTrainer(null);
				sessionRepository.save(session);
			}
		}

		User user = userRepository.findById(id);
		userService.deleteUser(user.getId());

		Trainer trainer = this.findById(id);
		trainer.setDeleted(true);
		trainerRepository.save(trainer);
	}

}
