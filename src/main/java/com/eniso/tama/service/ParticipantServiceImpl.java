package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.entity.Status;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.entity.User; 
import com.eniso.tama.helpers.RandomPasswordGenerator;
import com.eniso.tama.repository.ParticipantRegistrationRepository;
import com.eniso.tama.repository.ParticipantRepository;
import com.eniso.tama.repository.ProgramInstanceRepository;
import com.eniso.tama.repository.UserRepository;

@Service
@ComponentScan(basePackageClasses = ParticipantRepository.class)
public class ParticipantServiceImpl implements ParticipantService {

	@Autowired
	private ParticipantRepository participantRepository;
	// private ProgramInstanceRepository programInstanceRepository;

	@Autowired
	private ParticipantRegistrationRepository participantRegistrationRepository;
	
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RandomPasswordGenerator randomPassword;

	
	@Autowired
	private AttendanceService attendanceService;
	
	
	@Autowired
	private ParticipantRegistrationService participantRegistrationService;
	
	
	@Autowired
	private UserService userService ;

	@Autowired
	private UserRepository userRepository;

	

	public ParticipantServiceImpl(ParticipantRepository theParticipantRepository) {
		participantRepository = theParticipantRepository;
	}

	@Override
	public List<Participant> findAll() {
		return participantRepository.findAll();
	}

	@Override
	public Participant findById(long theId) {
		Optional<Participant> result = participantRepository.findById(theId);
		Participant theControl = null;

		if (result.isPresent()) {
			theControl = result.get();
		} else {
			// we didn't find the participant
			throw new RuntimeException("Did not find participant id - " + theId);
		}

		return theControl;
	}

	// find by Company
	@Override

	public List<Participant> getValidatedParticipantsByEntreprise(Entreprise company) {

		return participantRepository.findByEntrepriseAndValidatedTrue(company);

	}

	// find By level
	@Override
	public List<Participant> findByLevel(String theLevel) {

		return participantRepository.findByLevel(theLevel);

	}

	// find by abondan
	@Override
	public List<Participant> findByAbonadn(boolean theAbondan) {

		return participantRepository.findByAbandon(theAbondan);
	}

	@Override
	public void save(Participant theParticipant) {
		participantRepository.save(theParticipant);
	}

	@Override
	public void deleteById(long theId) {

		participantRepository.deleteById(theId);
	}

	// get Confirmed Participants by ProgramInst
	@Override
	public List<Participant> findParticipantsByClass(long programInstId) {
		/*
		 * Optional<ProgramInstance> result =
		 * programInstanceRepository.findById(programInstId);
		 * 
		 * List<Participant> participants ; ProgramInstance programInst ; if
		 * (result.isPresent()) { programInst=result.get(); } else { // we didn't find
		 * the programInst throw new RuntimeException("Did not find class with Id  - " +
		 * programInstId); }
		 */
		List<Long> partcipantsIdsList = participantRepository.findConfirmedParticipantsByClass(programInstId);
		List<Participant> participants = new ArrayList<>();
		for (long ParticipantID : partcipantsIdsList) {
			Optional<Participant> theP = participantRepository.findById(ParticipantID);
			if (theP.isPresent()) {
				participants.add(theP.get());

			}
		}
		return participants;

	}

	@Override
	public List<Participant> getValidatedParticipantsPerClass(long id) {
		List<Participant> participantsPerClasse = new ArrayList<Participant>();
		for (Participant theP : findAll()) {
			for (ParticipantRegistration reg : participantRegistrationRepository.findByParticipantId(theP.getId()))
				if (reg.getPrograminstance().getId() == id && theP.isValidated()
						&& reg.getStatus() == Status.ACCEPTED) {
					participantsPerClasse.add(theP);

				}
		}
		return participantsPerClasse;
	}

	@Override
	public List<Participant> getParticipantPerClass(long id) {
		List<Participant> participantsPerClasse = new ArrayList<Participant>();

		for (ParticipantRegistration reg : participantRegistrationRepository.findAll())
			if (reg.getPrograminstance().getId() == id) {
				participantsPerClasse.add(reg.getParticipant());

			}

		return participantsPerClasse;
	}

	@Override
	public float percentMascPart() {

		return participantRepository.getMaleParticipants();
	}

	@Override
	public List<Participant> findValidatedParticipants() {
		// TODO Auto-generated method stub
		return participantRepository.findByValidatedTrue();
	}

	@Override
	public Participant refuseParticipant(Participant participant) {
		Participant participantToSave = findById(participant.getId());
		if (participantToSave != null) {
			participantToSave.setValidated(false);
			save(participantToSave);
		}

		return participantToSave;
	}


	@Override
	public void resetPassword(long id, String newPassword) {
		Participant p = this.findById(id);

		p.setPassword(encoder.encode(newPassword));

		this.save(p);

	}

	@Override
	public void resetPasswordAutomatically(long id) {
		Participant p = this.findById(id);

		p.setPassword(encoder.encode(randomPassword.generateSecureRandomPassword()));

		this.save(p);

	}

	@Override
	public Set<Participant> findParticipantsByRegistrationStatus(Status status) {
		
		Set<Participant> participants = new HashSet<>();

		for (ParticipantRegistration reg : participantRegistrationRepository.findAll())
			if (reg.getStatus() == status) {
				participants.add(reg.getParticipant());

			}
		return participants;
	}

	@Override
	public List<Participant> findParticipantsWithoutRegistration() {
		List<Participant> participants = new ArrayList<>();
		for (Participant participant : findAll()) {
			if (!findParticipantsWithRegistration().contains(participant)) {
				participants.add(participant);
			}
		}
		return participants;
	}

	@Override
	public Set<Participant> findParticipantsWithRegistration() {
		Set<Participant> participants = new HashSet<>();
		for (ParticipantRegistration registration : participantRegistrationRepository.findAll()) {
			participants.add(registration.getParticipant());
		}
		return participants;

	}

	
	@Transactional
	@Override
	public void deleteParticipant(long id) {
		List<Attendance> attendanceList = attendanceService.findByParticipantId(id);
		
		if (attendanceList != null) {
			for (Attendance attendance : attendanceList) {
				attendanceService.deleteAttendance(attendance.getId());
			}
		}
		
		List <ParticipantRegistration>  participantRegistrationList =  participantRegistrationRepository.findByParticipantId(id);
		if (participantRegistrationList != null) {
			for (ParticipantRegistration p : participantRegistrationList ) {
				participantRegistrationService.deleteParticipantRegistration(p.getId());
			}	
		}
		
		User user = userRepository.findById(id);
		userService.deleteUser(user.getId());

		
		
		Participant participant = this.findById(id);
		participant.setDeleted(true);
		participantRepository.save(participant);
		
		
		
		
	}

}