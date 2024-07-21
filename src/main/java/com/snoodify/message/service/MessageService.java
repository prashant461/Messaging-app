package com.snoodify.message.service;

import java.util.List;

import com.snoodify.message.dto.MessageDTO;

public interface MessageService {

	MessageDTO sendNormalMessage(MessageDTO messageDTO);

	List<MessageDTO> getHistory(Long chatId, Long user, int pageSize, int pageNumber);

	MessageDTO sendReaction(Long messageId, Long reactionId);

	List<MessageDTO> search(Long chatId, Long user, String text);

	void delete(Long chatId, Long user);

	MessageDTO deleteReaction(Long messageId);
}
