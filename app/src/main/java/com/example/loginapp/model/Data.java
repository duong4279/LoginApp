package com.example.loginapp.model;

public class Data {
    public String full_name;
    public String html_url;
    public String description;
    public int id;
    public String avatar_url;
    public String login;

    public Data(String login, String avatar_url) {
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return login;
    }


    public String getAvatar_url() {
        return avatar_url;
    }

}
