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
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long chatId;
	
	private Long senderId;
	
	private Long recipientId;
		
	private String lastMessage;
		
	private LocalDateTime timestamp;
	
	private boolean deletedBySender;
	
	private boolean deletedByRecipient;
	
	private boolean mutedBySender;
	
	private boolean mutedByRecipient;
	
}
