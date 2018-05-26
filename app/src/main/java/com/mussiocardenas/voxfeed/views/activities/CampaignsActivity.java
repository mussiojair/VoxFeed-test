package com.mussiocardenas.voxfeed.views.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mussiocardenas.voxfeed.R;
import com.mussiocardenas.voxfeed.presenters.CampaignElement;
import com.mussiocardenas.voxfeed.presenters.CampaignsPresenter;
import com.mussiocardenas.voxfeed.views.adapters.CampaignsAdapter;
import com.mussiocardenas.voxfeed.views.interfaces.CampaignsViewInterface;

import java.util.HashMap;
import java.util.List;

public class CampaignsActivity extends AppCompatActivity
        implements CampaignsViewInterface{


    private CampaignsPresenter presenter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaigns_activity);

        presenter = new CampaignsPresenter(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.campaignRecycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void showCampaigns(final List<HashMap<CampaignElement, String>> campaigns) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter = new CampaignsAdapter(CampaignsActivity.this, campaigns);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

    }

}
