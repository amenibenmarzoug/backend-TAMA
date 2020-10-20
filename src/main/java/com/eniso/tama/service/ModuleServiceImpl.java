package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Module;
import com.eniso.tama.repository.ModuleRepository;
@Service
@ComponentScan(basePackageClasses = ModuleRepository.class )
public class ModuleServiceImpl  implements ModuleService{
	private ModuleRepository moduleRepository;
		
		public ModuleServiceImpl() {}

		@Autowired
		public ModuleServiceImpl(ModuleRepository moduleRepository) {
			this.moduleRepository = moduleRepository;
		}
		
		@Override
		public List<Module> findAll() {
			return moduleRepository.findAll();
		}

		@Override
		public Module findById(long theId) {
			Optional<Module> result = moduleRepository.findById(theId);
			
			Module module = null;
			
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
		public void save(Module module) {
			moduleRepository.save(module);
		}

		@Override
		public void deleteById(long    theId) {
			moduleRepository.deleteById(theId);
		}

		
	
}
