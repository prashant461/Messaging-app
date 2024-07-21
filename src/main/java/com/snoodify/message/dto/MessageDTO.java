package com.snoodify.message.dto;

import java.time.LocalDateTime;
import com.snoodify.message.model.ContentType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
	
	private Long MessageId;
	private Long senderId;
    private Long recipientId;
    private ContentType contentType;
    private String content;
    private LocalDateTime timestamp;
    private Long chatId;
    private Long reactionId;
    private boolean seen;
	private Long tagedMessageId;
	private boolean deletedBySender;
	private boolean deletedByRecipient;
}
