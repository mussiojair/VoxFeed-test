package com.mussiocardenas.voxfeed.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

import com.mussiocardenas.voxfeed.R;
import com.mussiocardenas.voxfeed.presenters.CampaignElement;
import com.mussiocardenas.voxfeed.presenters.CampaignsPresenter;
import com.mussiocardenas.voxfeed.views.adapters.CampaignsAdapter;
import com.mussiocardenas.voxfeed.views.adapters.MainPagerAdapter;
import com.mussiocardenas.voxfeed.views.fragments.CampaignsFragment;
import com.mussiocardenas.voxfeed.views.fragments.WelcomeFragment;
import com.mussiocardenas.voxfeed.views.interfaces.CampaignsViewInterface;

import java.util.HashMap;
import java.util.List;

public class CampaignsActivity extends AppCompatActivity
        implements CampaignsFragment.OnFragmentInteractionListener, WelcomeFragment.OnFragmentInteractionListener {



    private TabLayout mTabs;
    private ViewPager mViewPager;
    private MainPagerAdapter mPagerAdapter;
    private android.support.v7.app.ActionBar mActionBar;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaigns_activity);

        mActionBar = getSupportActionBar();

        mTabs = (TabLayout) findViewById(R.id.tabs);

        mViewPager = (ViewPager) findViewById(R.id.pager);


        initView();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.campaigns_menu, menu);
        return true;
    }

    private void initView(){

        mPagerAdapter = new MainPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTabs.setupWithViewPager(mViewPager);

        mActionBar.setElevation(0);

    }


    @Override
    public void onFragmentInteraction(int campaignId) {
        Intent intent = new Intent(this, CampaignDetailActivity.class);
        intent.putExtra(CampaignElement.ID.toString(), campaignId);
        startActivity(intent);
    }

}
