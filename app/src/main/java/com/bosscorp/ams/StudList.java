package com.bosscorp.ams;

public class StudList {
    String UserName;
    String UserStatus;

    public StudList(String UserName,String UserStatus){
        this.UserName = UserName;
        this.UserStatus = UserStatus;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserStatus() {
        return UserStatus;
    }

    public void setUserStatus(String userStatus) {
        UserStatus = userStatus;
    }
}
