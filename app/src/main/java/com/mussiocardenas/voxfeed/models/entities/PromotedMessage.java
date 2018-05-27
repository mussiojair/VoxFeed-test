package com.mussiocardenas.voxfeed.models.entities;

import java.util.Date;

public class PromotedMessage {

    private int id;
    private Date date;
    private String socialNetwork;
    private User user;
    private Campaign campaign;
    private Brand brand;
    private Post post;
    private Stats stats;
    private float earnings;

    public PromotedMessage(int id, Date date, String socialNetwork, User user, Campaign campaign, Brand brand, Post post, Stats stats, float earnings) {
        this.id = id;
        this.date = date;
        this.socialNetwork = socialNetwork;
        this.user = user;
        this.campaign = campaign;
        this.brand = brand;
        this.post = post;
        this.stats = stats;
        this.earnings = earnings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSocialNetwork() {
        return socialNetwork.substring(0,1).toUpperCase() + socialNetwork.substring(1);
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public float getEarnings() {
        return earnings;
    }

    public void setEarnings(float earnings) {
        this.earnings = earnings;
    }
}
