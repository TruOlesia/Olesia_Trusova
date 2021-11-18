package com.ot.conferences.controller;

import com.ot.conferences.controller.dto.TopicDto;
import com.ot.conferences.service.TopicService;
import com.ot.conferences.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController

public class TopicController {

    @Autowired
    private TopicService topicService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/topics")
    public List<TopicDto> getAllTopics() {
        return topicService.getAllTopics()
                .stream()
                .map(this::mapTopicToTopicDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/topic/{id}")
    public TopicDto getTopic(@PathVariable int id) {
        return mapTopicToTopicDto(topicService.getTopic(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/topic")
    public TopicDto createTopic(@RequestBody TopicDto topicDto) {
        Topic topic = mapTopicDtoToTopic(topicDto);
        return mapTopicToTopicDto(topicService.createTopic(topic));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/topic/{id}")
    public TopicDto updateTopic(@PathVariable int id, @RequestBody TopicDto topicDto) {
        Topic topic = mapTopicDtoToTopic(topicDto);
        return mapTopicToTopicDto(topicService.updateTopic(id, topic));
    }

    @DeleteMapping(value = "/topic/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable int id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }


    private TopicDto mapTopicToTopicDto(Topic topic) {
        return TopicDto.builder()
                .id(topic.getId())
                .name(topic.getName())
                .date(topic.getDate())
                .status(topic.getStatus())
                .speakerId(topic.getSpeakerId())
                .conferenceId(topic.getConferenceId())
                .createdByUserId(topic.getCreatedByUserId())
                .build();
    }

    private Topic mapTopicDtoToTopic(TopicDto topicDto) {
        return Topic.builder()
                .id(topicDto.getId())
                .name(topicDto.getName())
                .date(topicDto.getDate())
                .status(topicDto.getStatus())
                .speakerId(topicDto.getSpeakerId())
                .conferenceId(topicDto.getConferenceId())
                .createdByUserId(topicDto.getCreatedByUserId())
                .build();
    }
}
