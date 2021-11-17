package com.eniso.tama.service;

import java.util.List;

import org.springframework.http.ResponseEntity;


import com.eniso.tama.entity.Theme;

public interface ThemeService {
	
    public List<Theme> findAll();
	
	public Theme findById(long theId);
	
	public void save(Theme theme);
	
	public void deleteById(long id);
	
	public void deleteTheme(long id);
	
	public List<Theme> findByProgId(long id);
	
	public ResponseEntity<?> addThemeProgram(Theme theme,long id );
	
	public Theme updateTheme(Theme theTheme);
	
	public List<String> findDistinctThemeName();

}