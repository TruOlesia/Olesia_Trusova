package com.ot.conferences.repository;

import com.ot.conferences.model.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {


}
