package com.example.store;

import com.example.entity.Message;

import java.util.List;

public interface MessageService {
    String saveMessage(String message);
    List<Message> getMessages();
}

