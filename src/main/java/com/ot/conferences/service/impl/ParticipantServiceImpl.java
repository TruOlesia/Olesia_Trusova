package com.ot.conferences.service.impl;

import com.ot.conferences.model.Participant;
import com.ot.conferences.repository.ParticipantRepository;
import com.ot.conferences.service.ParticipantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public List<Participant> getAllParticipantByUserId(Long userId) {
        log.info("get list participants by user id");
        return participantRepository.findParticipantByUserId();
    }

    @Override
    public List<Participant> getAllParticipantByConfId(Long confId) {
        log.info("get list participants by conference id");
        return participantRepository.findParticipantByConfId();
    }
}
