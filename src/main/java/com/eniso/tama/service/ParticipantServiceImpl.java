package com.eniso.tama.service;

import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.entity.Status;
import com.eniso.tama.repository.ParticipantRegistrationRepository;
import com.eniso.tama.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@ComponentScan(basePackageClasses = ParticipantRepository.class)

public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private ParticipantRegistrationRepository participantRegistrationRepository;

    public ParticipantServiceImpl(ParticipantRepository theParticipantRepository) {
        participantRepository = theParticipantRepository;
    }

    @Override
    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    @Override
    public Participant findById(long theId) {
        Optional<Participant> result = participantRepository.findById(theId);

        Participant theControl = null;

        if (result.isPresent()) {
            theControl = result.get();
        } else {
            // we didn't find the participant
            throw new RuntimeException("Did not find participant id - " + theId);
        }

        return theControl;
    }

    //find By level
    @Override
    public List<Participant> findByLevel(String theLevel) {


        return participantRepository.findByLevel(theLevel);

    }

    //find by Company
    @Override

    public List<Participant> findByEntreprise(Participant theParticipant) {

        List<Participant> p1 = null;


        for (Participant theP : participantRepository.findAll()) {


            if (theP.getEntreprise() != null) {

                p1.add(theP);

            }

        }
        return p1;

    }

    @Override
    public List<Participant> getParticipantPilier2() {
        List<Participant> participants = new ArrayList<Participant>();

        for (Participant theP : participantRepository.findAll()) {

            if (theP.getEntreprise() != null) {

                participants.add(theP);

            }

        }
        return participants;
    }

    @Override
    public List<Participant> getParticipantPilier1() {
        List<Participant> participants = new ArrayList<Participant>();

        for (Participant theP : participantRepository.findAll()) {

            if (theP.getEntreprise() == null) {

                participants.add(theP);

            }

        }
        return participants;
    }

    //find by abondan
    @Override
    public List<Participant> findByAbonadn(boolean theAbondan) {

        return participantRepository.findByAbandon(theAbondan);
    }

    @Override
    public void save(Participant theParticipant) {
        participantRepository.save(theParticipant);
    }


    @Override
    public void deleteById(long theId) {


        participantRepository.deleteById(theId);
    }

    //get Confirmed Participants by ProgramInst
    @Override
    public List<Participant> findParticipantsByClass(long programInstId) {

        List<Long> partcipantsIdsList = participantRepository.findConfirmedParticipantsByClass(programInstId);
        List<Participant> participants = new ArrayList<>();
        for (long ParticipantID : partcipantsIdsList) {
            Optional<Participant> theP = participantRepository.findById(ParticipantID);
            if (theP.isPresent()) {
                participants.add(theP.get());

            }
        }
        return participants;

    }


    @Override
    public List<Participant> getParticipantPerClass(long id) {
        List<Participant> participantsPerClasse = new ArrayList<Participant>();
        for (Participant theP : findAll()) {
            for (ParticipantRegistration reg : participantRegistrationRepository.findByParticipantId(theP.getId()))
                if (reg.getPrograminstance().getId() == id && theP.getStatus().equals(Status.ACCEPTED)) {
                    participantsPerClasse.add(theP);

                }
        }
        return participantsPerClasse;
    }

    @Override
    public float percentMascPart() {

        return participantRepository.getMaleParticipants();
    }


}