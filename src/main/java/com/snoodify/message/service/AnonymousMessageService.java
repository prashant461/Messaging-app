package com.snoodify.message.service;

import java.util.List;

import com.snoodify.message.dto.AnonymousMessageDTO;

public interface AnonymousMessageService {

	AnonymousMessageDTO sendAnonymousMessage(AnonymousMessageDTO anonymousMessageDTO);

	List<AnonymousMessageDTO> getHistory(Long chatId, int pageSize, int pageNumber);

}
