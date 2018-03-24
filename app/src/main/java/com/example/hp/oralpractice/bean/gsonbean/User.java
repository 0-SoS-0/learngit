package com.example.hp.oralpractice.bean.gsonbean;

import java.io.Serializable;

/**
 * Created by HP on 2018/2/27.
 */

public class User implements Serializable {
    public static User user;
    private User(){

    }
    public static User getInstance() {
        if (user == null) {
            synchronized (User.class) {
                if (user == null) {
                    user = new User();
                }
            }
        }
        return user;
    }
    private long userID;
    //private String userPhone;
    private String userName;
    private String userPassword;

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    private String userGender;
    private String userportraitUrl;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        User.user = user;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getUserID() {
        return userID;
    }



    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

//    public String getUseraccount() {
//        return userPhone;
//    }
//
//    public void setUseraccount(String useraccount) {
//        this.userPhone = useraccount;
//    }

    public String getUserpassword() {
        return userPassword;
    }

    public void setUserpassword(String userpassword) {
        this.userPassword = userpassword;
    }

    public String getUserportrait_url() {
        return userportraitUrl;
    }

    public void setUserportrait_url(String userportrait_url) {
        this.userportraitUrl = userportrait_url;
    }
}
