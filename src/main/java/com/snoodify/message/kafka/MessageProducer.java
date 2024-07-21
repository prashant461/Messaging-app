package com.snoodify.message.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.snoodify.message.model.AnonymousMessage;
import com.snoodify.message.model.Message;

@Component
public class MessageProducer {
	
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	public void sendMessage(Message message) {
		System.out.println("Message sent to producer");

		kafkaTemplate.send("message", message);
	}

	public void sendAnonymousMessage(AnonymousMessage message) {
		System.out.println("Anonymous message sent to producer");

		kafkaTemplate.send("anonymous", message);
	}

}
