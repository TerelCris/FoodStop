package com.mobdeve.s11.group16.foodstop;

public class CommentModel {

    String Date;

    String Comment;

    String Username;

    String Title;

    String PostTitle;

    public CommentModel(){

    }

    public CommentModel(String date, String comment, String username, String title, String postTitle){
        Date = date;
        Comment = comment;
        Username = username;
        Title = title;
        PostTitle = postTitle;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPostTitle() {
        return PostTitle;
    }

    public void setPostTitle(String postTitle) {
        PostTitle = postTitle;
    }

}
