package com.mussiocardenas.voxfeed.models.entities;

public class User {

    private String username;
    private String profileImage;

    public User(String username, String profileImage) {
        this.username = username;
        this.profileImage = profileImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
