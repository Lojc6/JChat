package com.lojc.jchat.db.Dao;

import com.lojc.jchat.db.POJO.MyFriends;

public interface MyFriendsDao {
    int deleteByPrimaryKey(String id);

    int insert(MyFriends record);

    int insertSelective(MyFriends record);

    MyFriends selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MyFriends record);

    int updateByPrimaryKey(MyFriends record);
}