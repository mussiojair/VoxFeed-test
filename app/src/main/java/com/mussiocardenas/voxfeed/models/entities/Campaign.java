package com.mussiocardenas.voxfeed.models.entities;

public class Campaign {

    private String coverImage;
    private String name;

    public Campaign(String coverImage, String name) {
        this.coverImage = coverImage;
        this.name = name;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
