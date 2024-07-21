package com.snoodify.message.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.snoodify.message.model.Chat;

public interface ChatRepo extends JpaRepository<Chat, Long> {	
	@Query("SELECT c FROM Chat c WHERE (c.senderId = :senderId AND c.deletedBySender = false) OR (c.recipientId = :recipientId AND c.deletedByRecipient = false)")
    List<Chat> findBySenderIdOrRecipientId(
            @Param("senderId") Long senderId,
            @Param("recipientId") Long recipientId
            );
	
	@Query("SELECT c FROM Chat c WHERE (c.senderId = :senderId AND c.recipientId = :recipientId) OR (c.senderId = :recipientId AND c.recipientId = :senderId)")
    Optional<Chat> findChatByParticipants(
    		@Param("senderId") Long senderId,
    		@Param("recipientId") Long recipientId
    		);

}
