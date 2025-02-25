package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.eniso.tama.entity.ProgramInstance;

import com.eniso.tama.entity.Program;
import com.eniso.tama.repository.ProgramRepository;

@Service
@ComponentScan(basePackageClasses =ProgramRepository.class )
public class ProgramServiceImpl implements ProgramService {

	
	        @Autowired
			private ProgramRepository programRepository;
			
			@Autowired
			private ProgramInstanceService programInsService;

			
			
			public ProgramServiceImpl(ProgramRepository programRepository, ProgramInstanceService programInsService) {
				super();
				this.programRepository = programRepository;
				this.programInsService = programInsService;
			}

			@Override
			public List<Program> findAll() {
				return programRepository.findAll();
			}

			@Override
			public Program findById(long theId) {
				Optional<Program> result = programRepository.findById(theId);
				
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
					System.out.println(programInstance.getLocation());
					
					programInstance.setNbDaysProgInst(theProgram.getNbDaysProg());
					System.out.println(programInstance.getNbDaysProgInst());
					programInsService.save(programInstance);
				}
				save(theProgram);
				return theProgram;
			}	
	}