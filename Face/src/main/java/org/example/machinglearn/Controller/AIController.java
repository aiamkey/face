package org.example.machinglearn.Controller;


import org.example.machinglearn.Entity.AiRequest;
import org.example.machinglearn.Entity.AiResponse;
import org.example.machinglearn.Entity.ChatHistory;
import org.example.machinglearn.Service.AiService;
import org.example.machinglearn.Service.ChatHistoryService;
import org.example.machinglearn.VO.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@RestController
@RequestMapping("/api/users")
public class AIController {

    @Autowired
    private AiService aiService;

    @Autowired
    private ChatHistoryService chatHistoryService;


    // 对话接口
    @PostMapping("/chat")
    public ResponseEntity<AiResponse> chat(
            @RequestBody AiRequest request
    ) {
        //记录第一次时间
        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("startTime: " + startTime);

        // 生成 AI 响应
        String response = aiService.generateResponse(request.getPrompt());

        CompletableFuture.runAsync(() -> {
            ChatHistory history = new ChatHistory();
            history.setUserid(request.getId());
            history.setPrompt(request.getPrompt());
            history.setResponse(response);
            chatHistoryService.addChatHistory(history);
        });

        return ResponseEntity.ok(new AiResponse(response, 200));
    }


    //查看对话记录
    @GetMapping("/chatHistory")
    public ResultVO getChatHistory(
            @RequestParam int userId
    ) {
        System.out.println(userId);
        ArrayList<ChatHistory> chatHistory = chatHistoryService.getChatHistory(userId);
        return ResultVO.data(200,"成功获取",chatHistory);
    }


}
