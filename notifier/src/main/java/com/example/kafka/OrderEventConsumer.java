package com.example.kafka;

import com.example.kafka.dto.OrderDto;
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
    public void receiveOrderEvent(@Payload OrderDto orderDto,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                  @Header(KafkaHeaders.OFFSET) int offset){
        LOG.info("Received message [{}] from notifier-group, partition-{} with offset-{}",
                orderDto.getId(),
                partition,
                offset);
        LOG.info("--------------------------------");
        sendEmail(orderDto);
    }

    private void sendEmail(OrderDto orderDto) {
        String recipientEmail = "frabajopuyi-3559@yopmail.com";
        String subject = "Order placed";
        StringBuilder content = new StringBuilder("<p>Hello,</p><p>Order" + orderDto.getId() + " has been placed</p>"
                + "<p>Order details:</p>");
        orderDto.getLines().stream().forEach(
                line ->
                {
                    content.append("<p>Product:" + line.getProductId()+ "</p>")
                            .append("<p>Quantity: " + line.getQuantity() + "</p>");
                    if (line.getDiscountPrice() > 0){
                        content.append("<p>Discount: " + line.getDiscountPrice() + "</p>")
                                .append("<p>Total Price: <s>" + line.getUnitPrice() * line.getQuantity() + "</s> " +
                                (line.getUnitPrice() * line.getQuantity()
                                        - line.getDiscountPrice()) + "</p>");
                    } else {
                        content.append("<p>Total Price: " + line.getUnitPrice() * line.getQuantity() + "</p>");
                    }
                }
        );
        content.append("<br/><br/><hr/>");
        content.append("<p>Subtotal: " + orderDto.getSubtotalPrice() + "</p>")
                .append("<p>Delivery: " + orderDto.getDeliveryPrice() + "</p>")
                .append("<p>Discount: " + orderDto.getDiscountPrice() + "</p>")
                .append("<p>Total: " + orderDto.getTotalPrice() + "</p>");

        try {
            emailSender.sendEmail(recipientEmail, subject, content.toString());
            System.out.println("Email sent successfully.");
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
    }
}
