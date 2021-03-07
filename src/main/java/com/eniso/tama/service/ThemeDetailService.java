package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.ThemeDetail;

public interface ThemeDetailService {
	public List<ThemeDetail> findAll();
	
	public ThemeDetail findById(long theId);
	
	public void save(ThemeDetail themeDetail);
	
	public void deleteById(long id);
}
