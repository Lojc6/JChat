package com.lojc.jchat;

import com.lojc.jchat.Service.UserService;
import com.lojc.jchat.db.Dao.UsersDao;
import com.lojc.jchat.db.POJO.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JChatApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersDao usersDao;

    @Test
    void contextLoads() {
        Users user=new Users();
        user.setUsername("dddd");
        boolean b = userService.queryUsernameIsExist(user.getUsername());
        Users dddd = userService.queryUserForLogin("dddd", "646458");

        System.out.println(b);
        System.out.println(dddd);
    }

}
