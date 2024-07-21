package com.snoodify.message.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnonymousChatDTO {
	
	private Long chatId;
	
	private Long senderId;
	
	private Long recipientId;
		
	private String lastMessage;
	
	private String senderProfilePic;
	
	private String recipientProfilePic;
	
	private LocalDateTime timestamp;
	
	private Long createdBy;
	
	private boolean stillAnonymous;
}
