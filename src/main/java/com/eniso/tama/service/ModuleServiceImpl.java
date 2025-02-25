package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Module;
import com.eniso.tama.entity.ModuleInstance;
import com.eniso.tama.entity.ThemeInstance;
import com.eniso.tama.repository.ModuleRepository;

@Service
@ComponentScan(basePackageClasses = ModuleRepository.class)
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private ModuleInstanceService moduleInstService;
	
	@Autowired
	private ThemeInstanceService themeInstService;
	

	public ModuleServiceImpl() {
	}

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
		} else {
			// we didn't find the module
			throw new RuntimeException("Did not find module id - " + theId);
		}

		return module;
	}

	@Transactional 
	@Override
	public Module save(Module module) {
		long id=module.getTheme().getId();
		List<ThemeInstance> list = themeInstService.findByThemeId(id);
		
		Module mod=moduleRepository.save(module);
		
		for (ThemeInstance themeInstance : list) {
			ModuleInstance modInst= new ModuleInstance();
			modInst.setModule(mod);
			modInst.setModuleInstanceName(mod.getModuleName());
			modInst.setNbDaysModuleInstance(mod.getNbDaysModule());
			modInst.setThemeInstance(themeInstance);
			moduleInstService.save(modInst);
		}
		return mod;
	}

	@Override
	public void deleteById(long theId) {
		moduleRepository.deleteById(theId);
	}

	@Override
	public List<Module> findModulesByThemeId(long id) {
		List<Module> list = moduleRepository.findAll();
		List<Module> list1 = new ArrayList<>();
		for (Module m : list) {
			if (m.getTheme() != null) {
				if (m.getTheme().getId() == id) {
					list1.add(m);
				}
			}
		}

		return (list1);
	}

	@Transactional 
	@Override
	public Module updateModule(Module theModule) {
		long id = theModule.getId();
		List<ModuleInstance> list = moduleInstService.findByModuleId(id);

		Module module = findById(id);
		module.setModuleName(theModule.getModuleName());
		module.setNbDaysModule(theModule.getNbDaysModule());
		module.setTheme(theModule.getTheme());
		save(module);
		for (ModuleInstance moduleInstance : list) {
			moduleInstance.setModuleInstanceName(theModule.getModuleName());
			moduleInstance.setNbDaysModuleInstance(theModule.getNbDaysModule());
			moduleInstService.save(moduleInstance);
		}
		return module;
	}

}
