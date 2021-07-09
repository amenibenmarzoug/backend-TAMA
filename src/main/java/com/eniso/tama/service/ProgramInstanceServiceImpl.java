package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate5.HibernateOperations;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Module;
import com.eniso.tama.entity.ModuleInstance;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Theme;
import com.eniso.tama.entity.ThemeDetail;
import com.eniso.tama.entity.ThemeDetailInstance;
import com.eniso.tama.entity.ThemeInstance;
import com.eniso.tama.repository.ProgramInstanceRepository;

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

	
	
	
	
	/*public ProgramInstanceServiceImpl(ProgramInstanceRepository theProgramInstanceRepository) {
		programInstanceRepository = theProgramInstanceRepository;
	}*/
	
	
	

	public ProgramInstanceServiceImpl(ProgramInstanceRepository programInstanceRepository,

			ModuleService moduleService, ThemeDetailService themeDetailService,
			ThemeInstanceService themeInstanceService, ModuleInstanceService moduleInstanceService,
			ThemeDetailInstanceService themeDetailInstanceService, EntityManager entityManager) {
		super();
		this.programInstanceRepository = programInstanceRepository;
		//this.themeService = themeService;
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
		return programInstanceRepository.findAll();
	}

	@Override
	public ProgramInstance findById(long theId) {
		Optional<ProgramInstance> result = programInstanceRepository.findById(theId);

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
		// programInstanceRepository.deleteById(theId);
		Optional<ProgramInstance> p = programInstanceRepository.findById(theId);
		entityManager.remove(p);

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
	public void delete(ProgramInstance theProgramInstance) {

		// entityManager.getTransaction().begin();
		// entityManager.refresh(theProgramInstance);
		entityManager.remove(theProgramInstance);
		// entityManager.getTransaction().commit();

	}

	@Override
	public List<ProgramInstance> findByProgramId(long id) {
		List<ProgramInstance> list = programInstanceRepository.findAll();
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
		ProgramInstance p = save(programInst);
		long id = programInst.getProgram().getId();
		List<Theme> listT = this.themeService.findByProgId(id);

		for (Theme t : listT) {
			System.out.println(t.getThemeName());
			// traitement création ThmeInst
			ThemeInstance themeInst = new ThemeInstance();
			themeInst.setProgramInstance(p);
			themeInst.setTheme(t);
			themeInst.setThemeInstName(t.getThemeName());
			themeInst.setNbDaysthemeInst(t.getNbDaysTheme());
			ThemeInstance t1 = this.themeInstanceService.save(themeInst);

			List<Module> listM = this.moduleService.findModulesByThemeId(t.getId());
			for (Module m : listM) {
				System.out.println(m.getModuleName());
				// traitement création moduleInst
				ModuleInstance moduleInst = new ModuleInstance();
				moduleInst.setModule(m);
				moduleInst.setModuleInstanceName(m.getModuleName());
				moduleInst.setNbDaysModuleInstance(m.getNbDaysModule());
				moduleInst.setThemeInstance(t1);
				ModuleInstance m1 = this.moduleInstanceService.save(moduleInst);

				List<ThemeDetail> listTd = this.themeDetailService.findByModuleId(m.getId());
				for (ThemeDetail td : listTd) {
					System.out.println(td.getThemeDetailName());
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
		;
		newProgram.setLocation(programInst.getLocation());
		newProgram.setProgram(programInst.getProgram());
		newProgram.setBeginDate(programInst.getBeginDate());
		newProgram.setEndDate(programInst.getEndDate());

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
}