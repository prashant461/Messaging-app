package com.snoodify.message.dto;

import java.time.LocalDateTime;

import com.snoodify.message.model.ContentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnonymousMessageDTO {
	
	private Long messageId;
	
	private Long senderId;
	
    private Long recipientId;
	
    private ContentType contentType;
	
	private String content;
	
    private LocalDateTime timestamp;
	
	private Long reactionId;
	
	private Long chatId;
}
