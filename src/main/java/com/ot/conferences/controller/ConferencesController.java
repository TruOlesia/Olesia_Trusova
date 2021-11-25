package com.ot.conferences.controller;

import com.ot.conferences.controller.dto.ConferenceDto;
import com.ot.conferences.controller.dto.ParticipantDto;
import com.ot.conferences.exception.NotFoundException;
import com.ot.conferences.model.Conference;
import com.ot.conferences.service.ConferenceService;
import com.ot.conferences.service.ParticipantService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private ParticipantService participantService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/conference")
    public List<ConferenceDto> getAllConferences(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {

            Pageable paging = PageRequest.of(page, size);
        return conferenceService.getAllConferences(paging)
                .stream()
                .map(c -> dozerBeanMapper.map(c, ConferenceDto.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/conference/{id}")
    public ConferenceDto getConference(@PathVariable Long id) throws NotFoundException {
        Conference conference = conferenceService.getConference(id);
        if(conference == null) {
            throw new NotFoundException("Invalid conference id : " + id);
        }
        return dozerBeanMapper.map(conferenceService.getConference(id), ConferenceDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/conference")
    public ConferenceDto createConference(@Valid @RequestBody ConferenceDto conferenceDto) {
        Conference conference = dozerBeanMapper.map(conferenceDto, Conference.class);
        return dozerBeanMapper.map(conferenceService.createOrUpdateConference(conference), ConferenceDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/conference/{id}")
    public ConferenceDto updateConference(@PathVariable Long id, @RequestBody @Valid ConferenceDto conferenceDto) throws NotFoundException {
        Conference conference = dozerBeanMapper.map(conferenceDto, Conference.class);
        if(conference == null || conference.getId() ==null) {
            throw new NotFoundException("Invalid conference id : " + id);
        }
        return dozerBeanMapper.map(conferenceService.createOrUpdateConference(conference), ConferenceDto.class);
    }

    @DeleteMapping(value = "/conference/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) throws NotFoundException {
        Conference conference = conferenceService.getConference(id);
        if(conference == null) {
            throw new NotFoundException("Invalid conference id : " + id);
        }
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/conference/{id}/participant")
    public List<ParticipantDto> getParticipants(@PathVariable Long id) {

        return participantService.getAllParticipantByConfId(id)
                .stream()
                .map(c -> dozerBeanMapper.map(c, ParticipantDto.class))
                .collect(Collectors.toList());
    }
}
