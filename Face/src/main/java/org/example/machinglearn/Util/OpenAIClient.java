package org.example.machinglearn.Util;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.machinglearn.Exception.AiServiceException;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

public class OpenAIClient {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.api.base-url}")
    private String baseUrl;

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public OpenAIClient() {
        this.client = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public String chatWithModel(String prompt) throws AiServiceException {
        try {
            //deepseek r1:deepseek-reasoner
            //deepseek v3:deepseek-chat
            //
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "deepseek-chat");
            requestBody.put("messages", Arrays.asList(
                    new HashMap<String, Object>() {{
                        put("role", "system");
                        put("content", "你是一个乐于助人的智能助手，精通机器学习和人工智能。你可以回答各种问题，提供有用的建议和信息。如果你不知道答案，你可以说我不太了解。");
                    }},
                    new HashMap<String, Object>() {{
                        put("role", "user");
                        put("content", prompt);
                    }}
            ));
            requestBody.put("temperature", 1);  //保持创造力，范围0~1
            requestBody.put("max_tokens", 1024); // 控制响应长度
            String jsonBody = objectMapper.writeValueAsString(requestBody);


            Request request = new Request.Builder()
                    .url(baseUrl + "/chat/completions") // 拼接完整 URL /chat/completions
                    .post(RequestBody.create(jsonBody, MediaType.parse("application/json")))
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .build();


            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new AiServiceException("API 请求失败: " + response.code() + " - " + response.message());
                }
                String responseBody = response.body().string();
                return parseResponse(responseBody);
            }
        } catch (IOException e) {
            throw new AiServiceException("请求处理异常: " + e.getMessage());
        }
    }

    private String parseResponse(String responseBody) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(responseBody);
        return rootNode
                .path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();
    }
}
