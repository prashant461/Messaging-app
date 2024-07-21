package com.snoodify.message.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
	
	private Long chatId;
	
	private Long senderId;
	
	private Long recipientId;
	
	private boolean isGroup;
	
	private String lastMessage;
	
	private String senderProfilePic;// need from differnt microservice
	
	private String recipientProfilePic;
	
	private LocalDateTime timestamp;
	
	private boolean seen;
	
	private boolean deletedBySender;
	
	private boolean deletedByrecipient;
	
	private boolean mutedBySender;
	
	private boolean mutedByrecipient;
	
}
