package com.ot.conferences.service.impl;

import com.ot.conferences.service.TopicService;
import com.ot.conferences.service.model.Topic;
import com.ot.conferences.service.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service

public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<Topic> getAllTopics() {
        log.info("get all topics");
        return topicRepository.getAllTopics();
    }

    @Override
    public Topic getTopic(int id) {
        log.info("get topic by id {}", id);
        Topic topic = topicRepository.getTopic(id);
        return topic;
    }

    @Override
    public Topic createTopic(Topic topic) {
        log.info("create topic with id {}", topic.getId());
        return topicRepository.createTopic(topic);
    }

    @Override
    public Topic updateTopic(int id, Topic topic) {
        log.info("update conference with id {}", id);
        return topicRepository.updateTopic(id, topic);
    }

    @Override
    public void deleteTopic(int id) {
        log.info("deleteTopic with id {}", id);
        topicRepository.deleteTopic(id);
    }


}
