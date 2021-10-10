
package com.eniso.tama.service;

import java.util.List;


import com.eniso.tama.entity.ModuleInstance;

public interface ModuleInstanceService {

	public List<ModuleInstance> findAll();
	
	public List<ModuleInstance> findByModuleId(long id);

	public ModuleInstance findById(long theId);

	public ModuleInstance save(ModuleInstance module);

	public void deleteById(long id);
	
	public List<ModuleInstance> getThemeModules(long id);
}