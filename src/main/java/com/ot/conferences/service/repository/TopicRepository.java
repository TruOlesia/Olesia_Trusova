package com.ot.conferences.service.repository;

import com.ot.conferences.service.model.Topic;

import java.util.List;

public interface TopicRepository {

    List<Topic> getAllTopics();

    Topic getTopic(int id);

    List<Topic> listTopics();

    Topic createTopic(Topic topic);

    Topic updateTopic(int id, Topic topic);

    void deleteTopic(int id);
}
