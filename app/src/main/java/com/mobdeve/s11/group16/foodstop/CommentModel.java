package com.mobdeve.s11.group16.foodstop;

public class CommentModel {

    String Date;

    String Body;

    String Username;

    public CommentModel(String date, String body, String username){
        Date = date;
        Body = body;
        Username = username;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getBody() {
        return Body;
    }

    public void setTitle(String body) {
        Body = body;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
