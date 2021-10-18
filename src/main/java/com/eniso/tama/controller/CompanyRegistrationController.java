package com.eniso.tama.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.dto.EntrepriseDto;
import com.eniso.tama.entity.CompanyRegistration;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.service.CompanyRegistrationService;
import com.eniso.tama.service.EntrepriseService;

@CrossOrigin(origins = "*")
@RestController
@ComponentScan(basePackageClasses = CompanyRegistrationService.class)
@RequestMapping(value = "/api")
public class CompanyRegistrationController {

	@Autowired
	private CompanyRegistrationService companyRegistrationService;
	
	@Autowired
	private EntrepriseService entrepriseService;

	public CompanyRegistrationController(CompanyRegistrationService companyRegistration) {
		super();
		this.companyRegistrationService = companyRegistration;
	}

	@GetMapping("/companyRegistrations")
	public List<CompanyRegistration> findAll() {
		return companyRegistrationService.findAll();
	}

	@GetMapping("companyRegistrations/{companyRegistrationsId}")
	public CompanyRegistration getCompanyRegistration(@PathVariable long companyRegistrationsId) {

		CompanyRegistration companyRegistration = companyRegistrationService.findById(companyRegistrationsId);

		if (companyRegistration == null) {
			throw new RuntimeException("programId not found - " + companyRegistrationsId);
		}

		return companyRegistration;
	}

	@GetMapping("companyRegistrations/enterprise/{enterpriseId}")
	public List<CompanyRegistration> getEnterpriseRegistration(@PathVariable long enterpriseId) {

		return companyRegistrationService.findByEntreprise(enterpriseId);
	}

	@GetMapping("companyRegistrations/programInst/{programInstId}")
	public List<CompanyRegistration> getProgramInstRegistrations(@PathVariable long programInstId) {

      return companyRegistrationService.findByProgramInstance(programInstId);
	}

	@PutMapping("register/enterprise/{enterpriseId}")
	public Entreprise registerEnterprise(@RequestBody EntrepriseDto enterprisedto,@PathVariable long enterpriseId ) {
     return companyRegistrationService.registerEntrep(enterprisedto, enterpriseId);
	}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}