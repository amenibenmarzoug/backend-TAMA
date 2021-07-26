package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Module;
import com.eniso.tama.entity.ModuleInstance;
import com.eniso.tama.entity.Program;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Theme;
import com.eniso.tama.entity.ThemeInstance;
import com.eniso.tama.service.ModuleInstanceService;
import com.eniso.tama.service.ModuleService;
import com.eniso.tama.service.ThemeInstanceService;

@RestController
@ComponentScan(basePackageClasses = ModuleService.class)
@RequestMapping(value = "/api")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ModuleInstanceService moduleInstService;

    @Autowired
    private ThemeInstanceService themeInstService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping("/module")
    public List<Module> findAll() {
        return moduleService.findAll();
    }

    @GetMapping("/theme/modules")
    public List<Module> getThemeModules(@RequestParam("id") long id) {
        List<Module> modulesPerTheme = new ArrayList<Module>();
        for (Module theM : moduleService.findAll()) {
            if (theM.getTheme() != null) {
                if (id == theM.getTheme().getId()) {

                    modulesPerTheme.add(theM);
                }
            }
        }
        return modulesPerTheme;
    }

    @GetMapping("module/{moduleId}")
    public Module getModule(@PathVariable int moduleId) {

        Module module = moduleService.findById(moduleId);

        if (module == null) {
            throw new RuntimeException("module id not found - " + moduleId);
        }

        return module;
    }
    // add mapping for POST /Module - add new Module

    @Transactional
    @PostMapping("/module")
    public Module addModule(@RequestBody Module module) {
        long id = module.getTheme().getId();
        List<ThemeInstance> list = themeInstService.findByThemeId(id);

        Module mod = moduleService.save(module);

        for (ThemeInstance themeInstance : list) {
            ModuleInstance modInst = new ModuleInstance();
            modInst.setModule(mod);
            modInst.setModuleInstanceName(mod.getModuleName());
            modInst.setNbDaysModuleInstance(mod.getNbDaysModule());
            modInst.setThemeInstance(themeInstance);
            moduleInstService.save(modInst);
        }

        return module;
    }

    // add mapping for PUT /module - update existing module

    @Transactional
    @PutMapping("/module")
    public Module updateModule(@RequestBody Module theModule) {
        long id = theModule.getId();
        List<ModuleInstance> list = moduleInstService.findByModuleId(id);

        Module module = moduleService.findById(id);
        module.setModuleName(theModule.getModuleName());
        module.setNbDaysModule(theModule.getNbDaysModule());
        module.setTheme(theModule.getTheme());
        moduleService.save(module);
        for (ModuleInstance moduleInstance : list) {
            moduleInstance.setModuleInstanceName(theModule.getModuleName());
            moduleInstance.setNbDaysModuleInstance(theModule.getNbDaysModule());
            moduleInstService.save(moduleInstance);
        }
        return module;
    }

    @DeleteMapping("/module/{moduleId}")
    public String deleteModule(@PathVariable int moduleId) {

        Module module = moduleService.findById(moduleId);

        // throw exception if null

        if (module == null) {
            throw new RuntimeException("the Module id is not found - " + moduleId);
        }

        moduleService.deleteById(moduleId);

        return "Deleted Module id - " + moduleId;
    }
}
