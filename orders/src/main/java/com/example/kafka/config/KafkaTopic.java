package com.example.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    @Bean
    public NewTopic orderEventTopic() {
        return TopicBuilder.name("order_event")
                .partitions(3)
                .replicas(3)
                .build();
    }
}
