package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Theme;
import com.eniso.tama.entity.ThemeDetail;
import com.eniso.tama.repository.ThemeDetailRepository;


@Service
@ComponentScan(basePackageClasses = ThemeDetailRepository.class)
public class ThemeDetailServiceImpl implements ThemeDetailService {

    private ThemeDetailRepository themeDetailRepository;


    @Autowired
    public ThemeDetailServiceImpl(ThemeDetailRepository themeDetailRepository) {
        //super();
        this.themeDetailRepository = themeDetailRepository;
    }

    @Override
    public List<ThemeDetail> findAll() {
        return themeDetailRepository.findAll();
    }

    @Override
    public ThemeDetail findById(long theId) {
        Optional<ThemeDetail> result = themeDetailRepository.findById(theId);

        ThemeDetail theControl = null;

        if (result.isPresent()) {
            theControl = result.get();
        } else {
            // we didn't find the theme Detail
            throw new RuntimeException("Did not find THeme Detail id - " + theId);
        }

        return theControl;
    }

    @Override
    public ThemeDetail save(ThemeDetail themeDetail) {
        return themeDetailRepository.save(themeDetail);

    }

    @Override
    public void deleteById(long id) {
        themeDetailRepository.deleteById(id);

    }

    @Override
    public List<ThemeDetail> findByModuleId(long id) {
        List<ThemeDetail> list = themeDetailRepository.findAll();
        List<ThemeDetail> list1 = new ArrayList<>();
        for (ThemeDetail t : list) {
            if (t.getModule().getId() == id) {
                list1.add(t);
            }

        }
        return (list1);
    }

}
