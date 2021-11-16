package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.ThemeDetail;

public interface ThemeDetailService {
	public List<ThemeDetail> findAll();
	
	public ThemeDetail findById(long theId);
	
	public ThemeDetail save(ThemeDetail themeDetail);
	
	public void deleteById(long id);
	
	public void deleteThemeDetail(long id);

	public List<ThemeDetail> findByModuleId(long id);
	public ThemeDetail addThemeDetail (ThemeDetail theThemeDetail);
	
	public ThemeDetail updateThemeDetail(ThemeDetail theThemeDetail);
}