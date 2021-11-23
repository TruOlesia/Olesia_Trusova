package com.ot.conferences.service;

import com.ot.conferences.model.Participant;

import java.util.List;

public interface ParticipantService {

    List<Participant> getAllParticipantByUserId(Long userId);
    List<Participant> getAllParticipantByConfId(Long confId);
}
