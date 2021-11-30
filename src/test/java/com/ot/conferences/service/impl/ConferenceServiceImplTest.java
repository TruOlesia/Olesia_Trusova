package com.ot.conferences.service.impl;

import com.ot.conferences.model.Conference;
import com.ot.conferences.repository.ConferenceRepository;
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
public class ConferenceServiceImplTest {

    @InjectMocks
    private ConferenceServiceImpl conferenceService;

    @Mock
    private ConferenceRepository conferenceRepository;

    @Mock
    private Pageable paging;

    @Mock
    Page<Conference> pageresalt;

    private final static Long MOCK_ID = 1L;

    @Test
    void getConferenceById() {
        //given
        Conference expectedConference = Conference.builder().id(MOCK_ID).build();
        Optional<Conference> optionalConference = Optional.of(expectedConference);
        when(conferenceRepository.findById(MOCK_ID)).thenReturn(optionalConference);

        //when
        Conference actualConference = conferenceService.getConference(MOCK_ID);

        //then
        assertEquals(expectedConference.getId(), actualConference.getId());
    }

    @Test
    void deleteConferenceTest() {

        //given
        Conference expectedConference = Conference.builder().id(MOCK_ID).build();
        Optional<Conference> optionalConference = Optional.of(expectedConference);
        when(conferenceRepository.findById(MOCK_ID)).thenReturn(optionalConference);
        doNothing().when(conferenceRepository).delete(expectedConference);
        //when
        conferenceService.deleteConference(MOCK_ID);

        //then
        verify(conferenceRepository, times(1)).delete(expectedConference);
    }

    @Test
    public void createOrUpdateConferenceTest() {
        //given
        Conference expectedConference = Conference.builder().id(MOCK_ID).build();
        when(conferenceRepository.save(expectedConference)).thenReturn(expectedConference);

        //when
        Conference actualConference = conferenceService.createOrUpdateConference(expectedConference);

        //then
        assertEquals(expectedConference.getId(), actualConference.getId());

    }

    @Test
    public void getAllConferencesTest() {

        //given
        Conference expectedConference = Conference.builder().id(MOCK_ID).build();
        List<Conference> conferenceList = new ArrayList<>();
        conferenceList.add(expectedConference);
        when(conferenceRepository.findAll(paging)).thenReturn(pageresalt);
        when(pageresalt.hasContent()).thenReturn(true);
        when(pageresalt.getContent()).thenReturn(conferenceList);
        //when
        List<Conference> conferences = conferenceService.getAllConferences(paging);

        //then
        assertThat(conferences, hasSize(1));
    }

    @Test
    public void getAllConferencesFalseTest() {

        //given
        when(conferenceRepository.findAll(paging)).thenReturn(pageresalt);
        when(pageresalt.hasContent()).thenReturn(false);

        //when
        List<Conference> conferences = conferenceService.getAllConferences(paging);

        //then
        assertThat(conferences, hasSize(0));
    }
}