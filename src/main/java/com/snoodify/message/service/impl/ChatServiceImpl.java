package com.snoodify.message.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.snoodify.message.dto.ChatDTO;
import com.snoodify.message.model.Chat;
import com.snoodify.message.repository.ChatRepo;
import com.snoodify.message.service.ChatService;
import com.snoodify.message.service.MessageService;

@Service
public class ChatServiceImpl implements ChatService{
	
	private ChatRepo chatRepo;
	
	private ModelMapper modelMapper;
	
	private MessageService messageService;
	
	private RestTemplate restTemplate;
	
	
	
	public ChatServiceImpl(ChatRepo chatRepo, ModelMapper modelMapper, MessageService messageService, RestTemplate restTemplate) {
		this.chatRepo = chatRepo;
		this.modelMapper = modelMapper;
		this.messageService = messageService;
		this.restTemplate = restTemplate;
	}

	@Override
	public List<ChatDTO> getAllChats(Long senderId) {
		List<Chat> chats = chatRepo.findBySenderIdOrRecipientId(senderId, senderId);
		
		List<ChatDTO> chatDTOs = chats.stream()
				.map(chat->{
					ChatDTO chatDTO = modelMapper.map(chat, ChatDTO.class);
					
					// getting profile pic
					String senderPicUrl = getUserProfilePictureUrl(chat.getSenderId());
					String recipientPicUrl = getUserProfilePictureUrl(chat.getRecipientId());
					chatDTO.setSenderProfilePic(senderPicUrl);
					chatDTO.setRecipientProfilePic(recipientPicUrl);
					
					return chatDTO;
				})
				.collect(Collectors.toList());
		
		return chatDTOs;
	}
	
	private String getUserProfilePictureUrl(Long userId) {
//        String url = "http://localhost:8081/picture-url/{userId}";
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, userId);
//        return response.getBody();
		return null;
    }

	@Override
	public ChatDTO createNewChat(Long senderId, Long recipientId) {
		Optional<Chat> chatOptional = chatRepo.findChatByParticipants(senderId, recipientId);
		
		if(chatOptional.isEmpty()) {
			Chat chat = Chat.builder()
							.senderId(senderId)
							.recipientId(recipientId)
							.lastMessage(null)
							.timestamp(LocalDateTime.now())
							.build();
			
			Chat savedChat = chatRepo.save(chat);
			
			return modelMapper.map(savedChat, ChatDTO.class);
		}

		return modelMapper.map(chatOptional.get(), ChatDTO.class);
	}

	@Override
	public void muteChat(Long chatId, Long user) {
		Optional<Chat> chatOptional = chatRepo.findById(chatId);
		
		Chat chat = chatOptional.get();
		
		if(chat.getSenderId()==user) {
			chat.setMutedBySender(true);
		}
		else {
			chat.setMutedByRecipient(true);
		}
		chatRepo.save(chat);
	}

	@Override
	public void deleteChat(Long chatId, Long user) {
		Optional<Chat> chatOptional = chatRepo.findById(chatId);
		
		Chat chat = chatOptional.get();
		
		if(chat.getSenderId()==user) chat.setDeletedBySender(true);
		else chat.setDeletedByRecipient(true);
		
		if(chat.isDeletedByRecipient()&& chat.isDeletedBySender()) chatRepo.delete(chat);
		else chatRepo.save(chat);
		
		// also needs to update message for that chatId
		messageService.delete(chatId, user);
	}
}
