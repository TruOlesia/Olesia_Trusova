package com.ot.conferences.service;

import com.ot.conferences.model.Conference;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ConferenceService {

    List<Conference> getAllConferences(Pageable paging);

    Conference getConference(Long id);

    Conference createOrUpdateConference(Conference conference);

    void deleteConference(Long id);

}
