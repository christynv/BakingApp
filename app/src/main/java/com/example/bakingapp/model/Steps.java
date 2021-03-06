package com.example.bakingapp.model;

import java.util.List;

public class Steps {

    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public Steps() {

    }

    public Steps(String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public String getShortDescription() {return shortDescription;}

    public void setShortDescription(String shortDescription) {this.shortDescription = shortDescription;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getVideoURL() {return videoURL;}

    public void setVideoURL(String videoURL) {this.videoURL = videoURL;}

    public String getThumbnailURL() {return thumbnailURL;}

    public void setThumbnailURL(String thumbnailURL) {this.thumbnailURL = thumbnailURL;}
}
