package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Theme;
import com.eniso.tama.entity.Program;
import com.eniso.tama.repository.ProgramRepository;

@Service
@ComponentScan(basePackageClasses =ProgramRepository.class )
public class ProgramServiceImpl implements ProgramService {

	
	        @Autowired
			private ProgramRepository programRepository;
			
			@Autowired
			private ProgramInstanceService programInsService;

			@Autowired
			private ThemeService themeService;
			
			public ProgramServiceImpl(ProgramRepository programRepository, ProgramInstanceService programInsService) {
				super();
				this.programRepository = programRepository;
				this.programInsService = programInsService;
			}

			@Override
			public List<Program> findAll() {
				return programRepository.findAllByDeletedFalse();
			}

			@Override
			public Program findById(long theId) {
				Optional<Program> result = programRepository.findByIdAndDeletedFalse(theId);
				
				Program theControl = null;
				
				if (result.isPresent()) {
					theControl = result.get();
				}
				else {
					// we didn't find the participant
					throw new RuntimeException("Did not find program id - " + theId);
				}
				
				return theControl;
			}


			@Override
			public void save(Program theControl) {
				programRepository.save(theControl);
			}
			@Override
			public void saveSpecificProgram(Program theProgram) {
				theProgram.setSpecificProgram(true);
				programRepository.save(theProgram);
				
			}	

		
			@Override
			public void deleteById (long theId) {
				programRepository.deleteById(theId);
			}

			
			@Override
			@Transactional 
			public Program updateProgram(Program theProgram) {
				long id=theProgram.getId();
				Program newProgram = findById(id);
				newProgram.setProgramName(theProgram.getProgramName());
				newProgram.setNbDaysProg(theProgram.getNbDaysProg());
				
				List<ProgramInstance> list = programInsService.findByProgramId(id);
				for (ProgramInstance programInstance : list) {
					programInstance.setProgramInstName(theProgram.getProgramName());					
					programInstance.setNbDaysProgInst(theProgram.getNbDaysProg());
					programInstance.setNbMaxParticipants(theProgram.getNbMaxParticipants());
					programInstance.setNbMinParticipants(theProgram.getNbMinParticipants());
					programInsService.save(programInstance);
				}
				save(theProgram);
				return theProgram;
			}

			@Override
			@Transactional 
			public void deleteProgram(long theId) {
				List<ProgramInstance> programInstances=programInsService.findByProgramId(theId);
				List<Theme> themes=themeService.findByProgId(theId);
				if(programInstances!=null) {
					for (ProgramInstance programInstance : programInstances) {
						programInsService.deleteProgramInstance(programInstance.getId());
					}
				}
				if(themes!=null) {
					for ( Theme theme: themes) {
						themeService.deleteTheme(theme.getId());
					}
				}	
				Program program=findById(theId);
				program.setDeleted(true);
				programRepository.save(program);
			}

			@Override
			@Transactional
			public void omitProgram(long theId) {
				List<ProgramInstance> programInstances=programInsService.findByProgramId(theId);
				List<Theme> themes=themeService.findByProgId(theId);
				if(programInstances!=null) {
					for (ProgramInstance programInstance : programInstances) {
						programInsService.omitProgramInstance(programInstance.getId());
					}
				}
				if(themes!=null) {
					for ( Theme theme: themes) {
						themeService.omitTheme(theme.getId());
					}
				}	
				deleteById(theId);
				
			}

			
	}