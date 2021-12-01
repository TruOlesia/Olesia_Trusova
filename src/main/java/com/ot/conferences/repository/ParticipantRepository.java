package com.ot.conferences.repository;

import com.ot.conferences.model.Participant;
import com.ot.conferences.model.ParticipantKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ParticipantRepository extends PagingAndSortingRepository<Participant, ParticipantKey> {

    @Query("select p from Participant p where p.user.id = ?1")
    List<Participant> findParticipantByUserId(Long id);

    @Query("select p from Participant p where p.conference.id = ?1")
    List<Participant> findParticipantByConfId(Long id);

    @Query("select p from Participant p where p.conference.id = ?1 And p.isPresent = true ")
    List<Participant> findPresentByConfIdAndIsPresent();
}
