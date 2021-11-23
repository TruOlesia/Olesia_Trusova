package com.ot.conferences.controller;
import com.ot.conferences.controller.dto.TopicDto;
import com.ot.conferences.exception.NotFoundException;
import com.ot.conferences.service.TopicService;
import com.ot.conferences.model.Topic;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/topics")
    public List<TopicDto> getAllTopics(@RequestParam Pageable paging) {
        return topicService.getAllTopics(paging)
                .stream()
                .map(t -> dozerBeanMapper.map(t, TopicDto.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/topic/{id}")
    public TopicDto getTopic(@PathVariable Long id) {
        Topic topic = topicService.getTopic(id);
        if(topic == null) {
            throw new NotFoundException("Invalid topic id : " + id);
        }
        return dozerBeanMapper.map(topicService.getTopic(id), TopicDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/topic")
    public TopicDto createTopic(@RequestBody @Valid TopicDto topicDto) {
        Topic topic = dozerBeanMapper.map(topicDto, Topic.class);
        if(topic == null) {
            throw new NotFoundException("Invalid topic");
        }
        return dozerBeanMapper.map(topicService.createOrUpdateTopic(topic), TopicDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/topic/{id}")
    public TopicDto updateTopic(@PathVariable Long id, @RequestBody @Valid TopicDto topicDto) {
        Topic topic = dozerBeanMapper.map(topicDto, Topic.class);
        if(topic == null || topic.getId() == null) {
            throw new NotFoundException("Invalid topic id : " + id);
        }
        return dozerBeanMapper.map(topicService.createOrUpdateTopic(topic), TopicDto.class);
    }

    @DeleteMapping(value = "/topic/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        Topic topic = topicService.getTopic(id);
        if(topic == null) {
            throw new NotFoundException("Invalid topic id : " + id);
        }
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

}
