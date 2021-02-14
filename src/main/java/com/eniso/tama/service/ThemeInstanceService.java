package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.ThemeInstance;

public interface ThemeInstanceService {
	public List<ThemeInstance> findAll();

	public ThemeInstance findById(long theId);

	public void save(ThemeInstance theThemeInstance);

	public void deleteById(long theId);
}
