package com.ucf.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/*
* Entity classes are used to define objects stored in the database and their relations.
* This is used when hibernate updates the database schema or needs to determine SQL queries.
*
* The message entity defines an object that has been 'sent' by one user to another user in
* the context of a conversation.
* */

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_key")
    private Integer key;

    @ManyToOne
    @JoinColumn(name = "user_key")
    private User user;

    @ManyToOne
    @JoinColumn(name = "conversation_key")
    private Conversation conversation;

    @Column(name = "message_text")
    private String text;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @PrePersist
    protected void onCreate() {
        timestamp = new Timestamp(new Date().getTime());
    }

    public Message() {
        super();
    }

    public Message(User user, Conversation conversation, String text) {
        this.user = user;
        this.conversation = conversation;
        this.text = text;
    }

    public Integer getKey() {
        return key;
    }

    public Message setKey(Integer key) {
        this.key = key;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Message setUser(User user) {
        this.user = user;
        return this;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public Message setConversation(Conversation conversation) {
        this.conversation = conversation;
        return this;
    }

    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

}
