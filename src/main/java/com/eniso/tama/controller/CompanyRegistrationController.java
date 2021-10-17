package com.eniso.tama.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eniso.tama.entity.CompanyRegistration;
import com.eniso.tama.service.CompanyRegistrationService;

@CrossOrigin(origins = "*")
@RestController
@ComponentScan(basePackageClasses = CompanyRegistrationService.class)
@RequestMapping(value = "/api")
public class CompanyRegistrationController {


	@Autowired
	private CompanyRegistrationService companyRegistrationService;
	

	public CompanyRegistrationController(CompanyRegistrationService companyRegistration) {
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
	
	
}
