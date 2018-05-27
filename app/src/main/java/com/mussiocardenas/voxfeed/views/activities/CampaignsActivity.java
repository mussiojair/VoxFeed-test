package com.mussiocardenas.voxfeed.views.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

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
    private TabLayout mTabs;
    private android.support.v7.app.ActionBar mActionBar;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaigns_activity);

        mActionBar = getSupportActionBar();

        presenter = new CampaignsPresenter(this);
        mTabs = (TabLayout) findViewById(R.id.tabs);

        initView();

        mRecyclerView = (RecyclerView) findViewById(R.id.campaignRecycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.campaigns_menu, menu);
        return true;
    }

    private void initView(){


        mTabs.addTab(mTabs.newTab().setText(R.string.inicio));
        mTabs.addTab(mTabs.newTab().setText(R.string.publicaciones));

        mActionBar.setElevation(0);

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
