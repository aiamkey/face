package org.example.machinglearn.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.machinglearn.Entity.ChatHistory;
import org.example.machinglearn.Mapper.ChatHistoryMapper;
import org.example.machinglearn.Service.ChatHistoryService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@Service
public class ChatHistoryServiceImpl extends ServiceImpl<ChatHistoryMapper, ChatHistory> implements ChatHistoryService {

    //添加对话记录
    @Async
    @Override
    public void addChatHistory(ChatHistory chatHistory) {
        baseMapper.addChatHistory(chatHistory);
    }

    @Override
    public ArrayList<ChatHistory> getChatHistory(int userId) {
        return baseMapper.getChatHistory(userId);
    }
}
