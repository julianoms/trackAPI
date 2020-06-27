package com.trackAPI.controllers;

import com.trackAPI.dtos.TrackDto;
import com.trackAPI.gateway.TrackGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class TrackController {

    @Autowired
    TrackGateway trackGateway;

    @PostMapping("/track")
    public Mono<ResponseEntity> createTrack(TrackDto trackDto) {
        return null;
    }

}
