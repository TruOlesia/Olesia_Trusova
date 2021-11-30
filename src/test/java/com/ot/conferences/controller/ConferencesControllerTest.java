package com.ot.conferences.controller;

import com.ot.conferences.model.Conference;
import com.ot.conferences.model.Participant;
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

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ConferencesController.class)
@AutoConfigureMockMvc
//@Import(TestWebConfig.class)
class ConferencesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConferenceService conferenceService;

    @MockBean
    private ConferencesController conferencesController;

    @MockBean
    private ParticipantService participantService;

    @MockBean
    private DozerBeanMapper dozerBeanMapper;

    @Mock
    private Pageable paging;

    @Test
    void getAllConferencesTest() throws Exception {
        Conference conference = Conference
                .builder()
                .name("TESTNAME")
                .build();

        Participant participant = Participant
                .builder()
                .build();

        when(conferenceService.getAllConferences(paging)).thenReturn(Collections.singletonList(conference));

        mockMvc.perform(get("/conference"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}