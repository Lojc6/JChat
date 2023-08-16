package com.lojc.jchat.db.POJO;

import java.io.Serializable;

/**
 * my_friends
 * @author 
 */
public class MyFriends implements Serializable {
    private String id;

    private String myUserId;

    private String myFriendUserId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }

    public String getMyFriendUserId() {
        return myFriendUserId;
    }

    public void setMyFriendUserId(String myFriendUserId) {
        this.myFriendUserId = myFriendUserId;
    }
}