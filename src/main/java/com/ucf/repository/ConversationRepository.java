package com.ucf.repository;

import com.ucf.entity.Conversation;
import com.ucf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* Repositories provide a means for services to communicate with the database.
*
* The conversation repository uses no database queries beyond the ones provided in
* the JpaRepository package.
* */

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
}
