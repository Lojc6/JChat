package com.lojc.jchat.controller;

/*
 *@Auther Lojc
 *@Date 2023/8/2
 */

import com.lojc.jchat.Service.UserService;
import com.lojc.jchat.Utils.JChatJSONResult;
import com.lojc.jchat.Utils.MD5Utils;
import com.lojc.jchat.db.POJO.Users;
import com.lojc.jchat.db.POJO.VO.UsersVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/registOrLogin")

    public JChatJSONResult registOrLogin(@RequestBody Users users) throws Exception {


        //判断用户名是否为空      用到了commons-lang的包
        if (StringUtils.isBlank(users.getUsername())
                || StringUtils.isBlank(users.getPassword())) {    //这里isBlank传入的实际上是一个字符序列charsquence,String实现了这个接口

            return JChatJSONResult.errorMsg("用户名或密码不能为空");

        }


        boolean usernameIsExist = userService.queryUsernameIsExist(users.getUsername());

        Users userResult=null;
        if (usernameIsExist){
            //1.登录
            userResult = userService.queryUserForLogin(users.getUsername(), MD5Utils.getMD5Str(users.getPassword()));
            if (userResult==null){
                return JChatJSONResult.errorMsg("用户名或密码不正确");
            }
        }else {
            //2.注册
            users.setNickname(users.getUsername());
            users.setFaceImage("");
            users.setFaceImageBig("");
            users.setPassword(MD5Utils.getMD5Str(users.getPassword()));
            userResult = userService.saveUser(users);
        }


        UsersVO usersVO=new UsersVO();
        BeanUtils.copyProperties(usersVO,userResult);



        return JChatJSONResult.ok(usersVO);

    }
}

