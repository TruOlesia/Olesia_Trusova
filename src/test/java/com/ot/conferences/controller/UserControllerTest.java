package com.ot.conferences.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ot.conferences.controller.dto.ParticipantDto;
import com.ot.conferences.controller.dto.UserDto;
import com.ot.conferences.model.Participant;
import com.ot.conferences.model.User;
import com.ot.conferences.service.ParticipantService;
import com.ot.conferences.service.UserService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ParticipantService participantService;

    @MockBean
    private DozerBeanMapper dozerBeanMapper;

    @Mock
    private Pageable paging;

    private final static Long MOCK_ID = 1L;
    private final static Long MOCK_ID_2 = 2L;

    @Test
    void getAllUsersTest() throws Exception {
        User user = User.builder().login("TESTNAME").build();

        UserDto userDto = UserDto.builder().login("TESTNAME").build();

        List<User> userList = new ArrayList<>();
        userList.add(user);
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);
        when(userService.listUsers(paging)).thenReturn(userList);
        when(dozerBeanMapper.map(user, UserDto.class)).thenReturn(userDto);

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getUserTest() throws Exception {
        User user = User.builder().id(MOCK_ID).login("TESTNAME").build();

        UserDto userDto = UserDto.builder().id(MOCK_ID).login("TESTNAME").build();

        when(userService.getUser(MOCK_ID)).thenReturn(user);
        when(dozerBeanMapper.map(user, UserDto.class)).thenReturn(userDto);

        mockMvc.perform(get("/user/" + MOCK_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createUserTest() throws Exception {

        User user = User.builder().id(MOCK_ID).login("TESTNAME@gmail.com").fullName("test").build();

        UserDto userDto = UserDto.builder().id(MOCK_ID).login("TESTNAME@gmail.com").fullName("test").build();

        when(userService.save(user)).thenReturn(user);
        when(dozerBeanMapper.map(userDto, User.class)).thenReturn(user);
        when(dozerBeanMapper.map(user, UserDto.class)).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content(asJsonString(userDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    void updateUserTest() throws Exception {

        User user = User.builder().id(MOCK_ID).login("TESTNAME@gmail.com").fullName("test").build();

        UserDto userDto = UserDto.builder().id(MOCK_ID).login("TESTNAME@gmail.com").fullName("test").build();

        when(userService.save(user)).thenReturn(user);
        when(dozerBeanMapper.map(userDto, User.class)).thenReturn(user);
        when(dozerBeanMapper.map(user, UserDto.class)).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/user/{id}", MOCK_ID)
                        .content(asJsonString(userDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteConferenceTest() throws Exception{
        //given
        User user = User.builder().id(MOCK_ID).login("TESTNAME@gmail.com").fullName("test").build();
        doNothing().when(userService).deleteUser(MOCK_ID);
        when(userService.getUser(MOCK_ID)).thenReturn(user);
        //when
        mockMvc.perform(delete("/user/" + MOCK_ID))
                .andExpect(status().isNoContent());

        //then
        verify(userService, times(1)).deleteUser(MOCK_ID);
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
    void createUserValidationLoginTest() throws Exception {

        User user = User.builder().id(MOCK_ID).login("TESTNAME").fullName("test").build();

        UserDto userDto = UserDto.builder().id(MOCK_ID).login("TESTNAME").fullName("test").build();

        when(userService.save(user)).thenReturn(user);
        when(dozerBeanMapper.map(userDto, User.class)).thenReturn(user);
        when(dozerBeanMapper.map(user, UserDto.class)).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content(asJsonString(userDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUserValidationFullNameTest() throws Exception {

        User user = User.builder().id(MOCK_ID).login("TESTNAME").build();

        UserDto userDto = UserDto.builder().id(MOCK_ID).login("TESTNAME").build();

        when(userService.save(user)).thenReturn(user);
        when(dozerBeanMapper.map(userDto, User.class)).thenReturn(user);
        when(dozerBeanMapper.map(user, UserDto.class)).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content(asJsonString(userDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteConference_Exception_Test() throws Exception{
        //given
        User user = null;
        //when
        doNothing().when(userService).deleteUser(MOCK_ID);
        when(userService.getUser(MOCK_ID)).thenReturn(user);
        //then
        mockMvc.perform(delete("/user/" + MOCK_ID))
                .andExpect(status().isNotFound());

    }

    @Test
    void updateUser_Exception_Test() throws Exception {

        User user = null;

        UserDto userDto = UserDto.builder().id(MOCK_ID).login("TESTNAME@gmail.com").fullName("test").build();

        when(userService.save(user)).thenReturn(user);
        when(dozerBeanMapper.map(userDto, User.class)).thenReturn(user);
        when(dozerBeanMapper.map(user, UserDto.class)).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/user/{id}", MOCK_ID)
                        .content(asJsonString(userDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUser_Exception_Test() throws Exception {

        User user = null;

        UserDto userDto = UserDto.builder().id(MOCK_ID).login("TESTNAME@gmail.com").fullName("test").build();

        when(userService.save(user)).thenReturn(user);
        when(dozerBeanMapper.map(userDto, User.class)).thenReturn(user);
        when(dozerBeanMapper.map(user, UserDto.class)).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content(asJsonString(userDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUser_Exception_Test() throws Exception {
        User user = null;

        UserDto userDto = UserDto.builder().id(MOCK_ID).login("TESTNAME").build();

        when(userService.getUser(MOCK_ID)).thenReturn(user);
        when(dozerBeanMapper.map(user, UserDto.class)).thenReturn(userDto);

        mockMvc.perform(get("/user/" + MOCK_ID))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    void getAllParticipantsTest() throws Exception {
        Participant participant = Participant.builder().user(User.builder().id(MOCK_ID).build()).build();
        ParticipantDto participantDto = ParticipantDto.builder().user(UserDto.builder().id(MOCK_ID).build()).build();

        List<Participant> participantList = new ArrayList<>();
        participantList.add(participant);
        List<ParticipantDto> participantDtoList = new ArrayList<>();
        participantDtoList.add(participantDto);
        when(participantService.getAllParticipantByUserId(MOCK_ID)).thenReturn(participantList);
        when(dozerBeanMapper.map(participant, ParticipantDto.class)).thenReturn(participantDto);

        mockMvc.perform(get("/user/{id}/participant", MOCK_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}