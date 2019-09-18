package com.example.android.bakingproject.data.POJOS;

import com.google.gson.annotations.SerializedName;

import java.util.logging.StreamHandler;

public class Steps {
    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
