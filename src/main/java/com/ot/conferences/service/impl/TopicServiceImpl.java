package com.ot.conferences.service.impl;

import com.ot.conferences.model.Topic;
import com.ot.conferences.repository.TopicRepository;
import com.ot.conferences.service.TopicService;
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

public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<Topic> getAllTopics(Pageable paging) {
        Page<Topic> pagedResult = topicRepository.findAll(paging);
        log.info("get all topics");

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Topic>();
        }
    }

    @Override
    public Topic getTopic(Long id) {
        log.info("get topic by id {}", id);
        Optional<Topic> topic = topicRepository.findById(id);
        return topic.orElse(null);
    }

    @Override
    public Topic createOrUpdateTopic(Topic topic) {
        log.info("create topic with id {}", topic.getId());
        return topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        log.info("deleteTopic with id {}", id);
        topicRepository.delete(topic.orElse(null));
    }


}
