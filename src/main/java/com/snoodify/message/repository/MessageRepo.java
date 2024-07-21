package com.snoodify.message.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.snoodify.message.model.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
	
	@Query("SELECT m FROM Message m WHERE m.chatId = :chatId AND ((m.senderId = :user AND m.deletedBySender = false) OR (m.recipientId = :user AND m.deletedByRecipient = false)) ORDER BY m.timestamp DESC")
    Page<Message> findByChatIdAndUser(
            @Param("chatId") Long chatId, 
            @Param("user") Long user, 
            Pageable pageable
    );
	
	@Query("SELECT m FROM Message m WHERE ((m.senderId = :user AND m.deletedBySender = false) OR (m.recipientId = :user AND m.deletedByRecipient = false)) AND m.chatId = :chatId AND m.content LIKE CONCAT('%', :text, '%')")
    List<Message> findByMessageBody(
            @Param("chatId") Long chatId, 
            @Param("user") Long user, 
            @Param("text") String text
    );

	List<Message> findByChatId(Long chatId);

	List<Message> findByChatIdAndRecipientIdAndSeenFalse(Long chatId, Long user);
}
