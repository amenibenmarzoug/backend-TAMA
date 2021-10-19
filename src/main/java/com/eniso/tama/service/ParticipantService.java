package com.eniso.tama.service;

import com.eniso.tama.entity.Participant;

import java.util.List;


public interface ParticipantService {


    public List<Participant> findAll();

    public Participant findById(long theId);

    public List<Participant> findByLevel(String theLevel);

    public List<Participant> findByEntreprise(Participant theParticipant);

    public List<Participant> getParticipantPilier2();

    public List<Participant> getParticipantPilier1();

    public List<Participant> findByAbonadn(boolean theAbondan);

    public List<Participant> findParticipantsByClass(long programInstId);

    public void save(Participant theParticipant);

    public List<Participant> getParticipantPerClass(long id);

    public float percentMascPart();

    public void deleteById(long theId);

}