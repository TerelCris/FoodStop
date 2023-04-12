package com.mobdeve.s11.group16.foodstop;

import android.view.View;
import android.widget.Button;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RecipeModel {

    String Title;
    String Date;
    String Username;
    String Description;
    String Image;
    FloatingActionButton fab;
    Button btnListener;

    public RecipeModel(String title, String date, String username, String image, String description, FloatingActionButton fab,  Button btnListener) {
        Title = title;
        Date = date;
        Username = username;
        this.fab = fab;
        this.Image = image;
        this.btnListener = btnListener;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public FloatingActionButton getFab() {
        return fab;
    }

    public void setFab(FloatingActionButton fab) {
        this.fab = fab;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }
    public Button getBtnListener() {
        return btnListener;
    }

    public void setBtnListener(Button btnListener) {
        this.btnListener = btnListener;
    }
}
