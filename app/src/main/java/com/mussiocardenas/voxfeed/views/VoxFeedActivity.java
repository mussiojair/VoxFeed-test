package com.mussiocardenas.voxfeed.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mussiocardenas.voxfeed.R;
import com.mussiocardenas.voxfeed.presenters.CampaignsPresenter;
import com.mussiocardenas.voxfeed.views.activities.CampaignsActivity;

public class VoxFeedActivity extends AppCompatActivity {

    private CampaignsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vox_feed);

        Intent intent = new Intent(this, CampaignsActivity.class);
        startActivity(intent);
        finish();
    }


}
