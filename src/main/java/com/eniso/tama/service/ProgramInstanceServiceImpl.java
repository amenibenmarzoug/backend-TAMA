package com.eniso.tama.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.CompanyRegistration;
import com.eniso.tama.entity.Module;
import com.eniso.tama.entity.ModuleInstance;
import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Theme;
import com.eniso.tama.entity.ThemeDetail;
import com.eniso.tama.entity.ThemeDetailInstance;
import com.eniso.tama.entity.ThemeInstance;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.repository.ProgramInstanceRepository;
import com.eniso.tama.repository.SessionRepository;

@Service
@ComponentScan(basePackageClasses = ProgramInstanceRepository.class)
public class ProgramInstanceServiceImpl implements ProgramInstanceService {

	@Autowired
	private ProgramInstanceRepository programInstanceRepository;
	@Autowired
	private ThemeService themeService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ThemeDetailService themeDetailService;
	@Autowired
	private ThemeInstanceService themeInstanceService;
	@Autowired
	private ModuleInstanceService moduleInstanceService;
	@Autowired
	private ThemeDetailInstanceService themeDetailInstanceService;
	@Autowired
	private ParticipantService participantService;

	@Autowired
	private ParticipantRegistrationService participantRegistrationService;

	@Autowired
	private CompanyRegistrationService companyRegistrationService;

	@Autowired
	private SessionService sessionService;
	@Autowired
	private MailService mailService;

	public ProgramInstanceServiceImpl(ProgramInstanceRepository programInstanceRepository,

			ModuleService moduleService, ThemeDetailService themeDetailService,
			ThemeInstanceService themeInstanceService, ModuleInstanceService moduleInstanceService,
			ThemeDetailInstanceService themeDetailInstanceService, EntityManager entityManager) {
		super();
		this.programInstanceRepository = programInstanceRepository;
		// this.themeService = themeService;
		this.moduleService = moduleService;
		this.themeDetailService = themeDetailService;
		this.themeInstanceService = themeInstanceService;
		this.moduleInstanceService = moduleInstanceService;
		this.themeDetailInstanceService = themeDetailInstanceService;
		this.entityManager = entityManager;
	}

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<ProgramInstance> findAll() {
		return programInstanceRepository.findAllByDeletedFalse();
	}

	@Override
	public ProgramInstance findById(long theId) {
		Optional<ProgramInstance> result = programInstanceRepository.findByIdAndDeletedFalse(theId);

		ProgramInstance theProgramInstance = null;

		if (result.isPresent()) {
			theProgramInstance = result.get();
		} else {
			// we didn't find the participant
			throw new RuntimeException("Did not find program id - " + theId);
		}

		return theProgramInstance;
	}

	@Override
	public ProgramInstance save(ProgramInstance theProgramInstance) {
		ProgramInstance p = programInstanceRepository.save(theProgramInstance);
		return (p);
	}

	@Override

	public void deleteById(long theId) {
		programInstanceRepository.deleteById(theId);

	}

	public class ObjectRepositoryImpl {

		@PersistenceContext
		private EntityManager em;

	}

	@Override
	public ProgramInstance update(ProgramInstance theProgramInstance) {
		ProgramInstance a = entityManager.merge(theProgramInstance);
		return (a);

	}

	@Override
	@Transactional
	public void deleteProgramInstance(long id) {
		List<ThemeInstance> themeInstances = themeInstanceService.getProgramThemesInst(id);
		List<ParticipantRegistration> participantRegistrations = participantRegistrationService
				.findByProgramInstanceId(id);
		List<CompanyRegistration> companyRegistrations = companyRegistrationService.findByProgramInstance(id);
		if (participantRegistrations != null) {
			for (ParticipantRegistration participantRegistration : participantRegistrations) {
				participantRegistrationService.deleteParticipantRegistration(participantRegistration.getId());
			}
		}
		if (companyRegistrations != null) {

			for (CompanyRegistration companyRegistration : companyRegistrations) {
				companyRegistrationService.deleteCompanyRegistation(companyRegistration.getId());
			}
		}

		if (themeInstances != null) {
			for (ThemeInstance themeInstance : themeInstances) {
				themeInstanceService.deleteThemeInstance(themeInstance.getId());
			}
		}
		ProgramInstance programInstance=findById(id);
		programInstance.setDeleted(true);
		programInstanceRepository.save(programInstance);
	}

	@Override
	public List<ProgramInstance> findByProgramId(long id) {
		List<ProgramInstance> list = this.findAll();
		List<ProgramInstance> list1 = new ArrayList<>();
		for (ProgramInstance prInst : list) {
			if (prInst.getProgram().getId() == id) {
				list1.add(prInst);
			}

		}
		return (list1);
	}

	@Transactional
	@Override
	public ProgramInstance addClass(ProgramInstance programInst) {
		programInst.setNbMaxParticipants(programInst.getProgram().getNbMaxParticipants());
		programInst.setNbMinParticipants(programInst.getProgram().getNbMinParticipants());
		ProgramInstance p = save(programInst);
		long id = programInst.getProgram().getId();
		List<Theme> listT = this.themeService.findByProgId(id);

		for (Theme t : listT) {
			
			// traitement création ThmeInst
			ThemeInstance themeInst = new ThemeInstance();
			themeInst.setProgramInstance(p);
			themeInst.setTheme(t);
			themeInst.setThemeInstName(t.getThemeName());
			themeInst.setNbDaysthemeInst(t.getNbDaysTheme());
			ThemeInstance t1 = this.themeInstanceService.save(themeInst);

			List<Module> listM = this.moduleService.findModulesByThemeId(t.getId());
			for (Module m : listM) {
			
				// traitement création moduleInst
				ModuleInstance moduleInst = new ModuleInstance();
				moduleInst.setModule(m);
				moduleInst.setModuleInstanceName(m.getModuleName());
				moduleInst.setNbDaysModuleInstance(m.getNbDaysModule());
				moduleInst.setThemeInstance(t1);
				ModuleInstance m1 = this.moduleInstanceService.save(moduleInst);

				List<ThemeDetail> listTd = this.themeDetailService.findByModuleId(m.getId());
				for (ThemeDetail td : listTd) {
					// traitement
					ThemeDetailInstance themeDetailinst = new ThemeDetailInstance();
					themeDetailinst.setModuleInstance(m1);
					themeDetailinst.setThemeDetail(td);
					themeDetailinst.setNbDaysthemeDetailInst(td.getNbDaysThemeDetail());
					themeDetailinst.setThemeDetailInstName(td.getThemeDetailName());
					this.themeDetailInstanceService.save(themeDetailinst);

				}
			}

		}
		return (programInst);

	}

	@Transactional
	@Override
	public ProgramInstance updateProgramInst(ProgramInstance programInst) {
		ProgramInstance newProgram = findById(programInst.getId());
		newProgram.setProgramInstName(programInst.getProgramInstName());
		newProgram.setNbDaysProgInst(programInst.getNbDaysProgInst());
		newProgram.setPlace(programInst.getPlace());
		newProgram.setLocation(programInst.getLocation());
		newProgram.setProgram(programInst.getProgram());
		newProgram.setBeginDate(programInst.getBeginDate());
		newProgram.setEndDate(programInst.getEndDate());
		newProgram.setNbMaxParticipants(programInst.getProgram().getNbMaxParticipants());
		newProgram.setNbMinParticipants(programInst.getProgram().getNbMinParticipants());
		newProgram.setPrivateProgramInstance(programInst.isPrivateProgramInstance());

		// programService.save(newProgram);

		return (update(newProgram));

	}

	@Override
	public ProgramInstance confirm(ProgramInstance theProgramInstance) {
		theProgramInstance.setValidated(true);
		programInstanceRepository.save(theProgramInstance);
		return theProgramInstance;
	}

	@Override
	public ProgramInstance cancel(ProgramInstance theProgramInstance) {
		theProgramInstance.setValidated(false);
		programInstanceRepository.save(theProgramInstance);
		return theProgramInstance;
	}

	@Override
	public List<ProgramInstance> findByLocationAndValidated(String location, boolean validated) {
		// TODO Auto-generated method stub
		return programInstanceRepository.findProgramInstByLocationAndValidated(location, validated);
	}

	@Scheduled(cron = "0 0 9 * * *")
	// @Scheduled(fixedRate = 60000)
	public void LaunchAlert() throws AddressException, MessagingException, IOException {
		List<ProgramInstance> classes = findAll();
		LocalDate now = LocalDate.now();
		LocalDate next4Week = now.plus(4, ChronoUnit.WEEKS);
		

		for (ProgramInstance c : classes) {
			if ((next4Week == c.getBeginDate().toLocalDate())
					&& (participantService.getParticipantPerClass(c.getId()).size() < c.getNbMinParticipants())) {
				mailService.sendmailAlert(c.getId());
				
			}

		}
	}

	@Override
	public List<ProgramInstance> getConfirmedClasses() {
		// TODO Auto-generated method stub
		return programInstanceRepository.findByValidatedTrue();
	}

	@Override
	@Transactional
	public void omitProgramInstance(long id) {
		List<ThemeInstance> themeInstances = themeInstanceService.getProgramThemesInst(id);
		List<ParticipantRegistration> participantRegistrations = participantRegistrationService
				.findByProgramInstanceId(id);
		List<CompanyRegistration> companyRegistrations = companyRegistrationService.findByProgramInstance(id);
		if (participantRegistrations != null) {
			for (ParticipantRegistration participantRegistration : participantRegistrations) {
				participantRegistrationService.deleteById(participantRegistration.getId());
			}
		}
		if (companyRegistrations != null) {

			for (CompanyRegistration companyRegistration : companyRegistrations) {
				companyRegistrationService.deleteById(companyRegistration.getId());
			}
		}

		if (themeInstances != null) {
			for (ThemeInstance themeInstance : themeInstances) {
				themeInstanceService.omitThemeInstance(themeInstance.getId());
			}
		}
		deleteById(id);
		
	}

	@Override
	public List<ProgramInstance> findAllByPrivateProgramInstanceTrue() {
		
		return programInstanceRepository.findAllByPrivateProgramInstanceTrue();
	}

	@Override
	public List<ProgramInstance> findAllByPrivateProgramInstanceFalse() {
		return programInstanceRepository.findAllByPrivateProgramInstanceFalse();
	}

	@Override
	public List<ProgramInstance> findByProgramInstNameContaining(String name) {
		
		return programInstanceRepository.findByProgramInstNameContaining(name);
	}
	
	

}