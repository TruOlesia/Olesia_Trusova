package com.ot.conferences.service;

import com.ot.conferences.model.Topic;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopicService {

    List<Topic> getAllTopicsByConferenceId(Long id, Pageable paging);

    Topic getTopic(Long id);

    Topic createOrUpdateTopic(Topic topic);

    void deleteTopic(Long id);
}
