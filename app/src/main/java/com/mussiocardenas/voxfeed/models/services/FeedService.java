package com.mussiocardenas.voxfeed.models.services;


import android.util.Log;

import com.google.gson.Gson;
import com.mussiocardenas.voxfeed.models.entities.PromotedMessage;

import java.util.ArrayList;

public class FeedService {

    private static final String TAG = FeedService.class.getSimpleName();

    public static String STATE_READY = "STATE_READY";
    public static String STATE_EMPTY = "STATE_EMPTY";
    private static FeedService feedService;

    private String state;
    private ArrayList<PromotedMessage> promotedMessages;


    private FeedService(){
        state = STATE_EMPTY;
        promotedMessages = new ArrayList<>();
    }

    public static FeedService getInstance(){
        if(feedService == null){
            feedService = new FeedService();
        }

        return feedService;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<PromotedMessage> getPromotedMessages() {
        return promotedMessages;
    }

    public void process(String jsonContent){

        try{

            final PromotedMessage[] promotedMessages = new Gson().fromJson(jsonContent, PromotedMessage[].class);

            for( PromotedMessage pm : promotedMessages ){
                this.promotedMessages.add( pm );
            }

            state = STATE_READY;

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public PromotedMessage getPromotedMessage(int id){

        for( PromotedMessage pm: promotedMessages){
            if(pm.getId() == id)
                return pm;
        }

        return null;
    }
}
