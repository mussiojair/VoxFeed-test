package com.mussiocardenas.voxfeed.views.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mussiocardenas.voxfeed.R;
import com.mussiocardenas.voxfeed.views.fragments.CampaignsFragment;
import com.mussiocardenas.voxfeed.views.fragments.WelcomeFragment;

import java.util.ArrayList;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private ArrayList<Fragment> mFragments;

    public MainPagerAdapter(Context c, FragmentManager fm) {
        super(fm);

        mContext = c;

        mFragments = new ArrayList<>();
        mFragments.add(WelcomeFragment.newInstance());
        mFragments.add(CampaignsFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.inicio);
            case 1:
                return mContext.getString(R.string.publicaciones);
            default:
                return null;
        }
    }
}
