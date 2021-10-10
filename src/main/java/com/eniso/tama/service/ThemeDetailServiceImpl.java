package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.eniso.tama.entity.ModuleInstance;
import com.eniso.tama.entity.ThemeDetail;
import com.eniso.tama.entity.ThemeDetailInstance;
import com.eniso.tama.repository.ThemeDetailRepository;


@Service
@ComponentScan(basePackageClasses = ThemeDetailRepository.class )
public class ThemeDetailServiceImpl implements ThemeDetailService {
	
	@Autowired
	private ThemeDetailRepository themeDetailRepository;
    
	@Autowired
	private ModuleInstanceService moduleInstanceService;
	
	@Autowired
	private ThemeDetailInstanceService themeDetailInstService;
    
    
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
		}
		else {
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
		List<ThemeDetail> list= themeDetailRepository.findAll();
		List<ThemeDetail> list1= new ArrayList<>();
		for (ThemeDetail t : list ) {
			if (t.getModule().getId()== id) {
				 list1.add(t);
			}
			
		}
		return (list1);
	}

	@Override
	@Transactional
	public ThemeDetail addThemeDetail(ThemeDetail theThemeDetail) {
		long id=theThemeDetail.getModule().getId();
		List<ModuleInstance> list = moduleInstanceService.findByModuleId(id);
		
		ThemeDetail t = new ThemeDetail();
		t.setThemeDetailName(theThemeDetail.getThemeDetailName());
		t.setNbDaysThemeDetail(theThemeDetail.getNbDaysThemeDetail());
		t.setModule(theThemeDetail.getModule());
		
		ThemeDetail them=save(t);
		for (ModuleInstance modInstance : list) {
			ThemeDetailInstance themeInst= new ThemeDetailInstance();
			themeInst.setThemeDetail(them);
			themeInst.setModuleInstance(modInstance);
			themeInst.setThemeDetailInstName(theThemeDetail.getThemeDetailName());
			themeInst.setNbDaysthemeDetailInst(theThemeDetail.getNbDaysThemeDetail());
			themeDetailInstService.save(themeInst);
		}
		return theThemeDetail;
	}

	@Override
	@Transactional
	public ThemeDetail updateThemeDetail(ThemeDetail theThemeDetail) {
		long id=theThemeDetail.getId();
		List<ThemeDetailInstance> list= themeDetailInstService.findByThemeDetId(id);

		ThemeDetail newTheme = findById(id);
		newTheme.setNbDaysThemeDetail(theThemeDetail.getNbDaysThemeDetail());
		newTheme.setThemeDetailName(theThemeDetail.getThemeDetailName());
		
		for (ThemeDetailInstance themeInstance : list) {
			themeInstance.setThemeDetailInstName(theThemeDetail.getThemeDetailName());
			themeInstance.setNbDaysthemeDetailInst(theThemeDetail.getNbDaysThemeDetail());
			themeDetailInstService.save(themeInstance);
		}
		save(theThemeDetail);
		
		return theThemeDetail;
	}

}
