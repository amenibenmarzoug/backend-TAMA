package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.query.Param;
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
import com.eniso.tama.entity.Theme;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.service.ModuleService;

@RestController
@ComponentScan(basePackageClasses = ModuleService.class)
@RequestMapping(value = "/api")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;

	
	
	public ModuleController(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@GetMapping("/module")
	public List<Module> findAll() {
		return moduleService.findAll();
	}

	@GetMapping("/theme/modules")
	public List<Module> getThemeModules(@RequestParam("id") long id) {
		return moduleService.findModulesByThemeId(id);
		
	}
	
	
	@GetMapping("/modulesOfprogram")
	public List<Module> getProgramModules(@RequestParam("id") long id) {
		List<Module> modulesPerProgram = new ArrayList<Module>();
		for (Module theM : moduleService.findAll()) {
		if(theM.getTheme().getProgram()!=null) {
			if (id == theM.getTheme().getProgram().getId()) {

				modulesPerProgram.add(theM);			
			}
		}
		}
		return modulesPerProgram;
	}

	
	@GetMapping("/modulesNames")
	public List<String> getModulesNames() {
		return moduleService.getModulesNames();
	}
	
	@PostMapping("/modulesNamesPerThemes")
	public List<String> findDistinctModuleNameByThemes(@RequestBody List<String> themes) {
		return moduleService.findDistinctModuleNameByThemes(themes);
	}
	
	@GetMapping("module/{moduleId}")
	public Module getModule(@PathVariable int moduleId) {

		Module module = moduleService.findById(moduleId);

		if (module == null) {
			throw new RuntimeException("module id not found - " + moduleId);
		}

		return module;
	}
	// add mapping for POST /Module - add new Module

	
	@PostMapping("/module")
	public Module addModule(@RequestBody Module module) {
		
		return moduleService.save(module);
		
		
	}

	// add mapping for PUT /module - update existing module

	 
	@PutMapping("/module")
	public Module updateModule(@RequestBody Module theModule) {
	
		return moduleService.updateModule(theModule);
	}

	@DeleteMapping("/module/{moduleId}")
	public ResponseEntity<?> deleteModule(@PathVariable int moduleId) {

		Module module = moduleService.findById(moduleId);

		// throw exception if null

		if (module == null) {
			throw new RuntimeException("the Module id is not found - " + moduleId);
		}

		moduleService.deleteModule(moduleId);

		return ResponseEntity.ok(new MessageResponse("Suppression faite avec succès!"));
	}
	
	@DeleteMapping("/module/omit/{moduleId}")
	public ResponseEntity<?> omitModule(@PathVariable int moduleId) {

		Module module = moduleService.findById(moduleId);

		// throw exception if null

		if (module == null) {
			throw new RuntimeException("the Module id is not found - " + moduleId);
		}

		moduleService.omitModule(moduleId);

		return ResponseEntity.ok(new MessageResponse("Suppression faite avec succès!"));
	}
}