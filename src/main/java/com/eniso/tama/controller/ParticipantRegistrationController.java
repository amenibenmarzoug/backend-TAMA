package com.eniso.tama.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.service.ParticipantRegistrationService;

@CrossOrigin(origins = "*")
@RestController
@ComponentScan(basePackageClasses = ParticipantRegistrationService.class)
@RequestMapping(value = "/api")
public class ParticipantRegistrationController {
	@Autowired
	private ParticipantRegistrationService participantRegistrationService;

	public ParticipantRegistrationController(ParticipantRegistrationService participantRegService) {
		super();
		this.participantRegistrationService = participantRegService;
	}

	@GetMapping("/participantRegistrations")
	public List<ParticipantRegistration> findAll() {
		return participantRegistrationService.findAll();
	}

	@GetMapping("participantRegistrations/{participantRegistrationId}")
	public ParticipantRegistration getParticipantRegistration(@PathVariable long participantRegistrationId) {

		ParticipantRegistration participantRegistration = participantRegistrationService.findById(participantRegistrationId);

		if (participantRegistration == null) {
			throw new RuntimeException("registration not found - " + participantRegistrationId);
		}

		return participantRegistration;
	}

	@GetMapping("participantRegistrations/participant/{partId}")
	public List<ParticipantRegistration> getEnterpriseRegistration(@PathVariable long partId) {

		return participantRegistrationService.findByParticipantId(partId);
	}

	@GetMapping("participantRegistrations/programInst/{programInstId}")
	public List<ParticipantRegistration> getProgramInstRegistrations(@PathVariable long programInstId) {

		return participantRegistrationService.findByProgramInstanceId(programInstId);
	}

}
