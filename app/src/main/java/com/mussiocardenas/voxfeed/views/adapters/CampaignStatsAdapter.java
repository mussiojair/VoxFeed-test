package com.mussiocardenas.voxfeed.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mussiocardenas.voxfeed.R;

import java.util.List;

public class CampaignStatsAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<CampaignStats> mDataset;

    public CampaignStatsAdapter(Context c, List<CampaignStats> dataset){
        this.context = c;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mDataset = dataset;
    }

    @Override
    public int getCount() {
        return this.mDataset.size();
    }

    @Override
    public CampaignStats getItem(int i) {
        return this.mDataset.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        row = inflater.inflate(R.layout.campaign_stats_item_list, null);


        CampaignStats cs = getItem(i);

        ImageView icon = row.findViewById(R.id.stats_type_icon);
        TextView text = row.findViewById(R.id.stats_type_text);
        TextView value = row.findViewById(R.id.stats_type_value);

        text.setText( cs.stat );
        value.setText( cs.value );


        return row;
    }

}
