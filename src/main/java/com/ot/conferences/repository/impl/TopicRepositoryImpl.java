package com.ot.conferences.repository.impl;

import com.ot.conferences.model.Topic;
import com.ot.conferences.repository.TopicRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicRepositoryImpl implements TopicRepository {

    private final List<Topic> list = new ArrayList<>();

    @Override
    public List<Topic> getAllTopics() {
        return new ArrayList<>(list);
    }

    @Override
    public Topic getTopic(int id) {
        return list.stream()
                .filter(topic -> topic.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Topic is not found!"));
    }

    @Override
    public List<Topic> listTopics() {
        return new ArrayList<>(list);
    }

    @Override
    public Topic createTopic(Topic topic) {
        list.add(topic);
        return topic;
    }

    @Override
    public Topic updateTopic(int id, Topic topic) {
        boolean isDeleted = list.removeIf(u -> u.getId() == id);
        if (isDeleted) {
            list.add(topic);
        } else {
            throw new RuntimeException("Topic is not found!");
        }
        return topic;
    }

    @Override
    public void deleteTopic(int id) {
        list.removeIf(topic -> topic.getId() == id);
    }
}
