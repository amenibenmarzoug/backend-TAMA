package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Theme;

import com.eniso.tama.repository.ThemeRepository;

@Service
@ComponentScan(basePackageClasses = ThemeRepository.class)
public class ThemeServiceImpl implements ThemeService {
    private ThemeRepository themeRepository;


    public ThemeServiceImpl(ThemeRepository themeRepository) {
        super();
        this.themeRepository = themeRepository;
    }

    @Override
    public List<Theme> findAll() {

        return themeRepository.findAll();
    }

    @Override
    public Theme findById(long theId) {
        Optional<Theme> result = themeRepository.findById(theId);
        Theme theControl = null;

        if (result.isPresent()) {
            theControl = result.get();
        } else {
            // we didn't find the theme
            throw new RuntimeException("Did not find Theme  id - " + theId);
        }

        return theControl;
    }

    @Override
    public void save(Theme theme) {
        themeRepository.save(theme);

    }

    @Override
    public void deleteById(long id) {
        themeRepository.deleteById(id);

    }

    @Override
    public List<Theme> findByProgId(long id) {
        List<Theme> list = themeRepository.findAll();
        List<Theme> list1 = new ArrayList<>();
        for (Theme t : list) {
            if (t.getProgram().getId() == id) {
                list1.add(t);
            }

        }
        return (list1);
    }

}
