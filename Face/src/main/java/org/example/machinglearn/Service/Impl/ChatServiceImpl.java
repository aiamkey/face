package org.example.machinglearn.Service.Impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.example.machinglearn.Config.XfyunConfig;
import org.example.machinglearn.Entity.ChatRequest;
import org.example.machinglearn.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private XfyunConfig xfyunConfig;

    @Override
    public String getChatResponse(ChatRequest request) {
        try {
            // 原始代码中的HTTP请求逻辑
            URL url = new URL("https://spark-api-open.xf-yun.com/v2/chat/completions");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // 设置请求头
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + xfyunConfig.getApiPassword());
            con.setDoOutput(true);

            // 构建请求体
            JSONObject jsonBody = buildRequestBody(request);

            // 发送请求
            try(OutputStream os = con.getOutputStream()) {
                os.write(jsonBody.toString().getBytes());
                os.flush();
            }

            // 读取响应
            StringBuilder response = new StringBuilder();
            try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException("API请求失败", e);
        }
    }

    private JSONObject buildRequestBody(ChatRequest request) {
        JSONObject json = new JSONObject();
        json.put("user", request.getId());
        json.put("model", "x1");
        json.put("stream", true);
        json.put("max_tokens", 4096);

        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", request.getPrompt());
        message.put("temperature", 0.5);
        messages.add(message);

        json.put("messages", messages);
        return json;
    }
}