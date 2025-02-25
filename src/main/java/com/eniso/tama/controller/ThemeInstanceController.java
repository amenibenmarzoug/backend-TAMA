package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Program;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Theme;
import com.eniso.tama.entity.ThemeInstance;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.ProgramInstanceRepository;
import com.eniso.tama.repository.ProgramRepository;
import com.eniso.tama.service.ThemeInstanceService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = ThemeInstanceService.class)
@RequestMapping(value = "/api")
public class ThemeInstanceController {


    @Autowired
    private ThemeInstanceService themeInstanceService;


    public ThemeInstanceController(ThemeInstanceService themeInstanceService) {
        super();
        this.themeInstanceService = themeInstanceService;
    }

    @GetMapping("/themesInst")
    public List<ThemeInstance> findAll() {
        return themeInstanceService.findAll();
    }
    @GetMapping("/program/themesInst")
    public List<ThemeInstance> getProgramThemesInst(@RequestParam("id") long id) {
        System.out.println(id);

        return(themeInstanceService.getProgramThemesInst(id));


    }

    @GetMapping("themeInst/{themeId}")
    public ThemeInstance getThemeInst(@PathVariable long themeInstId) {

        ThemeInstance theThemeInst = themeInstanceService.findById(themeInstId);

        if (theThemeInst == null) {
            throw new RuntimeException("themeInst not found - " + themeInstId);
        }

        return theThemeInst;
    }


    @PostMapping("/themeInst")
    public ThemeInstance addThemeInst(@RequestBody ThemeInstance thethemeInst) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        // stheControl.setId(0);

        themeInstanceService.save(thethemeInst);
        return thethemeInst;
    }
    @PostMapping("/themeProgramInst")
    public ResponseEntity<?> addThemeProgram(@Valid @RequestBody ThemeInstance theme,@RequestParam("id") long id ) {

        return(themeInstanceService.addThemeProgram(theme,id));

    }


    @PutMapping("/themeInst")
    public ThemeInstance updateThemeInst(@RequestBody ThemeInstance theThemeInst) {

        return(themeInstanceService.updateThemeInst(theThemeInst));


    }

    @DeleteMapping("themeInst/{themeId}")
    public String deleteThemeInst(@PathVariable int themInstId) {

        ThemeInstance themeInst = themeInstanceService.findById(themInstId);

        // throw exception if null

        if (themeInst == null) {
            throw new RuntimeException("the ThemeInst id is not found - " + themInstId);
        }

        themeInstanceService.deleteById(themInstId);

        return "Deleted programId- " + themInstId;
    }


}