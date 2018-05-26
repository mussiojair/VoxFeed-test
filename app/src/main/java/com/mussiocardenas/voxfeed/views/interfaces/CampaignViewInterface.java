package com.mussiocardenas.voxfeed.views.interfaces;

import com.mussiocardenas.voxfeed.presenters.CampaignElement;

import java.util.HashMap;

public interface CampaignViewInterface {

    void showCampaign(HashMap<CampaignElement, String> campaign);

}
