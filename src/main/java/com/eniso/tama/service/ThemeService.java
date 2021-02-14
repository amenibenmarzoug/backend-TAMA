package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.Theme;

public interface ThemeService {
    public List<Theme> findAll();
	
	public Theme findById(long theId);
	
	public void save(Theme theme);
	
	public void deleteById(long id);
}
