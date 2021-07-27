package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import com.eniso.tama.entity.Participant;


public interface ParticipantService {


    public List<Participant> findAll();

    public Participant findById(long theId);

    public List<Participant> findByLevel(String theLevel);

    public List<Participant> findByEntreprise(Participant theParticipant);

    //public List<Participant> findByGroup(Participant theParticipant);
    public List<Participant> findByAbonadn(boolean theAbondan);

    //public List<Participant> findByGroup(long id);


    public void save(Participant theParticipant);

    public void deleteById(long theId);

}
