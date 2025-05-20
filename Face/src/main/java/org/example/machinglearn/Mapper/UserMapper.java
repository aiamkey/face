package org.example.machinglearn.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.machinglearn.Entity.User;

import java.io.File;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    //校验用户名和密码是否正确,并返回其对应的id
    @Select("SELECT id FROM user WHERE phone = #{phone} AND password = #{password}")
    Integer check(String phone, String password);

    // 检查手机号是否已存在
    @Select("SELECT EXISTS(SELECT 1 FROM user WHERE phone = #{phone})")
    boolean isPhoneExists(String phone);

    //注册
    @Insert("INSERT INTO user (username,phone,password,age,gender,avatar) VALUES (#{username},#{phone},#{password},#{age},#{gender},#{avatar})")
    boolean register(String username, String phone, String password, int age, String gender, byte[] avatar);

    //查询用户信息
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserInfo(Integer id);

}
