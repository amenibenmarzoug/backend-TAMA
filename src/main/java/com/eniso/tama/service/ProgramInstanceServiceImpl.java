package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.repository.ProgramInstanceRepository;

@Service
@ComponentScan(basePackageClasses = ProgramInstanceRepository.class)
public class ProgramInstanceServiceImpl implements ProgramInstanceService {

	private ProgramInstanceRepository programInstanceRepository;

	@Autowired
	public ProgramInstanceServiceImpl(ProgramInstanceRepository theProgramInstanceRepository) {
		programInstanceRepository = theProgramInstanceRepository;
	}

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
	public void save(ProgramInstance theProgramInstance) {
		programInstanceRepository.save(theProgramInstance);
	}

	@Override

	public void deleteById(long theId) {
		programInstanceRepository.deleteById(theId);
	}
}