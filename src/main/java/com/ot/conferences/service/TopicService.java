package com.ot.conferences.service;

import com.ot.conferences.controller.dto.TopicDto;
import com.ot.conferences.service.model.Topic;

import java.util.List;

public interface TopicService {

    List<Topic> getAllTopics();

    Topic getTopic(int id);

    Topic createTopic(Topic topic);

    Topic updateTopic(int id, Topic topic);

    void deleteTopic(int id);
}
