package com.ucf.entity;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_key")
    private Integer key;

    @Column
    private User user;

    @ManyToOne
    @JoinColumn(name = "conversation_key")
    private Conversation conversation;

    @Column(name = "message_text")
    private String text;

    public Message() {
        super();
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
}
