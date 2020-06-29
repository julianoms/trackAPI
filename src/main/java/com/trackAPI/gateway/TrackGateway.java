package com.trackAPI.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trackAPI.dtos.TrackDto;
import com.trackAPI.exceptions.TrackApiExceptions;
import com.trackAPI.messages.ErrorMessages;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;

@Component
public class TrackGateway {
    @Value("${kafka.topic.request-topic}")
    String topic;

    @Autowired
    KafkaSender<String, String> kafkaSender;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Mono<SenderResult<Integer>> sendTrackMessage(TrackDto trackDto) {

        String payload = toString(trackDto);

        SenderRecord<String, String, Integer> message = SenderRecord.create(new ProducerRecord<>(topic, payload), 1);

        return kafkaSender.send(Mono.just(message)).next();
    }

    private String toString(TrackDto trackDto) {
        try {
            return objectMapper.writeValueAsString(trackDto);
        } catch (JsonProcessingException e) {
            throw new TrackApiExceptions(ErrorMessages.MAPPING_ERROR, e);
        }
    }
}
