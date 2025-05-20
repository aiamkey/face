package org.example.machinglearn.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.machinglearn.Entity.User;

import java.io.File;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

public interface UserService extends IService<User>{

    //登录选项,并且接收json格式的数据
    Object check(String phone, String password);

    //注册选项,并且接收json格式的数据
    boolean register(String username, String phone, String password, int age, String gender, byte[] avatar);

    //查询用户信息
    User getUserInfo(Integer id);

    //手机号是否存在
    boolean isPhoneExists(String phone);
}
