package com.snoodify.message.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.snoodify.message.dto.MessageDTO;
import com.snoodify.message.kafka.MessageProducer;
import com.snoodify.message.model.Message;
import com.snoodify.message.repository.MessageRepo;
import com.snoodify.message.service.MessageService;


@Service
public class MessageServiceImpl implements MessageService{
	
	private MessageProducer messageProducer;
	
	private MessageRepo messageRepo;

	private ModelMapper modelMapper;
		
	public MessageServiceImpl(MessageProducer messageProducer, MessageRepo messageRepo, ModelMapper modelMapper) {
		this.messageProducer = messageProducer;
		this.messageRepo = messageRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public MessageDTO sendNormalMessage(MessageDTO messageDTO) {
		
		Message message = modelMapper.map(messageDTO, Message.class);
		message.setTimestamp(LocalDateTime.now());
		
		Message savedMessage =  messageRepo.save(message);
		
		// sending message to kafka (topic- message)
		messageProducer.sendMessage(message);
		return modelMapper.map(savedMessage, MessageDTO.class);
	}

	@Override
	public List<MessageDTO> getHistory(Long chatId, Long user, int pageSize, int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		updateSeen(chatId, user);
		
		Page<Message> messagePage = messageRepo.findByChatIdAndUser(chatId, user, pageable);				
		List<MessageDTO> messageDTOs = messagePage.stream()
				.map(message ->{
					MessageDTO messageDTO = modelMapper.map(message, MessageDTO.class);
					return messageDTO;
				})
				.collect(Collectors.toList());
		return messageDTOs;
	}
	
	private void updateSeen(Long chatId, Long user) {
		List<Message> messages = messageRepo.findByChatIdAndRecipientIdAndSeenFalse(chatId, user);
		
		for(Message message: messages) {
			message.setSeen(true);
			messageRepo.save(message);
		}
	}

	@Override
	public MessageDTO sendReaction(Long messageId, Long reactionId) {
		Optional<Message> messageOptional = messageRepo.findById(messageId);
		Message message = messageOptional.get();
		message.setReactionId(reactionId);
		
		messageRepo.save(message);
		
		return modelMapper.map(message, MessageDTO.class);
	}
	
	@Override
	public MessageDTO deleteReaction(Long messageId) {
		Optional<Message> messageOptional = messageRepo.findById(messageId);
		Message message = messageOptional.get();
		message.setReactionId(null);
		
		messageRepo.save(message);
		
		return modelMapper.map(message, MessageDTO.class);
	}
	
	@Override
	public List<MessageDTO> search(Long chatId, Long user, String text) {
		List<Message> messages = messageRepo.findByMessageBody(chatId, user, text);
		
		List<MessageDTO> messageDTOs = messages.stream()
				.map(message ->{
					MessageDTO messageDTO = modelMapper.map(message, MessageDTO.class);
					return messageDTO;
				})
				.collect(Collectors.toList());
		return messageDTOs;
	}

	@Override
	public void delete(Long chatId, Long user) {
		List<Message> messages = messageRepo.findByChatId(chatId);
		
		for(Message message : messages) {
			if(message.getSenderId()==user) message.setDeletedBySender(true);
			else message.setDeletedByRecipient(true);
			
			// check if deleted by both
			if(message.isDeletedByRecipient() && message.isDeletedBySender()) messageRepo.delete(message);
			else messageRepo.save(message);
		}
	}
}
