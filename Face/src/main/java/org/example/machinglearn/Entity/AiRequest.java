package org.example.machinglearn.Entity;


import lombok.Data;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@Data
public class AiRequest {
    private Integer id;
    private String prompt;

    public AiRequest(Integer id, String prompt) {
        this.id = id;
        this.prompt = prompt;
    }

}
