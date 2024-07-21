package com.snoodify.message.model;

import java.time.LocalDateTime;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
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
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
	
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
	
	@Column(name = "reaction_id")
	private Long reactionId;
	
	@Column(name = "chat_id")
	private Long chatId;
	
	@Column(name = "seen")
	private boolean seen;
	
	@Column(name = "taged_message_id")
	private Long tagedMessageId;
	
	private boolean deletedBySender;
	
	private boolean deletedByRecipient;
}
