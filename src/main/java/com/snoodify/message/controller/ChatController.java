package com.snoodify.message.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snoodify.message.dto.ChatDTO;
import com.snoodify.message.service.ChatService;

@RestController
@RequestMapping("/v1/chat")
public class ChatController {
	
	private ChatService chatService;

	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}
	
	@GetMapping("/allChats/{senderId}")
	public ResponseEntity<List<ChatDTO>> getAllChats(@PathVariable (name = "senderId") Long senderId) {
		
		List<ChatDTO> chatDTOs = chatService.getAllChats(senderId);
		
		return ResponseEntity.ok(chatDTOs);
	}
	
	@PostMapping("/new/{senderId}/{recipientId}")
	public ResponseEntity<ChatDTO> createNewChat(
			@PathVariable (name = "senderId") Long senderId,
			@PathVariable (name = "recipientId") Long recipientId
			) {
		ChatDTO chatDTO = chatService.createNewChat(senderId, recipientId);
		
		return ResponseEntity.ok(chatDTO);
	}
	
	@PatchMapping("/mute/{chatId}/{user}")
	public ResponseEntity<Void> muteChat(
			@PathVariable(name = "chatId") Long chatId,
			@PathVariable(name = "user") Long user
			) {
		chatService.muteChat(chatId, user);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/delete/{chatId}/{user}")
	public ResponseEntity<Void> deleteChat(
			@PathVariable(name = "chatId") Long chatId,
			@PathVariable(name = "user") Long user
			) {
		chatService.deleteChat(chatId, user);
		
		return ResponseEntity.ok().build();
	}
}
