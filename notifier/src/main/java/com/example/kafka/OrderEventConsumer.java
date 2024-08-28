package com.example.kafka;

import com.example.notifier.mail.EmailSender;
//import com.example.orders.kafka.OrderEvent;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class OrderEventConsumer {
    private static final String TOPIC = "order_event";
    private final EmailSender emailSender;
    private final Logger LOG = LoggerFactory.getLogger(OrderEventConsumer.class);

    public OrderEventConsumer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }
    @KafkaListener(topics = TOPIC, groupId = "notifier-group")//, containerFactory = "kafkaListenerContainerFactory")
    public void receiveOrderEvent(@Payload OrderEvent orderEvent,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                  @Header(KafkaHeaders.OFFSET) int offset){
        LOG.info("Received message [{}] from notifier-group, partition-{} with offset-{}",
                orderEvent.getId(),
                partition,
                offset);
        LOG.info("--------------------------------");
        String recipientEmail = "frabajopuyi-3559@yopmail.com";
        String subject = "Order " + orderEvent.getId() + " has been placed";
        String content = "<p>Hello,</p><p>This is a test email sent from Spring Boot.</p>";

        try {
            emailSender.sendEmail(recipientEmail, subject, content);
            System.out.println("Email sent successfully.");
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
    }
}
