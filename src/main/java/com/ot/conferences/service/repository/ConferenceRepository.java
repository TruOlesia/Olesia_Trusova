package com.ot.conferences.service.repository;

import com.ot.conferences.service.model.Conference;
import com.ot.conferences.service.model.User;

import java.util.List;

public interface ConferenceRepository {

    List<Conference> getAllConferences();

    Conference getConference(int id);

    List<Conference> listConferences();

    Conference createConference(Conference conference);

    Conference updateConference(int id, Conference conference);

    void deleteConference(int id);
}
