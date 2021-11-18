package com.ot.conferences.controller;

import com.ot.conferences.controller.dto.ConferenceDto;
import com.ot.conferences.service.ConferenceService;
import com.ot.conferences.model.Conference;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ConferencesController {
    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/conferences")
    public List<ConferenceDto> getAllConferences() {
        return conferenceService.getAllConferences()
                .stream()
                .map(c -> dozerBeanMapper.map(c, ConferenceDto.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/conference/{id}")
    public ConferenceDto getConference(@PathVariable int id) {
        return dozerBeanMapper.map(conferenceService.getConference(id), ConferenceDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/conference")
    public ConferenceDto createConference(@RequestBody @Valid ConferenceDto conferenceDto) {
        Conference conference = dozerBeanMapper.map(conferenceDto, Conference.class);

        return dozerBeanMapper.map(conferenceService.createConference(conference), ConferenceDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/conference/{id}")
    public ConferenceDto updateConference(@PathVariable int id, @RequestBody @Valid ConferenceDto conferenceDto) {
        Conference conference = dozerBeanMapper.map(conferenceDto, Conference.class);

        return dozerBeanMapper.map(conferenceService.updateConference(id, conference), ConferenceDto.class);
    }

    @DeleteMapping(value = "/conference/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable int id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }
}