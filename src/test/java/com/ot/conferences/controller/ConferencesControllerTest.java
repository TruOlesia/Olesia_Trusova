package com.ot.conferences.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ot.conferences.controller.dto.ConferenceDto;
import com.ot.conferences.model.Conference;
import com.ot.conferences.service.ConferenceService;
import com.ot.conferences.service.ParticipantService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ConferencesController.class)
@AutoConfigureMockMvc
class ConferencesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConferenceService conferenceService;

    @MockBean
    private ParticipantService participantService;

    @MockBean
    private DozerBeanMapper dozerBeanMapper;

    @Mock
    private Pageable paging;

    private final static Long MOCK_ID = 1L;
    private final static Long MOCK_ID_2 = 2L;


    @Test
    void getAllConferencesTest() throws Exception {
        Conference conference = Conference.builder().name("TESTNAME").build();

        ConferenceDto conferenceDto = ConferenceDto.builder().name("TESTNAME").build();

        List<Conference> conferenceList = new ArrayList<>();
        conferenceList.add(conference);
        List<ConferenceDto> conferenceDtoList = new ArrayList<>();
        conferenceDtoList.add(conferenceDto);
        when(conferenceService.getAllConferences(paging)).thenReturn(conferenceList);
        when(conferenceList.stream().map(c -> dozerBeanMapper.map(c, ConferenceDto.class)).collect(Collectors.toList())).thenReturn(conferenceDtoList);

        mockMvc.perform(get("/conference"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getConferenceTest() throws Exception {
        Conference conference = Conference.builder().id(MOCK_ID).name("TESTNAME").build();

        ConferenceDto conferenceDto = ConferenceDto.builder().id(MOCK_ID).name("TESTNAME").build();

        when(conferenceService.getConference(MOCK_ID)).thenReturn(conference);
        when(dozerBeanMapper.map(conference, ConferenceDto.class)).thenReturn(conferenceDto);

        mockMvc.perform(get("/conference/" + MOCK_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createConferenceTest() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = formatter.parse("2022-02-02 00:00");
        Conference conference = Conference.builder().id(MOCK_ID).name("TESTNAME").location("test").startDate(startDate).build();

        ConferenceDto conferenceDto = ConferenceDto.builder().id(MOCK_ID).name("TESTNAME").location("test").startDate(startDate).build();

        when(conferenceService.createOrUpdateConference(conference)).thenReturn(conference);
        when(dozerBeanMapper.map(conference, ConferenceDto.class)).thenReturn(conferenceDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/conference")
                .content(asJsonString(conferenceDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateConferenceTest() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = formatter.parse("2022-02-02 00:00");
        Conference conference = Conference.builder().id(MOCK_ID).name("TESTNAME").location("test").startDate(startDate).build();

        ConferenceDto conferenceDto = ConferenceDto.builder().id(MOCK_ID).name("TESTNAME").location("test").startDate(startDate).build();

        when(conferenceService.createOrUpdateConference(conference)).thenReturn(conference);
        when(dozerBeanMapper.map(conference, ConferenceDto.class)).thenReturn(conferenceDto);
        when(dozerBeanMapper.map(conferenceDto, Conference.class)).thenReturn(conference);
        mockMvc.perform(put("/conference/{id}", MOCK_ID)
                        .content(asJsonString(conferenceDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteConferenceTest() throws Exception{
        //given
        Conference conference = Conference.builder().id(MOCK_ID).name("TESTNAME").location("test").build();
        doNothing().when(conferenceService).deleteConference(MOCK_ID);
        when(conferenceService.getConference(MOCK_ID)).thenReturn(conference);
        //when
        mockMvc.perform(delete("/conference/" + MOCK_ID))
                .andExpect(status().isNoContent());

        //then
        verify(conferenceService, times(1)).deleteConference(MOCK_ID);
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
    void getConference_expectException_onError() throws Exception {
        Conference conference = null;

        ConferenceDto conferenceDto = ConferenceDto.builder().id(MOCK_ID).name("TESTNAME").build();

        when(conferenceService.getConference(MOCK_ID)).thenReturn(conference);
        when(dozerBeanMapper.map(conference, ConferenceDto.class)).thenReturn(conferenceDto);

        mockMvc.perform(get("/conference/" + MOCK_ID))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateConference_expectException_onError() throws Exception {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = formatter.parse("2022-02-02 00:00");
        Conference conference = Conference.builder().id(MOCK_ID_2).name("TESTNAME").location("test").startDate(startDate).build();

        ConferenceDto conferenceDto = ConferenceDto.builder().id(MOCK_ID_2).name("TESTNAME").location("test").startDate(startDate).build();

        when(conferenceService.createOrUpdateConference(conference)).thenReturn(conference);
        when(dozerBeanMapper.map(conference, ConferenceDto.class)).thenReturn(conferenceDto);
        when(dozerBeanMapper.map(conferenceDto, Conference.class)).thenReturn(conference);

        mockMvc.perform(put("/conference/{id}", MOCK_ID)
                        .content(asJsonString(conferenceDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteConference_expectException_onError_Test() throws Exception{
        //given
        Conference conference = null;
        doNothing().when(conferenceService).deleteConference(MOCK_ID);
        when(conferenceService.getConference(MOCK_ID)).thenReturn(conference);
        //when
        mockMvc.perform(delete("/conference/" + MOCK_ID))
                .andExpect(status().isNotFound());

    }

    @Test
    void createConferenceValidationNameTest() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = formatter.parse("2022-02-02 00:00");
        Conference conference = Conference.builder().id(MOCK_ID).location("test").startDate(startDate).build();

        ConferenceDto conferenceDto = ConferenceDto.builder().id(MOCK_ID).location("test").startDate(startDate).build();

        when(conferenceService.createOrUpdateConference(conference)).thenReturn(conference);
        when(dozerBeanMapper.map(conference, ConferenceDto.class)).thenReturn(conferenceDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/conference")
                        .content(asJsonString(conferenceDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createConferenceValidationLocationTest() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = formatter.parse("2022-02-02 00:00");
        Conference conference = Conference.builder().id(MOCK_ID).name("TESTNAME").startDate(startDate).build();

        ConferenceDto conferenceDto = ConferenceDto.builder().id(MOCK_ID).name("TESTNAME").startDate(startDate).build();

        when(conferenceService.createOrUpdateConference(conference)).thenReturn(conference);
        when(dozerBeanMapper.map(conference, ConferenceDto.class)).thenReturn(conferenceDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/conference")
                        .content(asJsonString(conferenceDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    void createConferenceValidationStartDateTest() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = formatter.parse("2021-02-02 00:00");
        Conference conference = Conference.builder().id(MOCK_ID).name("TESTNAME").location("test").startDate(startDate).build();

        ConferenceDto conferenceDto = ConferenceDto.builder().id(MOCK_ID).name("TESTNAME").location("test").startDate(startDate).build();

        when(conferenceService.createOrUpdateConference(conference)).thenReturn(conference);
        when(dozerBeanMapper.map(conference, ConferenceDto.class)).thenReturn(conferenceDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/conference")
                        .content(asJsonString(conferenceDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}