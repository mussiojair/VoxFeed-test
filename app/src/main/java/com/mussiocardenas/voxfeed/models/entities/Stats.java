package com.mussiocardenas.voxfeed.models.entities;

public class Stats {

    private int clicks;
    private int shares;
    private int likes;
    private int comments;
    private int audience;

    public Stats(int clicks, int shares, int likes, int comments, int audience) {
        this.clicks = clicks;
        this.shares = shares;
        this.likes = likes;
        this.comments = comments;
        this.audience = audience;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }
}
