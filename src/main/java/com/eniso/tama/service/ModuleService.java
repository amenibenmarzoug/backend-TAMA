package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.Module;

public interface ModuleService {
	public List<Module> findAll();

	public Module findById(long theId);

	public void save(Module module);

	public void deleteById(long id);

}
