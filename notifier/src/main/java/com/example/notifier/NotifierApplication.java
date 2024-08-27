package com.example.notifier;

import com.example.notifier.mail.EmailSender;
import jakarta.mail.MessagingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class NotifierApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(NotifierApplication.class, args);
		EmailSender emailSender = (EmailSender) context.getBean("emailSender");

		// Call the sendEmail method to send an email
		String recipientEmail = "frabajopuyi-3559@yopmail.com";
		String subject = "Hello from Spring Boot";
		String content = "<p>Hello,</p><p>This is a test email sent from Spring Boot.</p>";

		try {
			emailSender.sendEmail(recipientEmail, subject, content);
			System.out.println("Email sent successfully.");
		} catch (MessagingException | UnsupportedEncodingException e) {
			System.out.println("Failed to send email. Error: " + e.getMessage());
		}
	}

}
