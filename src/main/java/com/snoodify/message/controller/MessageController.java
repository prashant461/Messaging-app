package com.snoodify.message.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snoodify.message.dto.MessageDTO;
import com.snoodify.message.service.MessageService;

@RestController
@RequestMapping("/v1/messages")
public class MessageController {
	
	private MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping("/send")
    public ResponseEntity<MessageDTO> sendNormalMessage(@RequestBody MessageDTO messageDTO) {
        messageDTO =  messageService.sendNormalMessage(messageDTO);
        
		return ResponseEntity.ok(messageDTO);
    }
	
	@GetMapping("/history/{chatId}/{user}")
	public ResponseEntity<List<MessageDTO>> getHistory(
			@PathVariable(name = "chatId") Long chatId,
			@PathVariable(name = "user") Long user,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
	        @RequestParam(value = "pageNumber", defaultValue ="0", required = false) int pageNumber
			) {
		List<MessageDTO> messageDTOs = messageService.getHistory(chatId, user, pageSize, pageNumber);
		
		return ResponseEntity.ok(messageDTOs);
	}
	
	@PostMapping("/sendReaction/{messageId}/{reactionId}")
	public ResponseEntity<MessageDTO> sendReaction(
			@PathVariable Long messageId,
			@PathVariable Long reactionId
			) {
		MessageDTO messageDTO = messageService.sendReaction(messageId, reactionId);
		
		return ResponseEntity.ok(messageDTO);
	}
	
	@DeleteMapping("/deleteReaction/{messageId}")
	public ResponseEntity<MessageDTO> deleteReaction(
			@PathVariable Long messageId
			) {
		MessageDTO messageDTO = messageService.deleteReaction(messageId);
		
		return ResponseEntity.ok(messageDTO);
	}
	
	@GetMapping("search/{chatId}/{user}")
	public ResponseEntity<List<MessageDTO>> search(
			@PathVariable(name = "chatId") Long chatId,
			@PathVariable(name = "user") Long user,
			@RequestParam String text
			) {
		System.out.println(text);
		List<MessageDTO> messageDTOs = messageService.search(chatId, user, text);
		
		return ResponseEntity.ok(messageDTOs);
	}
	
	@DeleteMapping("/delete/{chatId}/{user}")
	public ResponseEntity<Void> delete(
			@PathVariable(name = "chatId") Long chatId,
			@PathVariable(name = "user") Long user
			) {
		messageService.delete(chatId, user);
		
		return ResponseEntity.ok().build();
	}
	
}
