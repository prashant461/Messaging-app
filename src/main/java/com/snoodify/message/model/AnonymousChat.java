package com.snoodify.message.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnonymousChat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long chatId;
	
	private Long senderId;
	
	private Long recipientId;
		
	private String lastMessage;
		
	private LocalDateTime timestamp;
	
	private Long createdBy;
	
	private boolean stillAnonymous;// this will indicate conversation is still anonymous
}
