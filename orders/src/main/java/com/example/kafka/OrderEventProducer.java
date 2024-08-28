package com.example.kafka;

import com.example.kafka.converters.OrderConverter;
import com.example.kafka.dto.OrderDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.orders.model.Order;

@Service
public class OrderEventProducer {
    private static final String TOPIC = "order_event";
    private final KafkaTemplate<String, OrderDto> kafkaTemplate;
    private final OrderConverter orderConverter;
    private final Logger LOG = LoggerFactory.getLogger(OrderEventProducer.class);

    public OrderEventProducer(KafkaTemplate<String, OrderDto> kafkaTemplate, OrderConverter orderConverter) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderConverter = orderConverter;
    }
    public void sendOrderEvent(Order order){
        OrderDto orderDto = this.orderConverter.convert(order);
        LOG.info("Sending : {}", orderDto.getId());
        LOG.info("--------------------------------");
        kafkaTemplate.send(TOPIC, orderDto);
    }
}
