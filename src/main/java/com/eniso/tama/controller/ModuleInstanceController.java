package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Module;
import com.eniso.tama.entity.ModuleInstance;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.service.ModuleInstanceService;

@RestController
@ComponentScan(basePackageClasses = ModuleInstanceService.class)
@RequestMapping(value = "/api")
public class ModuleInstanceController {

	@Autowired
	private ModuleInstanceService moduleInstanceService;

	public ModuleInstanceController(ModuleInstanceService moduleInstanceService) {
		this.moduleInstanceService = moduleInstanceService;
	}

	@GetMapping("/moduleInstance")
	public List<ModuleInstance> findAll() {
		return moduleInstanceService.findAll();
	}

	@GetMapping("/themeInst/modulesInst")
	public List<ModuleInstance> getThemeModules(@RequestParam("id") long id) {
		return moduleInstanceService.getThemeModules(id);
	}

	@GetMapping("/moduleInstance/{moduleInstanceId}")
	public ModuleInstance getModule(@PathVariable int moduleId) {

		ModuleInstance module = moduleInstanceService.findById(moduleId);

		if (module == null) {
			throw new RuntimeException("module id not found - " + moduleId);
		}

		return module;
	}
	// add mapping for POST /Module - add new Module
	
	
	
	
	
	@GetMapping("/modulesOfclass")
	public List<ModuleInstance> getProgramModules(@RequestParam("id") long id) {
		List<ModuleInstance> modulesInstPerClass = new ArrayList<ModuleInstance>();
		for (ModuleInstance theM : moduleInstanceService.findAll()) {
		if(theM.getThemeInstance().getProgramInstance()!=null) {
			if (id == theM.getThemeInstance().getProgramInstance().getId()) {

				modulesInstPerClass.add(theM);			
			}
		}
		}
		return modulesInstPerClass;
	}


	@PostMapping("/moduleInstance")
	public ModuleInstance addModule(@RequestBody ModuleInstance module) {

		moduleInstanceService.save(module);
		return module;
	}

	// add mapping for PUT /module - update existing module

	@PutMapping("/moduleInstance")
	public ModuleInstance updateModule(@RequestBody ModuleInstance module) {

		moduleInstanceService.save(module);

		return module;
	}

	@DeleteMapping("/moduleInstance/{moduleId}")
	public ResponseEntity<?> deleteModule(@PathVariable long moduleId) {

		ModuleInstance module = moduleInstanceService.findById(moduleId);

		// throw exception if null

		if (module == null) {
			throw new RuntimeException("the Module id is not found - " + moduleId);
		}

		moduleInstanceService.deleteModuleInstance(moduleId);

		return ResponseEntity.ok(new MessageResponse("Suppression faite avec succès!"));
	}
	
	@DeleteMapping("/moduleInstance/omit/{moduleId}")
	public ResponseEntity<?> omitModule(@PathVariable long moduleId) {

		ModuleInstance module = moduleInstanceService.findById(moduleId);

		// throw exception if null

		if (module == null) {
			throw new RuntimeException("the Module id is not found - " + moduleId);
		}

		moduleInstanceService.omitModuleInstance(moduleId);

		return ResponseEntity.ok(new MessageResponse("Suppression faite avec succès!"));
	}
}
