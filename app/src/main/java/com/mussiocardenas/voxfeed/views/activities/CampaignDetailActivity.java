package com.mussiocardenas.voxfeed.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mussiocardenas.voxfeed.R;
import com.mussiocardenas.voxfeed.presenters.CampaignElement;
import com.mussiocardenas.voxfeed.views.fragments.CampaignDetailFragment;

public class CampaignDetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        Bundle data = this.getIntent().getExtras();

        int campaignId = data.getInt(CampaignElement.ID.toString());

        CampaignDetailFragment fragment = CampaignDetailFragment.newInstance(campaignId);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.campaigns_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch ( item.getItemId() ){
            case android.R.id.home :
                this.finish();
        }
        return true;
    }

}
