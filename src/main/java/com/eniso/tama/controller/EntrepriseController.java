package com.eniso.tama.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.dto.EntrepriseDto;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.helpers.RandomPasswordGenerator;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.EnterpriseRepository;
import com.eniso.tama.service.EntrepriseService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = EntrepriseService.class)
@RequestMapping(value = "/api")
public class EntrepriseController {
	
	@Autowired
	private EntrepriseService entrepriseService;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
    RandomPasswordGenerator randomPassword;
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;

	
	public EntrepriseController(EntrepriseService entrepriseService) {
		super();
		this.entrepriseService = entrepriseService;
	}

	@GetMapping("/entreprises")
	public List<Entreprise> findAll() {
		return entrepriseService.findAll();
		
	}

	@GetMapping("entreprises/{entrepriseId}")
	public Entreprise getParticipant(@PathVariable long entrepriseId) {

		Entreprise theEntreprise = entrepriseService.findById(entrepriseId);

		if (theEntreprise == null) {
			throw new RuntimeException("Entreprise id not found - " + entrepriseId);
		}

		return theEntreprise;
	}
	
	@GetMapping("entreprises/location/{location}")
	public List<Entreprise> getEnterpriseByCity(@PathVariable String location) {

		List<Entreprise> entreprises = entrepriseService.findEnterpriseByLocation(location);

		

		return entreprises;
	}
	
// add mapping for POST /participants - add new control

	@PostMapping("/auth/manager/company")
	public ResponseEntity<?> addCompanyByManager(@RequestBody EntrepriseDto company) {

		if (enterpriseRepository.existsByEmail(company.getEmail())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Erreur: Veuillez donner un email valide. Cet email existe déjà"));
		}
		if (enterpriseRepository.existsByPhoneNumber(company.getPhoneNumber())) {
			return ResponseEntity.badRequest().body(new MessageResponse(
					"Erreur: Veuillez donner un numéro de téléphone valide. Ce numéro existe déjà"));
		}
		
		entrepriseService.addCompanyByManager(company);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


	@PutMapping("/update/entreprise")
	public  ResponseEntity<?>   updateEntreprise(@RequestBody EntrepriseDto theEntreprise) {
		return entrepriseService.updateEntreprise(theEntreprise);
		
	}

	
	@DeleteMapping("/entreprises/{entrepriseId}")
	public String deleteParticipant(@PathVariable int entrepriseId) {

		Entreprise tempEntreprise = entrepriseService.findById(entrepriseId);

		// throw exception if null

		if (tempEntreprise == null) {
			throw new RuntimeException("the participant id is not found - " + entrepriseId);
		}

		entrepriseService.deleteById(entrepriseId);

		return "Deleted participant id - " + entrepriseId;
	}

	@GetMapping("/NonValidEntreprise")
	public List<Entreprise> getNonValid() {

		
		return entrepriseService.getNonValid();

	}

	@GetMapping("/sendMailToEntrep")
	private void sendmail(@RequestParam("id") long id) throws AddressException, MessagingException, IOException {

		entrepriseService.sendmail(id);;
		
	}
	
	@PutMapping("/entreprises/refuse")
	public Entreprise refuseCompany(@RequestBody Entreprise company)
		{
		Entreprise company1 = entrepriseService.refuseCompany(company);
		return company1;
	}


}