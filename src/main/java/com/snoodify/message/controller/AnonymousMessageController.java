package com.snoodify.message.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snoodify.message.dto.AnonymousMessageDTO;
import com.snoodify.message.service.AnonymousMessageService;


@RestController
@RequestMapping("/v1/messages/anonymous")
public class AnonymousMessageController {
	
	private AnonymousMessageService anonymousMessageService;

	public AnonymousMessageController(AnonymousMessageService anonymousMessageService) {
		this.anonymousMessageService = anonymousMessageService;
	}
	
	@PostMapping("/send")
	public ResponseEntity<AnonymousMessageDTO> sendAnonymousMessage(@RequestBody AnonymousMessageDTO anonymousMessageDTO) {
		AnonymousMessageDTO anonymousMessageDTOFromDB = anonymousMessageService.sendAnonymousMessage(anonymousMessageDTO);
		
		return ResponseEntity.ok(anonymousMessageDTOFromDB);
	}
	
	@GetMapping("/history/{chatId}")
	public ResponseEntity<List<AnonymousMessageDTO>> getHistory(
			@PathVariable(name = "chatId") Long chatId,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
	        @RequestParam(value = "pageNumber", defaultValue ="0", required = false) int pageNumber
			) {
		List<AnonymousMessageDTO> anonymousMessageDTOs = anonymousMessageService.getHistory(chatId, pageSize, pageNumber);
		
		return ResponseEntity.ok(anonymousMessageDTOs);
	}
	
}
