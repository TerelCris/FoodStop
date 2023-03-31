package com.mobdeve.s11.group16.foodstop;

import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RecipeModel {

    String Title;
    String Date;
    String Username;
    String image;
    FloatingActionButton fab;



    public RecipeModel(String title, String date, String username, String image, FloatingActionButton fab) {
        Title = title;
        Date = date;
        Username = username;
        this.fab = fab;
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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

    public FloatingActionButton getFab() {
        return fab;
    }

    public void setFab(FloatingActionButton fab) {
        this.fab = fab;
    }

    public void setFavoriteOnClickListener(View.OnClickListener onClickListener) {
        fab.setOnClickListener(onClickListener);
        if (fab.isActivated()) {
            fab.setImageResource(R.mipmap.star);
        } else {
            fab.setImageResource(R.mipmap.favorite);
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
