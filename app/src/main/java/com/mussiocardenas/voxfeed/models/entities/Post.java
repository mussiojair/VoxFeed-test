package com.mussiocardenas.voxfeed.models.entities;

public class Post {

    public Post(String text, String image, String link) {
        this.text = text;
        this.image = image;
        this.link = link;
    }

    private String text;
    private String image;
    private String link;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
