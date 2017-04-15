package com.ucf.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "conversation_key")
    private Integer key;

    @ManyToMany
    private List<User> users;

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages;

    @Column(name = "conversation_name")
    private String name;

    public Conversation() {
        super();
    }

    public Integer getKey() {
        return key;
    }

    public Conversation setKey(Integer key) {
        this.key = key;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Conversation setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Conversation setMessages(List<Message> messages) {
        this.messages = messages;
        return this;
    }

    public String getName() {
        return name;
    }

    public Conversation setName(String name) {
        this.name = name;
        return this;
    }
}
