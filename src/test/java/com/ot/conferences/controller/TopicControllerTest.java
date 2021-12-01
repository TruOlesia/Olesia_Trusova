package com.ot.conferences.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ot.conferences.controller.dto.ConferenceDto;
import com.ot.conferences.controller.dto.TopicDto;
import com.ot.conferences.model.Conference;
import com.ot.conferences.model.Topic;
import com.ot.conferences.service.ConferenceService;
import com.ot.conferences.service.ParticipantService;
import com.ot.conferences.service.TopicService;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = TopicController.class)
@AutoConfigureMockMvc
class TopicControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicService topicService;

    @MockBean
    private DozerBeanMapper dozerBeanMapper;

    @Mock
    private Pageable paging;

    private final static Long MOCK_ID = 1L;
    private final static Long MOCK_ID_2 = 2L;

    @Test
    void getAllTopicsTest() throws Exception {
        Topic topic = Topic.builder().name("TESTNAME").build();

        TopicDto topicDto = TopicDto.builder().name("TESTNAME").build();

        List<Topic> topicList = new ArrayList<>();
        topicList.add(topic);
        List<TopicDto> topicDtoList = new ArrayList<>();
        topicDtoList.add(topicDto);
        when(topicService.getAllTopics(paging)).thenReturn(topicList);
        when(dozerBeanMapper.map(topic, TopicDto.class)).thenReturn(topicDto);

        mockMvc.perform(get("/topics"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getTopicTest() throws Exception {
        Topic topic = Topic.builder().id(MOCK_ID).name("TESTNAME").build();

        TopicDto topicDto = TopicDto.builder().id(MOCK_ID).name("TESTNAME").build();

        when(topicService.getTopic(MOCK_ID)).thenReturn(topic);
        when(dozerBeanMapper.map(topic, TopicDto.class)).thenReturn(topicDto);

        mockMvc.perform(get("/topic/" + MOCK_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createTopicTest() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = formatter.parse("2022-02-02 00:00");
        Topic topic = Topic.builder().id(MOCK_ID).name("TESTNAME").date(date).build();

        TopicDto topicDto = TopicDto.builder().id(MOCK_ID).name("TESTNAME").date(date).build();

        when(topicService.createOrUpdateTopic(topic)).thenReturn(topic);
        when(dozerBeanMapper.map(topicDto, Topic.class)).thenReturn(topic);
        when(dozerBeanMapper.map(topic, TopicDto.class)).thenReturn(topicDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/topic")
                        .content(asJsonString(topicDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    void updateTopicTest() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = formatter.parse("2022-02-02 00:00");
        Topic topic = Topic.builder().id(MOCK_ID).name("TESTNAME").date(date).build();

        TopicDto topicDto = TopicDto.builder().id(MOCK_ID).name("TESTNAME").date(date).build();

        when(topicService.createOrUpdateTopic(topic)).thenReturn(topic);
        when(dozerBeanMapper.map(topicDto, Topic.class)).thenReturn(topic);
        when(dozerBeanMapper.map(topic, TopicDto.class)).thenReturn(topicDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/topic/{id}", MOCK_ID)
                        .content(asJsonString(topicDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteConferenceTest() throws Exception{
        //given
        Topic topic = Topic.builder().id(MOCK_ID).name("TESTNAME").build();
        doNothing().when(topicService).deleteTopic(MOCK_ID);
        when(topicService.getTopic(MOCK_ID)).thenReturn(topic);
        //when
        mockMvc.perform(delete("/topic/" + MOCK_ID))
                .andExpect(status().isNoContent());

        //then
        verify(topicService, times(1)).deleteTopic(MOCK_ID);
    }
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createTopicValidationNameTest() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = formatter.parse("2022-02-02 00:00");
        Topic topic = Topic.builder().id(MOCK_ID).date(date).build();

        TopicDto topicDto = TopicDto.builder().id(MOCK_ID).date(date).build();

        when(topicService.createOrUpdateTopic(topic)).thenReturn(topic);
        when(dozerBeanMapper.map(topicDto, Topic.class)).thenReturn(topic);
        when(dozerBeanMapper.map(topic, TopicDto.class)).thenReturn(topicDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/topic")
                        .content(asJsonString(topicDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createTopicValidationDateTest() throws Exception {

        Topic topic = Topic.builder().id(MOCK_ID).name("Test").build();

        TopicDto topicDto = TopicDto.builder().id(MOCK_ID).name("test").build();

        when(topicService.createOrUpdateTopic(topic)).thenReturn(topic);
        when(dozerBeanMapper.map(topicDto, Topic.class)).thenReturn(topic);
        when(dozerBeanMapper.map(topic, TopicDto.class)).thenReturn(topicDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/topic")
                        .content(asJsonString(topicDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteTopic_Exception_Test() throws Exception{
        //given
        Topic topic = null;
        doNothing().when(topicService).deleteTopic(MOCK_ID);
        when(topicService.getTopic(MOCK_ID)).thenReturn(topic);
        //then
        mockMvc.perform(delete("/topic/" + MOCK_ID))
                .andExpect(status().isNotFound());

    }

    @Test
    void updateTopic_Exception_Test() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = formatter.parse("2022-02-02 00:00");
        Topic topic = null;

        TopicDto topicDto = TopicDto.builder().id(MOCK_ID_2).name("TESTNAME").date(date).build();

        when(topicService.createOrUpdateTopic(topic)).thenReturn(topic);
        when(dozerBeanMapper.map(topicDto, Topic.class)).thenReturn(topic);
        when(dozerBeanMapper.map(topic, TopicDto.class)).thenReturn(topicDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/topic/{id}", MOCK_ID)
                        .content(asJsonString(topicDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createTopic_Exception_Test() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = formatter.parse("2022-02-02 00:00");
        Topic topic = null;

        TopicDto topicDto = TopicDto.builder().id(MOCK_ID).name("TESTNAME").date(date).build();

        when(topicService.createOrUpdateTopic(topic)).thenReturn(topic);
        when(dozerBeanMapper.map(topicDto, Topic.class)).thenReturn(topic);
        when(dozerBeanMapper.map(topic, TopicDto.class)).thenReturn(topicDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/topic")
                        .content(asJsonString(topicDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTopic_Exception_Test() throws Exception {
        Topic topic = null;

        TopicDto topicDto = TopicDto.builder().id(MOCK_ID).name("TESTNAME").build();

        when(topicService.getTopic(MOCK_ID)).thenReturn(topic);
        when(dozerBeanMapper.map(topic, TopicDto.class)).thenReturn(topicDto);

        mockMvc.perform(get("/topic/" + MOCK_ID))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


}