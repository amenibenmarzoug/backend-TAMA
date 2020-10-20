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
import com.eniso.tama.entity.ThemeDetail;
import com.eniso.tama.service.ThemeDetailService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = ThemeDetailService.class )
@RequestMapping(value="/api")
public class ThemeDetailController {
	
	private ThemeDetailService themeDetailService;
	@Autowired
	public ThemeDetailController(ThemeDetailService themeDetailService) {
		super();
		this.themeDetailService = themeDetailService;
	}
	@GetMapping("/themeDetail")
	public List<ThemeDetail> findAll() {
		return themeDetailService.findAll();
	}
	
	@GetMapping("themeDetail/{themeDetailId}")
	public ThemeDetail getThemeDetail(@PathVariable int  themeDetailId) {
		
		ThemeDetail theThemeDetail = themeDetailService.findById(themeDetailId);
		
		if (theThemeDetail == null) {
			throw new RuntimeException("themeDetail id not found - " + themeDetailId);
		}
		
		return theThemeDetail;
	}
	
	@PostMapping("/themeDetail")
	public  ThemeDetail addThemeDetail(@RequestBody ThemeDetail theThemeDetail) {
		
		themeDetailService.save(theThemeDetail);
		return theThemeDetail;
	}
	@PutMapping("/themeDetail")
	public ThemeDetail updateThemeDetail (@RequestBody ThemeDetail theThemeDetail) {
		
		themeDetailService.save(theThemeDetail);
		
		return theThemeDetail;
	}
	
	@DeleteMapping("/themeDetail/{themeDetailId}")
	public String deleteThemeDetail(@PathVariable int  themeDetailId) {
		
		ThemeDetail themeDetail = themeDetailService.findById(themeDetailId);
		
		// throw exception if null
		
		if (themeDetail == null) {
			throw new RuntimeException("the themeDetail id is not found - " + themeDetailId);
		}
		
		themeDetailService.deleteById(themeDetailId);
		
		return "Deleted themeDetail id - " + themeDetailId;
	}
}
