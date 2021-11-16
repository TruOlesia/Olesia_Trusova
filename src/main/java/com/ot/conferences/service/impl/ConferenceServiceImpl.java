package com.ot.conferences.service.impl;

import com.ot.conferences.controller.dto.ConferenceDto;
import com.ot.conferences.controller.dto.UserDto;
import com.ot.conferences.service.ConferenceService;
import com.ot.conferences.service.model.Conference;
import com.ot.conferences.service.repository.ConferenceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceRepository conferenceRepository;

    @Override
    public List<Conference> getAllConferences() {
        log.info("get all conferences");
        return conferenceRepository.getAllConferences()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Conference getConference(int id) {
        log.info("get conference by id {}", id);
        Conference conference = conferenceRepository.getConference(id);
        return conference;
    }

    @Override
    public Conference createConference(Conference conference) {
        log.info("create conference with id {}", conference.getId());
        return conferenceRepository.createConference(conference);
    }

    @Override
    public Conference updateConference(int id, Conference conference) {
        log.info("update conference with id {}", id);
        return conferenceRepository.updateConference(id, conference);
    }

    @Override
    public void deleteConference(int id) {
        log.info("deleteConference by id {}", id);
        conferenceRepository.deleteConference(id);
    }


}
