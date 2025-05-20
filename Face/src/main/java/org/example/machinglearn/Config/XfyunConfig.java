package org.example.machinglearn.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XfyunConfig {
    @Value("${spark.api-password}")
    private String apiPassword;

    public String getApiPassword() {
        return apiPassword;
    }
}