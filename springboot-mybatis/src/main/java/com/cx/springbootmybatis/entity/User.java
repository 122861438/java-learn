package com.cx.springbootmybatis.entity;

public class User {

    private Integer id;

    private String username;

    private String password;

    private String realName;

    public User(){}

    public User(String username,String password,String realName){
        this.username = username;
        this.password = password;
        this.realName = realName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String toString(){
        return "{ id : "+id+", username : "+username+", password : "+password+", realname : "+realName+"}";
    }
}
