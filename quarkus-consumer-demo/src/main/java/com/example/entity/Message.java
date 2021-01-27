package com.example.entity;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = Message.GET_ALL_MESSAGES, query = "select m from Message m order by m.id")
})
public class Message {

    public static final String GET_ALL_MESSAGES = "Message.getAllMessages";

    @Id
    @GeneratedValue
    private Long id;
    private String text;

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
