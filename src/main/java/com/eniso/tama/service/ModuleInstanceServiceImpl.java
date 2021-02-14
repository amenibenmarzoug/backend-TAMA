package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.ModuleInstance;
import com.eniso.tama.repository.ModuleInstanceRepository;
@Service
@ComponentScan(basePackageClasses = ModuleInstanceRepository.class )
public class ModuleInstanceServiceImpl  implements ModuleInstanceService{
	private ModuleInstanceRepository moduleInstanceRepository;
		
		public ModuleInstanceServiceImpl() {}

		@Autowired
		public ModuleInstanceServiceImpl(ModuleInstanceRepository moduleInstanceRepository) {
			this.moduleInstanceRepository = moduleInstanceRepository;
		}
		
		@Override
		public List<ModuleInstance> findAll() {
			return moduleInstanceRepository.findAll();
		}

		@Override
		public ModuleInstance findById(long theId) {
			Optional<ModuleInstance> result = moduleInstanceRepository.findById(theId);
			
			ModuleInstance module = null;
			
			if (result.isPresent()) {
				module = result.get();
			}
			else {
				// we didn't find the module
				throw new RuntimeException("Did not find module id - " + theId);
			}
			
			return module;
		}

		@Override
		public void save(ModuleInstance module) {
			moduleInstanceRepository.save(module);
		}

		@Override
		public void deleteById(long    theId) {
			moduleInstanceRepository.deleteById(theId);
		}

		
	
}
