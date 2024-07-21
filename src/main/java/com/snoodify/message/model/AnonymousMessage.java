package com.snoodify.message.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class AnonymousMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageId;
	
	@Column(name = "sender_id")
	private Long senderId;
	
	@Column(name = "recipient_id")
    private Long recipientId;
	
	@Column(name = "content_type")
	@Enumerated(EnumType.STRING)
    private ContentType contentType;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "timestamp")
    private LocalDateTime timestamp;
	
//	@Column(name = "anonymous")
//	private boolean anonymous;
	
	@Column(name = "reaction_id")
	private Long reactionId;
	
	@Column(name = "chat_id")
	private Long chatId;
}
