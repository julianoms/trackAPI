package com.trackAPI.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SpringKafkaConfiguration {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServersConfig;

    @Bean
    public SenderOptions<String, String> producerConfig() {
        final Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer .class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer .class);
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersConfig);

        return SenderOptions.create(producerProps);
    }

    @Bean
    public KafkaSender<String, String> getProducer() {
       return KafkaSender.create(producerConfig());
    }
}
