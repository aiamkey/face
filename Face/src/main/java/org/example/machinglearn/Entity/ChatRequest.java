package org.example.machinglearn.Entity;

import lombok.Data;

//讯飞星火大模型的请求类
@Data
public class ChatRequest {
    private String id;
    private String prompt;

}