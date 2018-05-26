package com.mussiocardenas.voxfeed.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mussiocardenas.voxfeed.R;
import com.mussiocardenas.voxfeed.presenters.CampaignElement;

import java.util.HashMap;
import java.util.List;

public class CampaignsAdapter extends RecyclerView.Adapter<CampaignsAdapter.ViewHolder>{

    private List<HashMap<CampaignElement, String>> mDataset;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mPost;
        public ImageView mImage;

        public ViewHolder(View v, TextView post, ImageView image) {
            super(v);
            mPost = post;
            mImage = image;
        }
    }

    public CampaignsAdapter(Context context, List<HashMap<CampaignElement, String>> mDataset) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @Override
    public CampaignsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.campaign_view_holder, parent, false);

        TextView post = (TextView)v.findViewById(R.id.post_text);
        ImageView image = (ImageView)v.findViewById(R.id.post_image);

        ViewHolder vh = new ViewHolder(v, post, image);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mPost.setText(mDataset.get(position).get(CampaignElement.POST_TEXT));
        Glide.with(context)
                .load(mDataset.get(position).get(CampaignElement.POST_IMAGE))
                .into(holder.mImage);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
