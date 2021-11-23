package com.ot.conferences.service.impl;

import com.ot.conferences.model.Conference;
import com.ot.conferences.repository.ConferenceRepository;
import com.ot.conferences.service.ConferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Override
    public List<Conference> getAllConferences(Pageable paging) {
        Page<Conference> pagedResult = conferenceRepository.findAll(paging);
        log.info("get all conferences");
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Conference>();
        }
    }

    @Override
    public Conference getConference(Long id) {
        log.info("get conference by id {}", id);
        Optional<Conference> conference = conferenceRepository.findById(id);

        return conference.orElse(null);
    }

    @Override
    public Conference createOrUpdateConference(Conference conference) {
        log.info("create or update conference with id {}", conference.getId());

        return conferenceRepository.save(conference);
    }

    @Override
    public void deleteConference(Long id) {
        Optional<Conference> conference = conferenceRepository.findById(id);
        log.info("deleteConference by id {}", id);
        
        conferenceRepository.delete(conference.orElse(null));
    }


}
