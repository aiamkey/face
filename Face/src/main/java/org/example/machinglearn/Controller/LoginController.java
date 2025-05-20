package org.example.machinglearn.Controller;

import org.example.machinglearn.Service.UserService;
import org.example.machinglearn.VO.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@RestController
@RequestMapping("/api/users")
public class LoginController {

    @Autowired
    private UserService userService;


    //登录选项,并且接收json格式的数据
    @PostMapping("/login")
    public ResultVO login(@RequestBody Map<String, Object> map) {
        if (map == null) {
            return ResultVO.error("参数为空");
        } else if (map.get("phone") == null || map.get("password") == null) {
            return ResultVO.error("账号或密码为空");
        } else {
            String phone = (String) map.get("phone");
            String password = (String) map.get("password");
            System.out.println("phone:" + phone);
            System.out.println("password:" + password);
            Object check = userService.check(phone, password);
            System.out.println("check:" + check);

            if (check==null) {
                return ResultVO.data(400,"用户名或密码错误", 0);
            } else {

                return ResultVO.success("登录成功", (int) check);
            }
        }
    }

    //注册选项,并且接收json格式的数据
    @PostMapping("/register")
    public ResultVO register(@RequestParam String username,
                             @RequestParam String phone,
                             @RequestParam String password,
                             @RequestParam Integer age,
                             @RequestParam String gender,
                             @RequestParam MultipartFile avatar) throws IOException {
        if (userService.isPhoneExists(phone)) {
            return ResultVO.success("用户已注册",false);
        }else {
            boolean register = userService.register(username, phone, password, age, gender, avatar.getBytes());
            if (register) {
                return ResultVO.success("注册成功",true);
            }else {
                return ResultVO.success("注册失败",false);
            }
        }
    }

}
