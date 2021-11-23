package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
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
import com.eniso.tama.entity.Theme;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.service.ThemeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = ThemeService.class )
@RequestMapping(value="/api")
public class ThemeController {	
	@Autowired
	private ThemeService themeService;
	
	public ThemeController(ThemeService themeService) {
		super();
		this.themeService = themeService;
	}
	
	@GetMapping("/themes")
	public List<Theme> findAll() {
		return themeService.findAll();
	}
	
	@GetMapping("/themesNames")
	public List<String> findDistinctThemeName() {
		return themeService.findDistinctThemeName();
	}
	
	@GetMapping("/program/themes")
	public List<Theme> getProgramThemes(@RequestParam("id") long id) {
		List<Theme> themesPerProgram = new ArrayList<Theme>();
		for (Theme theT : themeService.findAll()) {
		if(theT.getProgram()!=null) {
			if (id == theT.getProgram().getId()) {

				themesPerProgram.add(theT);			
			}
		}
		}
		return themesPerProgram;
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
	
	
	@PostMapping("/themeProgram")
	public ResponseEntity<?> addThemeProgram(@Valid @RequestBody Theme theme,@RequestParam("id") long id ) {
		
		return(themeService.addThemeProgram(theme,id));
	
	}
	

	@PutMapping("/theme")
	public Theme updateTheme (@RequestBody Theme theTheme) {
		
		return(themeService.updateTheme(theTheme));
		
	}
	
	@DeleteMapping("/theme/{themeId}")
	public ResponseEntity<?> deleteTheme(@PathVariable int  themeId) {
		
		Theme theme = themeService.findById(themeId);
		
		// throw exception if null
		
		if (theme == null) {
			throw new RuntimeException("the theme id is not found - " + themeId);
		}
		
		themeService.deleteTheme(themeId);
		
		return ResponseEntity.ok(new MessageResponse("Suppression faite avec succès!"));
	}
	
	@DeleteMapping("/theme/omit/{themeId}")
	public ResponseEntity<?> omitTheme(@PathVariable int  themeId) {
		
		Theme theme = themeService.findById(themeId);
		
		// throw exception if null
		
		if (theme == null) {
			throw new RuntimeException("the theme id is not found - " + themeId);
		}
		
		themeService.omitTheme(themeId);
		
		return ResponseEntity.ok(new MessageResponse("Suppression faite avec succès!"));
	}

}
