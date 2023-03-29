package com.mobdeve.s11.group16.foodstop;

public class UploadInfo {
    public String imageName;
    public String imageURL;
    public UploadInfo(){}

    public UploadInfo(String name, String url) {
        this.imageName = name;
        this.imageURL = url;
    }

    public String getImageName() {
        return imageName;
    }
    public String getImageURL() {
        return imageURL;
    }
}
