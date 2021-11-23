package com.ot.conferences.repository;

import com.ot.conferences.model.Conference;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConferenceRepository extends PagingAndSortingRepository<Conference, Long> {

}
