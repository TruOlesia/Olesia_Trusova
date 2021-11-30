package com.ot.conferences.service.impl;

import com.ot.conferences.model.Conference;
import com.ot.conferences.model.Participant;
import com.ot.conferences.model.User;
import com.ot.conferences.repository.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParticipantServiceImplTest {
    @InjectMocks
    private ParticipantServiceImpl participantService;

    @Mock
    private ParticipantRepository participantRepository;

    private final static Long MOCK_ID = 1L;

    @Test
    public void getAllParticipantByUserIdTest() {

        //given
        Participant expectedParticipant = Participant.builder().user(User.builder().id(MOCK_ID).build()).build();
        List<Participant> participantsList = new ArrayList<>();
        participantsList.add(expectedParticipant);
        when(participantRepository.findParticipantByUserId(MOCK_ID)).thenReturn(participantsList);

        //when
        List<Participant> participants = participantService.getAllParticipantByUserId(MOCK_ID);

        //then
        assertThat(participants, hasSize(1));
    }

    @Test
    public void getAllParticipantByConfIdTest() {

        //given
        Participant expectedParticipant = Participant.builder().conference(Conference.builder().id(MOCK_ID).build()).build();
        List<Participant> participantsList = new ArrayList<>();
        participantsList.add(expectedParticipant);
        when(participantRepository.findParticipantByConfId(MOCK_ID)).thenReturn(participantsList);

        //when
        List<Participant> participants = participantService.getAllParticipantByConfId(MOCK_ID);

        //then
        assertThat(participants, hasSize(1));
    }
}