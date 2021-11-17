package com.eniso.tama.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.eniso.tama.entity.ThemeInstance;

public interface ThemeInstanceService {
	public List<ThemeInstance> findAll();

	public List<ThemeInstance> findByThemeId(long id);
	
	public ThemeInstance findById(long theId);

	public ThemeInstance save(ThemeInstance theThemeInstance);

	public void deleteById(long theId);
	
	public void deleteThemeInstance(long theId);

	
	public List<ThemeInstance> getProgramThemesInst(long id);
	
	public ResponseEntity<?>  addThemeProgram (ThemeInstance theme, long id);
	
	public ThemeInstance updateThemeInst(ThemeInstance theThemeInst);
}