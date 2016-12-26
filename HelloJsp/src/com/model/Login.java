package com.model;

/**
 * Created by hdhamee on 12/13/16.
 */
public class Login {
    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    private String uname;
    private String pass;
    private  String remember;

    public String getUname() {
        return uname;
    }

    public String getPass() {
        return pass;
    }

    public String getRemember() {
        return remember;
    }

}
