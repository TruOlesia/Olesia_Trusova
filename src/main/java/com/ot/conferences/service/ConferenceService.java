package com.ot.conferences.service;

import com.ot.conferences.model.Conference;

import java.util.List;

public interface ConferenceService {

    List<Conference> getAllConferences();

    Conference getConference(int id);

    Conference createConference(Conference conference);

    Conference updateConference(int id, Conference conference);

    void deleteConference(int id);

}
