package com.example.orders.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderEventProducer {
    private static final String TOPIC = "order_event";
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final Logger LOG = LoggerFactory.getLogger(OrderEventProducer.class);

    public OrderEventProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendOrderEvent(OrderEvent orderEvent){
        LOG.info("Sending : {}", orderEvent.getId());
        LOG.info("--------------------------------");
        kafkaTemplate.send(TOPIC, orderEvent);
    }
}
