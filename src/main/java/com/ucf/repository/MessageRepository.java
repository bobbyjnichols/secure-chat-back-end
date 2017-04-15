package com.ucf.repository;

import com.ucf.entity.Conversation;
import com.ucf.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>, JpaSpecificationExecutor {
    @Query(value = "SELECT m.key FROM Message m WHERE m.conversation = ?1 ORDER BY m.timestamp")
    List<Integer> getMessages(Conversation conversation);

    Page<Message> findByKeyIn(List<Integer> keys, Pageable pageable);
}
