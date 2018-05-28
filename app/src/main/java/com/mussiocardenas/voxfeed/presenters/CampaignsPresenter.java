package com.mussiocardenas.voxfeed.presenters;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mussiocardenas.voxfeed.BuildConfig;
import com.mussiocardenas.voxfeed.models.entities.PromotedMessage;
import com.mussiocardenas.voxfeed.models.services.FeedService;
import com.mussiocardenas.voxfeed.utils.FileDownloader;
import com.mussiocardenas.voxfeed.views.interfaces.CampaignsViewInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CampaignsPresenter {

    private final String TAG = CampaignsPresenter.class.getSimpleName();

    private CampaignsViewInterface view;
    private FeedService feed;

    public CampaignsPresenter( CampaignsViewInterface view ){

        this.view = view;
        this.feed = FeedService.getInstance();

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

                        // ID
                        preparedData.put( CampaignElement.ID, String.valueOf(pm.getId()));

                        // SN Account Data
                        preparedData.put( CampaignElement.SOCIAL_NETWORK, pm.getSocialNetwork());
                        preparedData.put( CampaignElement.USER_USERNAME, pm.getUser().getUsername());
                        preparedData.put( CampaignElement.USER_PROFILE_IMAGE, pm.getUser().getProfileImage());

                        // Post Data
                        preparedData.put( CampaignElement.POST_TEXT, pm.getPost().getText());
                        preparedData.put( CampaignElement.POST_IMAGE, pm.getPost().getImage());

                        // Date (Human format)
                        SimpleDateFormat sdf = new SimpleDateFormat(BuildConfig.DATE_FORMAT_PATTERN);
                        String date = sdf.format(pm.getDate());

                        preparedData.put( CampaignElement.DATE, date);


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
