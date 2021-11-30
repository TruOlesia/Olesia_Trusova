package com.ot.conferences.service.impl;

import com.ot.conferences.model.Topic;
import com.ot.conferences.model.User;
import com.ot.conferences.repository.TopicRepository;
import com.ot.conferences.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopicServiceImplTest {

    @InjectMocks
    private TopicServiceImpl topicService;

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private Pageable paging;

    @Mock
    Page<Topic> pageResult;

    private final static Long MOCK_ID = 1L;

    @Test
    void getTopicTest() {
        //given
        Topic expectedTopic = Topic.builder().id(MOCK_ID).build();
        Optional<Topic> optionalTopic = Optional.of(expectedTopic);
        when(topicRepository.findById(MOCK_ID)).thenReturn(optionalTopic);

        //when
        Topic actualTopic = topicService.getTopic(MOCK_ID);

        //then
        assertEquals(expectedTopic.getId(), actualTopic.getId());
    }

    @Test
    void deleteTopicTest() {

        //given
        Topic expectedTopic = Topic.builder().id(MOCK_ID).build();
        Optional<Topic> optionalTopic = Optional.of(expectedTopic);
        when(topicRepository.findById(MOCK_ID)).thenReturn(optionalTopic);
        doNothing().when(topicRepository).delete(expectedTopic);
        //when
        topicService.deleteTopic(MOCK_ID);

        //then
        verify(topicRepository, times(1)).delete(expectedTopic);
    }

    @Test
    public void createOrUpdateTopicTest() {
        //given
        Topic expectedTopic = Topic.builder().id(MOCK_ID).build();
        when(topicRepository.save(expectedTopic)).thenReturn(expectedTopic);

        //when
        Topic actualTopic = topicService.createOrUpdateTopic(expectedTopic);

        //then
        assertEquals(expectedTopic.getId(), actualTopic.getId());

    }

    @Test
    public void getAllTopicsTest() {

        //given
        Topic expectedTopic = Topic.builder().id(MOCK_ID).build();
        List<Topic> topicList = new ArrayList<>();
        topicList.add(expectedTopic);
        when(topicRepository.findAll(paging)).thenReturn(pageResult);
        when(pageResult.hasContent()).thenReturn(true);
        when(pageResult.getContent()).thenReturn(topicList);
        //when
        List<Topic> topics = topicService.getAllTopics(paging);

        //then
        assertThat(topics, hasSize(1));
    }

    @Test
    public void getAllTopicsFalseTest() {

        //given
        when(topicRepository.findAll(paging)).thenReturn(pageResult);
        when(pageResult.hasContent()).thenReturn(false);

        //when
        List<Topic> topics = topicService.getAllTopics(paging);

        //then
        assertThat(topics, hasSize(0));
    }
}