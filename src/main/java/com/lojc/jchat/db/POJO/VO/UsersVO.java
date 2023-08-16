package com.lojc.jchat.db.POJO.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * users
 * @author 
 */
@Data
public class UsersVO implements Serializable {
    private String id;

    private String username;

    private String faceImage;

    private String faceImageBig;

    private String nickname;

    private String qrcode;

}