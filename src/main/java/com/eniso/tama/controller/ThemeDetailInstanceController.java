package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eniso.tama.entity.ThemeDetailInstance;
import com.eniso.tama.service.ThemeDetailInstanceService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = ThemeDetailInstanceService.class)
@RequestMapping(value = "/api")
public class ThemeDetailInstanceController {
	
	@Autowired
	private ThemeDetailInstanceService themeDetailInstanceService;
	
	public ThemeDetailInstanceController(ThemeDetailInstanceService themeDetailInstanceService) {
		super();
		this.themeDetailInstanceService = themeDetailInstanceService;
	}
	@GetMapping("/themeDetailInst")
	public List<ThemeDetailInstance> findAll() {
		return themeDetailInstanceService.findAll();
	}
	
	@GetMapping("/moduleInst/themesDetailsInst")
	public List<ThemeDetailInstance> getModuleThemeDetails(@RequestParam("id") long id) {
		return(themeDetailInstanceService.getModuleThemeDetails(id));
		
	}
	@GetMapping("themeDetailInst/{themeDetailInstId}")
	public ThemeDetailInstance getThemeDetailInst(@PathVariable int  themeDetailInstId) {
		
		ThemeDetailInstance theThemeDetailInst = themeDetailInstanceService.findById(themeDetailInstId);
		
		if (theThemeDetailInst == null) {
			throw new RuntimeException("themeDetailInst id not found - " + themeDetailInstId);
		}
		
		return theThemeDetailInst;
	}
	
	@PostMapping("/themeDetailInst")
	public  ThemeDetailInstance addThemeDetailInst(@RequestBody ThemeDetailInstance theThemeDetailInst) {
		
		themeDetailInstanceService.save(theThemeDetailInst);
		return theThemeDetailInst;
	}
	@PutMapping("/themeDetailInst")
	public ThemeDetailInstance updateThemeDetailInst (@RequestBody ThemeDetailInstance theThemeDetailInst) {
		
		themeDetailInstanceService.save(theThemeDetailInst);
		
		return theThemeDetailInst;
	}
	
	@DeleteMapping("/themeDetailInst/{themeDetailInstId}")
	public String deleteThemeDetailInst(@PathVariable int  themeDetailInstId) {
		
		ThemeDetailInstance themeDetailInst = themeDetailInstanceService.findById(themeDetailInstId);
		
		// throw exception if null
		
		if (themeDetailInst == null) {
			throw new RuntimeException("the themeDetailInst id is not found - " + themeDetailInstId);
		}
		
		themeDetailInstanceService.deleteById(themeDetailInstId);
		
		return "Deleted themeDetailInst id - " + themeDetailInstId;
	}
}
