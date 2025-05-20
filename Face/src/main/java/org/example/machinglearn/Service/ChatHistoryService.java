package org.example.machinglearn.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.machinglearn.Entity.ChatHistory;
import java.util.ArrayList;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

public interface ChatHistoryService extends IService<ChatHistory> {


    void addChatHistory(ChatHistory chatHistory);

    ArrayList<ChatHistory> getChatHistory(int userId);

}
