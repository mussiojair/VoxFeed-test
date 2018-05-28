package com.mussiocardenas.voxfeed.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mussiocardenas.voxfeed.R;
import com.mussiocardenas.voxfeed.presenters.CampaignElement;
import com.mussiocardenas.voxfeed.utils.StringsFunctions;
import com.mussiocardenas.voxfeed.views.interfaces.CampaignsInteraction;

import java.util.HashMap;
import java.util.List;

public class CampaignsAdapter extends RecyclerView.Adapter<CampaignsAdapter.ViewHolder>{

    private List<HashMap<CampaignElement, String>> mDataset;
    private Context context;
    private CampaignsInteraction mCampaignInteraction;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mAccountImage;
        public TextView mAccountUsername;
        public TextView mAccountSocialNetwork;
        public TextView mPostText;
        public ImageView mPostImage;
        public Button mGoToDetail;

        public ViewHolder(View v, ImageView accountImage, TextView accountUsername, TextView accountSocialNetwork, TextView post, ImageView image, Button goToDetail) {
            super(v);
            mAccountImage = accountImage;
            mAccountUsername = accountUsername;
            mAccountSocialNetwork = accountSocialNetwork;
            mPostText = post;
            mPostImage = image;
            mGoToDetail = goToDetail;
        }
    }

    public CampaignsAdapter(Context context, List<HashMap<CampaignElement, String>> mDataset, CampaignsInteraction campaignsInteraction) {
        this.mDataset = mDataset;
        this.context = context;
        this.mCampaignInteraction = campaignsInteraction;
    }

    @Override
    public CampaignsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.campaign_view_holder, parent, false);

        ImageView accountImage = (ImageView)v.findViewById(R.id.account_image);
        TextView accountUsername = (TextView)v.findViewById(R.id.account_username);
        TextView accountSocialNetwork = (TextView)v.findViewById(R.id.account_social_network);
        TextView post = (TextView)v.findViewById(R.id.post_text);
        ImageView image = (ImageView)v.findViewById(R.id.post_image);
        Button goToDetail = (Button)v.findViewById(R.id.go_to_detail);

        ViewHolder vh = new ViewHolder(v, accountImage, accountUsername, accountSocialNetwork, post, image, goToDetail);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // post text
        holder.mPostText.setText(mDataset.get(position).get(CampaignElement.POST_TEXT));

        // post image
        Glide.with(context)
                .load(mDataset.get(position).get(CampaignElement.POST_IMAGE))
                .into(holder.mPostImage);

        // account image
        Glide.with(context)
                .load(mDataset.get(position).get(CampaignElement.USER_PROFILE_IMAGE))
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mAccountImage);

        // account username
        holder.mAccountUsername.setText(mDataset.get(position).get(CampaignElement.USER_USERNAME));

        // account social network
        String socialNetwork = mDataset.get(position).get(CampaignElement.SOCIAL_NETWORK);
        holder.mAccountSocialNetwork.setText(mDataset.get(position).get(CampaignElement.SOCIAL_NETWORK));
        if(socialNetwork.toLowerCase().contains("facebook")){
            holder.mAccountSocialNetwork.setTextColor(context.getResources().getColor(R.color.FacebookColor));
        }else if(socialNetwork.toLowerCase().contains("twitter")){
            holder.mAccountSocialNetwork.setTextColor(context.getResources().getColor(R.color.TwitterColor));
        }else if(socialNetwork.toLowerCase().contains("instagram")){
            holder.mAccountSocialNetwork.setTextColor(context.getResources().getColor(R.color.InstagramColor));
        }

        // Go To Detail (text = Date)
        holder.mGoToDetail.setText(StringsFunctions.capitalizeFirstCharacter(mDataset.get(position).get(CampaignElement.DATE)));
        holder.mGoToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCampaignInteraction.goToDetail(Integer.parseInt( mDataset.get(position).get(CampaignElement.ID)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
