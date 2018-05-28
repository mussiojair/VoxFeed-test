package com.mussiocardenas.voxfeed.views.adapters;

public class CampaignStats {
    public String stat;
    public String value;

    public CampaignStats(){

    }

    public CampaignStats setStat(String stat) {
        this.stat = stat;
        return this;
    }

    public CampaignStats setValue(String value) {
        this.value = value;
        return this;
    }
}