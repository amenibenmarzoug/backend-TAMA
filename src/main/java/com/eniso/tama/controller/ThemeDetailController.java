package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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

import com.eniso.tama.entity.ModuleInstance;
import com.eniso.tama.entity.ThemeDetail;
import com.eniso.tama.entity.ThemeDetailInstance;
import com.eniso.tama.service.ModuleInstanceService;
import com.eniso.tama.service.ThemeDetailInstanceService;
import com.eniso.tama.service.ThemeDetailService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = ThemeDetailService.class )
@RequestMapping(value="/api")
public class ThemeDetailController {
	
	
	
	@Autowired
	private ThemeDetailService themeDetailService;
	
	
	
	public ThemeDetailController(ThemeDetailService themeDetailService) {
		super();
		this.themeDetailService = themeDetailService;
	}
	@GetMapping("/themeDetail")
	public List<ThemeDetail> findAll() {
		return themeDetailService.findAll();
	}
	
	@GetMapping("/module/themesDetails")
	public List<ThemeDetail> getModuleThemeDetails(@RequestParam("id") long id) {
		List<ThemeDetail> themeDetailsPerModule = new ArrayList<ThemeDetail>();
		for (ThemeDetail theTD : themeDetailService.findAll()) {
		if(theTD.getModule()!=null) {
			if (id == theTD.getModule().getId()) {

				themeDetailsPerModule.add(theTD);			
			}
		}
		}
		return themeDetailsPerModule;
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
		
		return(themeDetailService.addThemeDetail(theThemeDetail));
		
		
	}
	
	
	@PutMapping("/themeDetail")
	public ThemeDetail updateThemeDetail (@RequestBody ThemeDetail theThemeDetail) {
		
		return(themeDetailService.updateThemeDetail(theThemeDetail));
		
		
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
