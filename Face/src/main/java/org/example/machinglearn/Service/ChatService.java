package org.example.machinglearn.Service;

import org.example.machinglearn.Entity.ChatRequest;

public interface ChatService {
    String getChatResponse(ChatRequest request);
}