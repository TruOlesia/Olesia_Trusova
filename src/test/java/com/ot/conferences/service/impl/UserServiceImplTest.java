package com.ot.conferences.service.impl;

import com.ot.conferences.model.User;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Pageable paging;

    @Mock
    Page<User> pageResult;

    private final static Long MOCK_ID = 1L;

    @Test
    void getUserTest() {
        //given
        User expectedUser = User.builder().id(MOCK_ID).build();
        Optional<User> optionalUser = Optional.of(expectedUser);
        when(userRepository.findById(MOCK_ID)).thenReturn(optionalUser);

        //when
        User actualUser = userService.getUser(MOCK_ID);

        //then
        assertEquals(expectedUser.getId(), actualUser.getId());
    }

    @Test
    void deleteUserTest() {

        //given
        User expectedUser = User.builder().id(MOCK_ID).build();
        Optional<User> optionalUser = Optional.of(expectedUser);
        when(userRepository.findById(MOCK_ID)).thenReturn(optionalUser);
        doNothing().when(userRepository).delete(expectedUser);
        //when
        userService.deleteUser(MOCK_ID);

        //then
        verify(userRepository, times(1)).delete(expectedUser);
    }

    @Test
    public void saveUserTest() {
        //given
        User expectedUser = User.builder().id(MOCK_ID).build();
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);

        //when
        User actualUser = userService.save(expectedUser);

        //then
        assertEquals(expectedUser.getId(), actualUser.getId());

    }

    @Test
    public void getAllUsersTest() {

        //given
        User expectedUser = User.builder().id(MOCK_ID).build();
        List<User> userList = new ArrayList<>();
        userList.add(expectedUser);
        when(userRepository.findAll(paging)).thenReturn(pageResult);
        when(pageResult.hasContent()).thenReturn(true);
        when(pageResult.getContent()).thenReturn(userList);
        //when
        List<User> users = userService.listUsers(paging);

        //then
        assertThat(users, hasSize(1));
    }

    @Test
    public void getAllUsersFalseTest() {

        //given
        when(userRepository.findAll(paging)).thenReturn(pageResult);
        when(pageResult.hasContent()).thenReturn(false);

        //when
        List<User> users = userService.listUsers(paging);

        //then
        assertThat(users, hasSize(0));
    }

}