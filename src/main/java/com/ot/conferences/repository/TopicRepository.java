package com.ot.conferences.repository;

import com.ot.conferences.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {

    @Query("select t from Topic t where t.conference.id = ?1")
    Page<Topic> findAllTopicByConfId(Long id, Pageable pageable);
}
