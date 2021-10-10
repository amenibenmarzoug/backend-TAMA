package com.eniso.tama.service;

import java.util.ArrayList;
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
	   @Autowired
	private ModuleInstanceRepository moduleInstanceRepository;
	 
		public ModuleInstanceServiceImpl() {}

		
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
		public ModuleInstance save(ModuleInstance module) {
			return(moduleInstanceRepository.save(module));
		}

		@Override
		public void deleteById(long    theId) {
			moduleInstanceRepository.deleteById(theId);
		}

		@Override
		public List<ModuleInstance> findByModuleId(long id) {
			List<ModuleInstance> list= moduleInstanceRepository.findAll();
			List<ModuleInstance> list1= new ArrayList<>();
			for (ModuleInstance modInst : list ) {
				if (modInst.getModule().getId()== id) {
					 list1.add(modInst);
				}
				
			}
			return (list1);
		}

		@Override
		public List<ModuleInstance> getThemeModules(long id) {
			List<ModuleInstance> list= moduleInstanceRepository.findAll();
			List<ModuleInstance> modulesPerTheme = new ArrayList<ModuleInstance>();
			for (ModuleInstance theM : list) {
				if (theM.getThemeInstance() != null) {
					if (id == theM.getThemeInstance().getId()) {

						modulesPerTheme.add(theM);
					}
				}
			}
			return modulesPerTheme;
			
		}


		@Override
		public void saveModuleInstance(ModuleInstance module) {
			System.out.println("module id");
			System.out.println(module.getId());
			moduleInstanceRepository.save(module);			
		}
		
	
}
