package com.ot.conferences.controller;

import com.ot.conferences.controller.dto.ConferenceDto;
import com.ot.conferences.service.ConferenceService;
import com.ot.conferences.model.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ConferencesController {
    @Autowired
    private ConferenceService conferenceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/conferences")
    public List<ConferenceDto> getAllConferences() {
        return conferenceService.getAllConferences()
                .stream()
                .map(this::mapConferenceToConferenceDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/conference/{id}")
    public ConferenceDto getConference(@PathVariable int id) {
        return mapConferenceToConferenceDto(conferenceService.getConference(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/conference")
    public ConferenceDto createConference(@RequestBody ConferenceDto conferenceDto) {
        Conference conference = mapConferenceDtoToConference(conferenceDto);
        return mapConferenceToConferenceDto(conferenceService.createConference(conference));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/conference/{id}")
    public ConferenceDto updateConference(@PathVariable int id, @RequestBody ConferenceDto conferenceDto) {
        Conference conference = mapConferenceDtoToConference(conferenceDto);
        return mapConferenceToConferenceDto(conferenceService.updateConference(id, conference));
    }

    @DeleteMapping(value = "/conference/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable int id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }

    private ConferenceDto mapConferenceToConferenceDto(Conference conference) {
        return ConferenceDto.builder()
                .id(conference.getId())
                .name(conference.getName())
                .location(conference.getLocation())
                .startDate(conference.getStartDate())
                .endDate(conference.getEndDate())
                .description(conference.getDescription())
                .moderatorId(conference.getModeratorId())
                .build();
    }

    private Conference mapConferenceDtoToConference(ConferenceDto conferenceDto) {
        return Conference.builder()
                .id(conferenceDto.getId())
                .name(conferenceDto.getName())
                .location(conferenceDto.getLocation())
                .startDate(conferenceDto.getStartDate())
                .endDate(conferenceDto.getEndDate())
                .description((conferenceDto.getDescription()))
                .moderatorId(conferenceDto.getModeratorId())
                .build();
    }
}