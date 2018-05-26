package com.mussiocardenas.voxfeed.views.interfaces;

import com.mussiocardenas.voxfeed.presenters.CampaignElement;

import java.util.HashMap;
import java.util.List;

public interface CampaignsViewInterface {

    void showCampaigns(List<HashMap<CampaignElement, String>> campaigns);

}
