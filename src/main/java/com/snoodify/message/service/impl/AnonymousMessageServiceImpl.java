package com.snoodify.message.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.snoodify.message.dto.AnonymousMessageDTO;
import com.snoodify.message.kafka.MessageProducer;
import com.snoodify.message.model.AnonymousMessage;
import com.snoodify.message.repository.AnonymousMessageRepo;
import com.snoodify.message.service.AnonymousMessageService;

@Service
public class AnonymousMessageServiceImpl implements AnonymousMessageService {
	
	private ModelMapper modelMapper;
	
	private AnonymousMessageRepo anonymousMessageRepo;
		
	private MessageProducer messageProducer;
	
	public AnonymousMessageServiceImpl(ModelMapper modelMapper, AnonymousMessageRepo anonymousMessageRepo,
			MessageProducer messageProducer) {
		super();
		this.modelMapper = modelMapper;
		this.anonymousMessageRepo = anonymousMessageRepo;
		this.messageProducer = messageProducer;
	}

	@Override
	public AnonymousMessageDTO sendAnonymousMessage(AnonymousMessageDTO anonymousMessageDTO) {
		
		AnonymousMessage message = AnonymousMessage.builder()
				.senderId(anonymousMessageDTO.getSenderId())
				.recipientId(anonymousMessageDTO.getRecipientId())
				.chatId(anonymousMessageDTO.getChatId())
				.reactionId(anonymousMessageDTO.getReactionId())
				.content(anonymousMessageDTO.getContent())
				.contentType(anonymousMessageDTO.getContentType())
				.timestamp(LocalDateTime.now())
				.build();
	
		// sending message to kafka (topic - anonymous)
		messageProducer.sendAnonymousMessage(message);
		
		AnonymousMessage savedAnonymousMessage = anonymousMessageRepo.save(message);
		
		return modelMapper.map(savedAnonymousMessage, AnonymousMessageDTO.class);
	}

	@Override
	public List<AnonymousMessageDTO> getHistory(Long chatId, int pageSize, int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<AnonymousMessage> anonymousMessages = anonymousMessageRepo.findByChatIdOrderByTimestampDesc(chatId, pageable);
		
		List<AnonymousMessageDTO> anonymousMessageDTOs = anonymousMessages.stream()
					.map(anonymousMessage-> {
						AnonymousMessageDTO anonymousMessageDTO = modelMapper.map(anonymousMessage, AnonymousMessageDTO.class);
						return anonymousMessageDTO;
					})
					.collect(Collectors.toList());
		
		return anonymousMessageDTOs;
	}

}
