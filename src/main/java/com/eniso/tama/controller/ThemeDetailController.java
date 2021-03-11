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

import com.eniso.tama.entity.Module;
import com.eniso.tama.entity.ModuleInstance;
import com.eniso.tama.entity.Program;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Theme;
import com.eniso.tama.entity.ThemeDetail;
import com.eniso.tama.entity.ThemeDetailInstance;
import com.eniso.tama.entity.ThemeInstance;
import com.eniso.tama.service.ModuleInstanceService;
import com.eniso.tama.service.ThemeDetailInstanceService;
import com.eniso.tama.service.ThemeDetailService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = ThemeDetailService.class )
@RequestMapping(value="/api")
public class ThemeDetailController {
	
	@Autowired
	private ThemeDetailInstanceService themeDetailInstService;
	
	@Autowired
	private ThemeDetailService themeDetailService;
	
	@Autowired
	private ModuleInstanceService moduleInstanceService;
	
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
	
	@Transactional
	@PostMapping("/themeDetail")
	public  ThemeDetail addThemeDetail(@RequestBody ThemeDetail theThemeDetail) {
		long id=theThemeDetail.getModule().getId();
		List<ModuleInstance> list = moduleInstanceService.findByModuleId(id);
		
		ThemeDetail t = new ThemeDetail();
		t.setThemeDetailName(theThemeDetail.getThemeDetailName());
		t.setNbDaysThemeDetail(theThemeDetail.getNbDaysThemeDetail());
		t.setModule(theThemeDetail.getModule());
		
		ThemeDetail them=themeDetailService.save(t);
		for (ModuleInstance modInstance : list) {
			ThemeDetailInstance themeInst= new ThemeDetailInstance();
			themeInst.setThemeDetail(them);
			themeInst.setModuleInstance(modInstance);
			themeInst.setThemeDetailInstName(theThemeDetail.getThemeDetailName());
			themeInst.setNbDaysthemeDetailInst(theThemeDetail.getNbDaysThemeDetail());
			themeDetailInstService.save(themeInst);
		}
		return theThemeDetail;
	}
	
	@Transactional
	@PutMapping("/themeDetail")
	public ThemeDetail updateThemeDetail (@RequestBody ThemeDetail theThemeDetail) {
		long id=theThemeDetail.getId();
		List<ThemeDetailInstance> list= themeDetailInstService.findByThemeDetId(id);

		ThemeDetail newTheme = themeDetailService.findById(id);
		newTheme.setNbDaysThemeDetail(theThemeDetail.getNbDaysThemeDetail());
		newTheme.setThemeDetailName(theThemeDetail.getThemeDetailName());
		
		for (ThemeDetailInstance themeInstance : list) {
			themeInstance.setThemeDetailInstName(theThemeDetail.getThemeDetailName());
			themeInstance.setNbDaysthemeDetailInst(theThemeDetail.getNbDaysThemeDetail());
			themeDetailInstService.save(themeInstance);
		}
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
