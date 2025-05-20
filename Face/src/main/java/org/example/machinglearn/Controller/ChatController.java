package org.example.machinglearn.Controller;

import org.example.machinglearn.Entity.ChatRequest;
import org.example.machinglearn.Service.ChatService;
import org.example.machinglearn.VO.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/stream")
    public ResultVO chatCompletion(@RequestBody Map<String,Object> map) {

        String id = String.valueOf(map.get("id"));
        String prompt = (String) map.get("prompt");
        ChatRequest request = new ChatRequest();
        request.setId(id);
        request.setPrompt(prompt);
        String chatResponse = chatService.getChatResponse(request);
        return ResultVO.success("成功获取",chatResponse);
    }
}