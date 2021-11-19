package com.ot.conferences.repository.impl;

import com.ot.conferences.model.Conference;

import com.ot.conferences.repository.ConferenceRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConferenceRepositoryImpl implements ConferenceRepository {

    private final List<Conference> list = new ArrayList<>();

    @Override
    public List<Conference> getAllConferences() {
        return new ArrayList<>(list);
    }

    @Override
    public Conference getConference(int id) {

        return list.stream()
                .filter(conference -> conference.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Conference> listConferences() {
        return new ArrayList<>(list);
    }

    @Override
    public Conference createConference(Conference conference) {
        list.add(conference);
        return conference;
    }

    @Override
    public Conference updateConference(int id, Conference conference) {
        boolean isDeleted = list.removeIf(u -> u.getId() == id);
        if (isDeleted) {
            list.add(conference);
        } else {
            return null;
        }
        return conference;
    }

    @Override
    public void deleteConference(int id) {
        list.removeIf(conference -> conference.getId() == id);
    }
}
