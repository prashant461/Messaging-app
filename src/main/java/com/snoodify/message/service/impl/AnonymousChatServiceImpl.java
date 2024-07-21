package com.snoodify.message.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.snoodify.message.dto.AnonymousChatDTO;
import com.snoodify.message.dto.AnonymousMessageDTO;
import com.snoodify.message.dto.ChatDTO;
import com.snoodify.message.model.AnonymousChat;
import com.snoodify.message.repository.AnonymousChatRepo;
import com.snoodify.message.repository.ChatRepo;
import com.snoodify.message.service.AnonymousChatService;

@Service
public class AnonymousChatServiceImpl implements AnonymousChatService {
	
	@Autowired
	private AnonymousChatRepo anonymousChatRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<AnonymousChatDTO> getAllChats(Long senderId) {
		// this will return all received and sent chat
		List<AnonymousChat> anonymousChats = anonymousChatRepo.findBySenderIdOrRecipientId(senderId, senderId);
		
		List<AnonymousChatDTO> anonymousChatDTOs = anonymousChats.stream()
				.map(chat->{
					AnonymousChatDTO anonymousChatDTO = modelMapper.map(chat, AnonymousChatDTO.class);
					
					// getting profile pic
					String senderPicUrl = getUserProfilePictureUrl(chat.getSenderId());
					String recipientPicUrl = getUserProfilePictureUrl(chat.getRecipientId());
					anonymousChatDTO.setSenderProfilePic(senderPicUrl);
					anonymousChatDTO.setRecipientProfilePic(recipientPicUrl);
					
					return anonymousChatDTO;
				})
				.collect(Collectors.toList());
		
		return anonymousChatDTOs;
	}
	
	private String getUserProfilePictureUrl(Long userId) {
//        String url = "http://localhost:8081/picture-url/{userId}";
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, userId);
//        return response.getBody();
		
		return null;
    }
	
	@Override
	public AnonymousChatDTO createNewChat(Long senderId, Long recipientId) {
		Optional<AnonymousChat> chatOptional = anonymousChatRepo.findBySenderIdAndRecipientIdAndCreatedBy(
				senderId, recipientId, senderId);
		
		if(chatOptional.isEmpty()) {
			AnonymousChat chat = AnonymousChat.builder()
							.senderId(senderId)
							.recipientId(recipientId)
							.createdBy(senderId)
							.stillAnonymous(true)
							.lastMessage(null)
							.timestamp(LocalDateTime.now())
							.build();
			
			AnonymousChat savedChat = anonymousChatRepo.save(chat);
			
			return modelMapper.map(savedChat, AnonymousChatDTO.class);
		}
		
		return modelMapper.map(chatOptional.get(), AnonymousChatDTO.class);
	}

	@Override
	public AnonymousChatDTO updateAnonymousFlag(Long chatId) {
		Optional<AnonymousChat> chatopOptional = anonymousChatRepo.findById(chatId);
		
		AnonymousChat chat = chatopOptional.get();
		chat.setStillAnonymous(false);
		
		anonymousChatRepo.save(chat);
		
		return modelMapper.map(chat, AnonymousChatDTO.class);
	}
}
