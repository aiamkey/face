package org.example.machinglearn.Exception;
/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

public class AiServiceException extends RuntimeException {
    public AiServiceException(String message) {
        super("AI服务异常: " + message);
    }
}