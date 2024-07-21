package com.snoodify.message.service;

import java.util.List;

import com.snoodify.message.dto.AnonymousChatDTO;
import com.snoodify.message.dto.AnonymousMessageDTO;
import com.snoodify.message.dto.ChatDTO;
import com.snoodify.message.model.AnonymousChat;

public interface AnonymousChatService {
	List<AnonymousChatDTO> getAllChats(Long senderId);

	AnonymousChatDTO createNewChat(Long senderId, Long recipientId);

	AnonymousChatDTO updateAnonymousFlag(Long chatId);
}
