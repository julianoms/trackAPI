package com.trackAPI.controllers;

import com.trackAPI.dtos.TrackDto;
import com.trackAPI.gateway.TrackGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/v1")
@Slf4j
public class TrackController {

    @Autowired
    TrackGateway trackGateway;

    @PostMapping("/track")
    public Mono<ResponseEntity> createTrack(@RequestBody TrackDto trackDto) {
        log.info("received create track: {}",trackDto.getName());
        return  trackGateway.sendTrackMessage(trackDto)
                .then(Mono.just(new ResponseEntity<>(CREATED)));
    }

}
