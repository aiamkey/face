package org.example.machinglearn.Service.Impl;

import org.example.machinglearn.Exception.AiServiceException;
import org.example.machinglearn.Service.AiService;
import org.example.machinglearn.Util.OpenAIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@Service
public class AiServiceImpl implements AiService {


    @Autowired
    private OpenAIClient openAIClient;

    @Override
    public String generateResponse(String prompt) {
        try {
            return openAIClient.chatWithModel(prompt);
        } catch (AiServiceException e) {
            throw new RuntimeException("生成响应失败", e);
        }
    }
}
