package com.ot.conferences.controller;

import com.ot.conferences.controller.dto.ConferenceDto;
import com.ot.conferences.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(value = ConferencesController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
class ConferencesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConferenceService conferenceService;

    @Test
    void getAllUsersTest() throws Exception {
        ConferenceDto conferenceDto = ConferenceDto
                .builder()
                .firstName("TESTNAME")
                .build();

        when(userService.listUsers()).thenReturn(Collections.singletonList(userDto));

        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value(userDto.getFirstName()));
    }
}