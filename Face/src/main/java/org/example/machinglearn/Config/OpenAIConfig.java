package org.example.machinglearn.Config;


import org.example.machinglearn.Util.OpenAIClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@Configuration
public class OpenAIConfig {

    @Bean
    public OpenAIClient openAIClient() {
        return new OpenAIClient(); // 根据构造方法调整
    }
}
