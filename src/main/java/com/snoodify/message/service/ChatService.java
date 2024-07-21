package com.snoodify.message.service;

import java.util.List;

import com.snoodify.message.dto.ChatDTO;
import com.snoodify.message.dto.MessageDTO;

public interface ChatService {
	List<ChatDTO> getAllChats(Long sender);

	ChatDTO createNewChat(Long senderId, Long recipientId);

	void muteChat(Long chatId, Long user);

	void deleteChat(Long chatId, Long user);
}
