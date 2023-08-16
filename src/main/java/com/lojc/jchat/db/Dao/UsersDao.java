package com.lojc.jchat.db.Dao;

import com.lojc.jchat.db.POJO.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersDao {
    Users selectOne(String username);

    public Users queryUserForLogin(@Param("username") String username, @Param("password") String password);

    void insertUser(Users user);
}