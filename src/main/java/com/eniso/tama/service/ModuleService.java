package com.eniso.tama.service;

import java.util.List;


import com.eniso.tama.entity.Module;

public interface ModuleService {
    public List<Module> findAll();

    public Module findById(long theId);

    public Module save(Module module);

    public void deleteById(long id);

	public List<Module> findModulesByThemeId(long id);
	
	public List<String> getModulesNames();
	
	public Module updateModule(Module theModule) ;
	
	public List<String> findDistinctModuleNameByThemes(List<String> themes);

}
