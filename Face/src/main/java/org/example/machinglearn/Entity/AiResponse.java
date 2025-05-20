package org.example.machinglearn.Entity;

import lombok.Data;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@Data
public class AiResponse {
    private String response;
    private Integer code;
    public AiResponse(String response, Integer code) {
        System.out.println("response:"+response);
        this.response = response;
        this.code = code;
    }

}