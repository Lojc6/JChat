package com.lojc.jchat.Service;

import com.lojc.jchat.db.POJO.Users;

public interface UserService {

    //判断用户名是否存在
    public boolean queryUsernameIsExist(String username);

    //查询用户是否存在
    public Users queryUserForLogin(String username,String password);

    //用户注册
    public Users saveUser(Users user);


}
