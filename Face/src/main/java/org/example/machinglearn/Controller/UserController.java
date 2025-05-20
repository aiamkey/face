package org.example.machinglearn.Controller;


import org.example.machinglearn.Entity.User;
import org.example.machinglearn.Service.UserService;
import org.example.machinglearn.VO.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * author 张宇
 * version 1.0
 * date 2025/4/29 22:47
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    //查询个人信息
    @GetMapping(value = "/info",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO info(@RequestParam Integer id){
        System.out.println("id:"+id);
        User user = userService.getUserInfo(id);
        if(user == null){
            return ResultVO.error("查询失败");
        }else {
            return ResultVO.data(200,"查询成功",user);
        }
    }





}
