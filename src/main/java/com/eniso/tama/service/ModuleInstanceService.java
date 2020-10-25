package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.ModuleInstance;

public interface ModuleInstanceService {

	public List<ModuleInstance> findAll();

	public ModuleInstance findById(long theId);

	public void save(ModuleInstance module);

	public void deleteById(long id);
}
