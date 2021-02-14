package com.eniso.tama.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Module;
import com.eniso.tama.entity.Theme;
import com.eniso.tama.service.ModuleService;

@RestController
@ComponentScan(basePackageClasses = ModuleService.class )
@RequestMapping(value="/api")
public class ModuleController {


		private ModuleService moduleService;
		@Autowired
		public ModuleController(ModuleService moduleService) {
			this.moduleService = moduleService;
		} 


		@GetMapping("/module")
		public List<Module> findAll() {
			return moduleService.findAll();
		}
		
		@GetMapping("module/{moduleId}")
		public Module getModule(@PathVariable int  moduleId) {
			
			Module module = moduleService.findById(moduleId);
			
			if (module == null) {
				throw new RuntimeException("module id not found - " + moduleId);
			}
			
			return module;
		}
		// add mapping for POST /Module - add new Module

		@PostMapping("/module")
		public  Module addModule(@RequestBody Module module) {
		
			
			
			moduleService.save(module);
			return module;
		}
		
		
		// add mapping for PUT /module - update existing module
		
			@PutMapping("/module")
			public Module updateModule(@RequestBody Module theModule) {
				Module module = moduleService.findById(theModule.getId());
				module.setModuleName(theModule.getModuleName());
				module.setNbDaysModule(theModule.getNbDaysModule());
				module.setTheme(theModule.getTheme());
				moduleService.save(module);
				
				return module;
			}

			@DeleteMapping("/module/{moduleId}")
			public String deleteModule(@PathVariable int  moduleId) {
				
				Module module = moduleService.findById(moduleId);
				
				// throw exception if null
				
				if (module == null) {
					throw new RuntimeException("the Module id is not found - " + moduleId);
				}
				
				moduleService.deleteById(moduleId);
				
				return "Deleted Module id - " + moduleId;
			}
}
