package com.mussiocardenas.voxfeed.presenters;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mussiocardenas.voxfeed.BuildConfig;
import com.mussiocardenas.voxfeed.models.entities.PromotedMessage;
import com.mussiocardenas.voxfeed.models.services.FeedService;
import com.mussiocardenas.voxfeed.utils.FileDownloader;
import com.mussiocardenas.voxfeed.views.interfaces.CampaignsViewInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CampaignsPresenter {

    private final String TAG = CampaignsPresenter.class.getSimpleName();

    private CampaignsViewInterface view;
    private FeedService feed;

    public CampaignsPresenter( CampaignsViewInterface view ){

        this.view = view;
        this.feed = new FeedService();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonContent = new FileDownloader(BuildConfig.VOXFEED_SOURCE).download();
                feed.process(jsonContent);
                if (feed.getState() == FeedService.STATE_READY) {

                    List<PromotedMessage> messages = feed.getPromotedMessages();
                    ArrayList<HashMap<CampaignElement, String>> promotedList = new ArrayList<>();

                    for( PromotedMessage pm : messages){

                        HashMap<CampaignElement, String> preparedData = new HashMap<>();
                        preparedData.put( CampaignElement.ID, String.valueOf(pm.getId()));
                        preparedData.put( CampaignElement.DATE, pm.getDate().toString());
                        preparedData.put( CampaignElement.POST_TEXT, pm.getPost().getText());
                        preparedData.put( CampaignElement.POST_IMAGE, pm.getPost().getImage());

                        promotedList.add(preparedData);
                    }


                    sendCampaignsToView(promotedList);
                }
            }
        }).start();
    }

    private void sendCampaignsToView(List<HashMap<CampaignElement, String>> data){
        view.showCampaigns(data);
    }
}
