package com.mussiocardenas.voxfeed.presenters;

import com.mussiocardenas.voxfeed.models.entities.PromotedMessage;
import com.mussiocardenas.voxfeed.models.services.FeedService;
import com.mussiocardenas.voxfeed.views.interfaces.CampaignViewInterface;

import java.util.HashMap;

public class CampaignPresenter {

    private final String TAG = CampaignPresenter.class.getSimpleName();

    private CampaignViewInterface mView;
    private FeedService mFeed;
    private int mId;

    public CampaignPresenter( int id, CampaignViewInterface view ){

        mView = view;
        mFeed = FeedService.getInstance();
        mId = id;

        if (mFeed.getState() == FeedService.STATE_READY) {

            PromotedMessage pm = mFeed.getPromotedMessage(mId);

            if(pm != null) {

                HashMap<CampaignElement, String> campaign = new HashMap<>();

                campaign.put(CampaignElement.ID, String.valueOf(pm.getId()));
                campaign.put(CampaignElement.STATS_LIKES, String.valueOf(pm.getStats().getLikes()));
                campaign.put(CampaignElement.STATS_CLICK, String.valueOf(pm.getStats().getClicks()));
                campaign.put(CampaignElement.STATS_AUDIENCE, String.valueOf(pm.getStats().getAudience()));
                campaign.put(CampaignElement.STATS_COMMENTS, String.valueOf(pm.getStats().getComments()));
                campaign.put(CampaignElement.STATS_SHARES, String.valueOf(pm.getStats().getShares()));
                campaign.put(CampaignElement.EARNINGS, String.valueOf(pm.getEarnings()));
                campaign.put(CampaignElement.SOCIAL_NETWORK, pm.getSocialNetwork());

                campaign.put(CampaignElement.BRAND_LOGO, pm.getBrand().getLogo());
                campaign.put(CampaignElement.BRAND_NAME, pm.getBrand().getName());

                campaign.put(CampaignElement.CAMPAIGN_NAME, pm.getCampaign().getName());
                campaign.put(CampaignElement.CAMPAIGN_COVER_IMAGE, pm.getCampaign().getCoverImage());

                view.showCampaign(campaign);
            }
        }

    }
}
