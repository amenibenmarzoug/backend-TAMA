package com.eniso.tama.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.eniso.tama.entity.Institution;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Role;
import com.eniso.tama.entity.Roles;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.helpers.RandomPasswordGenerator;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.InstitutionRepository;
import com.eniso.tama.repository.RoleRepository;

@Service
@ComponentScan(basePackageClasses = InstitutionRepository.class)
public class InstitutionServiceImpl implements InstitutionService {
	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	

	
	@Autowired
	RandomPasswordGenerator randomPassword;

	// public InstitutionServiceImpl() {}

	public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
		super();
		this.institutionRepository = institutionRepository;
	}

	@Override
	public List<Institution> findAll() {
		return institutionRepository.findAll();
	}

	@Override
	public Institution findById(long theId) {
		Optional<Institution> result = institutionRepository.findById(theId);

		Institution theControl = null;

		if (result.isPresent()) {
			theControl = result.get();
		} else {
			// we didn't find the participant
			throw new RuntimeException("Did not find institution id - " + theId);
		}

		return theControl;
	}

	@Override
	public void save(Institution theinstitution) {
		institutionRepository.save(theinstitution);

	}

	@Override
	public void deleteById(long theId) {
		institutionRepository.deleteById(theId);
	}

	public Institution getInstitution(long institutionId) {

		Institution theInstitution = findById(institutionId);

		if (theInstitution == null) {
			throw new RuntimeException("Participant id not found - " + institutionId);
		}

		return theInstitution;
	}
	
	public ResponseEntity<?> registerInstitutionParManager(Institution theI) {

		if (institutionRepository.existsByEmail(theI.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
	
		Set<Role> roles = new HashSet<>();
		Role modRole = roleRepository.findByRole(Roles.INSTITUTION)
				.orElseThrow(() -> new RuntimeException("Error: Role Institution is not found."));
		roles.add(modRole);

		Institution inst = new Institution();
		inst.setEmail(theI.getEmail());
		inst.setPassword(encoder.encode(theI.getPassword()));
		inst.setStreet(theI.getStreet());
		inst.setCity(theI.getCity());
		inst.setPostalCode(theI.getPostalCode());
		inst.setPhoneNumber(theI.getPhoneNumber());
		inst.setRoles(roles);
		inst.setInstitutionName(theI.getInstitutionName());

		institutionRepository.save(inst);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	public Institution updateParticipant(Institution theInstitution) {
		Institution newInstitution = findById(theInstitution.getId());
		
		newInstitution.setCity(theInstitution.getCity());
		newInstitution.setStreet(theInstitution.getStreet());
		newInstitution.setPostalCode(theInstitution.getPostalCode());
		newInstitution.setPhoneNumber(theInstitution.getPhoneNumber());
		newInstitution.setInstitutionName(theInstitution.getInstitutionName());	
		save(newInstitution);

		return theInstitution;
	}
	
	public String deleteInstitution( long id) {

		Institution inst= findById(id);

		// throw exception if null

		if (inst == null) {
			throw new RuntimeException("the institution id is not found - " + id);
		}

		deleteById(id);

		return "Deleted institution id - " + id;
}

	@Override
	public void resetPassword(long id, String newPassword) {
        Institution i = this.findById(id);
		
		i.setPassword(encoder.encode(newPassword));
		
		this.save(i);
		
	}

	@Override
	public void resetPasswordAutomatically(long id) {
		Institution i = this.findById(id);
		
		i.setPassword(encoder.encode(randomPassword.generateSecureRandomPassword()));
		
		this.save(i);
		
		
	}

}