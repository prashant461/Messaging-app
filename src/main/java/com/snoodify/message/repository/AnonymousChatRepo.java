package com.snoodify.message.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.snoodify.message.dto.AnonymousChatDTO;
import com.snoodify.message.model.AnonymousChat;

public interface AnonymousChatRepo extends JpaRepository<AnonymousChat, Long>{

	Optional<AnonymousChat> findBySenderIdAndRecipientIdAndCreatedBy(Long senderId, Long recipientId,
			Long senderId2);

	List<AnonymousChat> findBySenderIdOrRecipientId(Long senderId, Long recipientId);

}
