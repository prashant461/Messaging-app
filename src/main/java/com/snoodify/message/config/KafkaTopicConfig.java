package com.snoodify.message.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	
	@Bean
	public NewTopic message() {
		return TopicBuilder.name("message")
				.build();
	}
	@Bean
	public NewTopic anonymous() {
		return TopicBuilder.name("anonymous")
				.build();
	}

}
