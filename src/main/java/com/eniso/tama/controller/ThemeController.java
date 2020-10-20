package com.eniso.tama.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Theme;


import com.eniso.tama.service.ThemeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = ThemeService.class )
@RequestMapping(value="/api")
public class ThemeController {
	private ThemeService themeService;
	@Autowired
	public ThemeController(ThemeService themeService) {
		super();
		this.themeService = themeService;
	}
	
	@GetMapping("/theme")
	public List<Theme> findAll() {
		return themeService.findAll();
	}
	
	
	@GetMapping("theme/{themeId}")
	public Theme getTheme(@PathVariable int  themeId) {
		
		Theme theTheme = themeService.findById(themeId);
		
		if (theTheme == null) {
			throw new RuntimeException("theme id not found - " + themeId);
		}
		
		return theTheme;
	}
	
	@PostMapping("/theme")
	public  Theme addTheme(@RequestBody Theme theTheme) {
		
		themeService.save(theTheme);
		return theTheme;
	}
	@PutMapping("/theme")
	public Theme updateTheme (@RequestBody Theme theTheme) {
		
		themeService.save(theTheme);
		
		return theTheme;
	}
	
	@DeleteMapping("/theme/{themeId}")
	public String deleteTheme(@PathVariable int  themeId) {
		
		Theme theme = themeService.findById(themeId);
		
		// throw exception if null
		
		if (theme == null) {
			throw new RuntimeException("the theme id is not found - " + themeId);
		}
		
		themeService.deleteById(themeId);
		
		return "Deleted theme id - " + themeId;
	}

}
