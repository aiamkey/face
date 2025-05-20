package org.example.machinglearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@SpringBootApplication(scanBasePackages = "org.example")
@ComponentScan(basePackages = {
        "org.example.machinglearn.Controller",    // 扫描 web 模块的控制器
        "org.example.machinglearn.Service",     // 扫描 common 模块的 Service 包
        "org.example.machinglearn.Util",       // 新增！扫描 common 模块的 util 包
        "org.example.machinglearn.Entity",
        "org.example.machinglearn.Mapper",
        "org.example.machinglearn.Exception",
        "org.example.machinglearn.Service.Impl",
        "org.example.machinglearn.VO",
        "org.example.machinglearn.Config",
})
public class ZHApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZHApplication.class, args);
    }

}
