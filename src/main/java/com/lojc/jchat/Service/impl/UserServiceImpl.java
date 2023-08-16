package com.lojc.jchat.Service.impl;

import com.lojc.jchat.Service.UserService;
import com.lojc.jchat.db.Dao.UsersDao;
import com.lojc.jchat.db.POJO.Users;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
 *@Auther Lojc
 *@Date 2023/8/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {

        Users result=usersDao.selectOne(username);
        return result!=null?true:false;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {

       Users result=usersDao.queryUserForLogin(username,password);
        return result;
    }

    @Transactional
    @Override
    public Users saveUser(Users user) {

        String userId=sid.nextShort();

        //为所有用户生成唯一二维码
        user.setQrcode("");

        user.setId(userId);

        usersDao.insertUser(user);

        return user;
    }
}
