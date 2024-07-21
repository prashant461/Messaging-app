package com.snoodify.message.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.snoodify.message.model.AnonymousMessage;

public interface AnonymousMessageRepo extends JpaRepository<AnonymousMessage, Long> {
	Page<AnonymousMessage> findByChatIdOrderByTimestampDesc(Long chatId, Pageable pageable);

}
