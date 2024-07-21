package com.snoodify.message.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snoodify.message.dto.AnonymousChatDTO;
import com.snoodify.message.dto.ChatDTO;
import com.snoodify.message.repository.AnonymousChatRepo;
import com.snoodify.message.service.AnonymousChatService;

import jakarta.ws.rs.PATCH;

@RestController
@RequestMapping("/v1/chat/anonymous")
public class AnonymousChatController {

	private AnonymousChatService anonymousChatService;
	
	public AnonymousChatController(AnonymousChatService anonymousChatService) {
		this.anonymousChatService = anonymousChatService;
	}

	@GetMapping("/allChats/{senderId}")
	public ResponseEntity<List<AnonymousChatDTO>> getAllChats(@PathVariable (name = "senderId") Long senderId){
		List<AnonymousChatDTO> anonymousChatDTOs = anonymousChatService.getAllChats(senderId);
		
		return ResponseEntity.ok(anonymousChatDTOs);
	}
	
	@PostMapping("/createNewChat/{senderId}/{recipientId}")
	public ResponseEntity<AnonymousChatDTO> createNewchat(
			@PathVariable Long senderId,
			@PathVariable Long recipientId
			) {
		AnonymousChatDTO anonymousChatDTO = anonymousChatService.createNewChat(senderId, recipientId);
		return ResponseEntity.ok(anonymousChatDTO);
	}
	
	@PatchMapping("/updateAnonymous/{chatId}")
	public ResponseEntity<AnonymousChatDTO> updateAnonoumsFlag(
			@PathVariable Long chatId
			) {
		AnonymousChatDTO chatDTO = anonymousChatService.updateAnonymousFlag(chatId);
		return ResponseEntity.ok(chatDTO);
	}
}
