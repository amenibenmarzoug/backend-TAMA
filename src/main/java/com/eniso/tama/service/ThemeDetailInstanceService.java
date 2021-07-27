package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.ThemeDetailInstance;

public interface ThemeDetailInstanceService {
	public List<ThemeDetailInstance> findAll();

	public List<ThemeDetailInstance> findByThemeDetId(long id);
	
	public ThemeDetailInstance findById(long theId);

	public void save(ThemeDetailInstance theThemeDetailInstance);

	public void deleteById(long theId);
	public List<ThemeDetailInstance> getModuleThemeDetails(long id);
}