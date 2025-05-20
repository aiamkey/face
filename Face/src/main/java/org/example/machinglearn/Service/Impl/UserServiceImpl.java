package org.example.machinglearn.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.machinglearn.Entity.User;
import org.example.machinglearn.Mapper.UserMapper;
import org.example.machinglearn.Service.UserService;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //登录校验
    @Override
    public Object check(String phone, String password) {
        Integer check = baseMapper.check(phone, password);
        return check;
    }

    //注册校验
    @Override
    public boolean register(String username, String phone, String password, int age, String gender, byte[] avatar) {
        boolean register = baseMapper.register(username, phone, password, age, gender, avatar);
        return register;
    }

    @Override
    public User getUserInfo(Integer id) {
        User user = baseMapper.getUserInfo(id);
        return user;
    }

    @Override
    public boolean isPhoneExists(String phone) {
        boolean phoneExists = baseMapper.isPhoneExists(phone);
        return phoneExists;
    }


}
