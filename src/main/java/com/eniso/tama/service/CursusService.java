package com.eniso.tama.service;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;

import com.eniso.tama.entity.Cursus;

public interface CursusService {
	

	public List<Cursus> findAll();
	
	public Cursus findById(long theId);
	
	public void save(Cursus cursus);
	
	public void deleteById(long id);

}
