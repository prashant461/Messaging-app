package com.snoodify.message.kafka;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;

import com.snoodify.message.model.AnonymousMessage;
import com.snoodify.message.model.Message;

@Component
public class MessageConsumer {
	
//	@Autowired
//    private RestTemplate restTemplate;
	
	@KafkaListener(topics = "message", groupId = "group_id")
	public void listen(Message message) {
//		// Define the API URL of your frontend or notification service
//        String apiUrl = "http://localhost:8080/api/notify";
//
//        // Send the message using RestTemplate
//        restTemplate.postForObject(apiUrl, message, Message.class);
		System.out.println("message consumed : "+ message.getContent());
	}
	
	@KafkaListener(topics = "anonymous", groupId = "group_id")
	public void listenAnonymous(AnonymousMessage message) {
		
	}

}
