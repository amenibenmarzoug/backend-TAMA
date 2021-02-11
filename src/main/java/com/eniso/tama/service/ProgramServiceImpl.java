package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Program;
import com.eniso.tama.repository.ProgramRepository;
@Service
@ComponentScan(basePackageClasses =ProgramRepository.class )

public class ProgramServiceImpl implements ProgramService {

	

			private ProgramRepository programRepository;

			
			@Autowired
			public ProgramServiceImpl(ProgramRepository theProgramRepository) {
				programRepository = theProgramRepository;
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
	}