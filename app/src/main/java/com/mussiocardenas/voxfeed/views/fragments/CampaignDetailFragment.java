package com.mussiocardenas.voxfeed.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.mussiocardenas.voxfeed.R;
import com.mussiocardenas.voxfeed.presenters.CampaignElement;
import com.mussiocardenas.voxfeed.presenters.CampaignPresenter;
import com.mussiocardenas.voxfeed.utils.StringsFunctions;
import com.mussiocardenas.voxfeed.views.activities.WebActivity;
import com.mussiocardenas.voxfeed.views.adapters.CampaignStatsAdapter;
import com.mussiocardenas.voxfeed.views.interfaces.CampaignViewInterface;

import java.util.HashMap;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@ link CampaignDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CampaignDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CampaignDetailFragment extends Fragment implements CampaignViewInterface, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "id";

    // TODO: Rename and change types of parameters
    private int mId;
    private HashMap<CampaignElement, String> campaign;
    private CampaignPresenter presenter;

    private Button mGoToSN;
    private ImageView mCoverImage;
    private TextView mLikes, mClicks, mComments, mShares, mAudience;
    private TextView mBrandName, mCampaignName;
    private TextView mEarnings;
    private ImageView mBrandLogo;
    private String mPostLink;
    private View mView;


    // private OnFragmentInteractionListener mListener;

    public CampaignDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id Parameter 1.

     * @return A new instance of fragment CampaignDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CampaignDetailFragment newInstance(int id) {
        CampaignDetailFragment fragment = new CampaignDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_campaign_detail, container, false);;

        final TextView textViewTest = (TextView) mView.findViewById(R.id.textViewTest);
        mGoToSN = (Button) mView.findViewById(R.id.goToSN);
        mLikes = (TextView) mView.findViewById(R.id.stats_likes_value);
        mShares = (TextView) mView.findViewById(R.id.stats_shares_value);
        mComments = (TextView) mView.findViewById(R.id.stats_comments_value);
        mClicks = (TextView) mView.findViewById(R.id.stats_clicks_value);
        mAudience = (TextView) mView.findViewById(R.id.stats_audience_value);
        mCoverImage = ((AppCompatActivity)getActivity()).findViewById(R.id.toolbar_image);
        mBrandLogo = ((AppCompatActivity)getActivity()).findViewById(R.id.brand_logo);
        mBrandName = ((AppCompatActivity)getActivity()).findViewById(R.id.brand_name);
        mCampaignName = ((AppCompatActivity)getActivity()).findViewById(R.id.campaign_name);
        mEarnings = ((AppCompatActivity)getActivity()).findViewById(R.id.earnings);

        // Start presenter
        presenter = new CampaignPresenter(mId, this);


        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            ((mListener.onFragmentInteraction(uri);
        }
    }*/


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        } */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // mListener = null;
    }

    @Override
    public void showCampaign(HashMap<CampaignElement, String> campaign) {

        // Paint Brand Logo
        Glide.with(getActivity())
                .load(campaign.get(CampaignElement.BRAND_LOGO))
                .apply(RequestOptions.circleCropTransform())
                .into(mBrandLogo);

        mBrandName.setText(campaign.get(CampaignElement.BRAND_NAME));
        mCampaignName.setText(campaign.get(CampaignElement.CAMPAIGN_NAME));

        // Paint Earnings
        mEarnings.setText(StringsFunctions.formatCurrency(Float.parseFloat(campaign.get(CampaignElement.EARNINGS)) ) + " USD");

        // Paint Cover Image
        Glide.with(getActivity())
                .load(campaign.get(CampaignElement.CAMPAIGN_COVER_IMAGE))
                .into(mCoverImage);



        // Paint Campaign Stats
        mLikes.setText(StringsFunctions.formatDecimal( Integer.parseInt(campaign.get(CampaignElement.STATS_LIKES)) ));
        mClicks.setText(StringsFunctions.formatDecimal( Integer.parseInt(campaign.get(CampaignElement.STATS_CLICK) )));
        mComments.setText(StringsFunctions.formatDecimal( Integer.parseInt(campaign.get(CampaignElement.STATS_COMMENTS)) ));
        mShares.setText(StringsFunctions.formatDecimal( Integer.parseInt(campaign.get(CampaignElement.STATS_SHARES)) ));
        mAudience.setText(StringsFunctions.formatDecimal( Integer.parseInt(campaign.get(CampaignElement.STATS_AUDIENCE)) ));


        // Paint Social Network
        String sn = campaign.get(CampaignElement.SOCIAL_NETWORK);
        mGoToSN.setText(getActivity().getString(R.string.go_to_sn_text) + " " + sn);
        if(sn.toLowerCase().contains("facebook")){
            mGoToSN.setTextColor(getActivity().getResources().getColor(R.color.FacebookColor));
        }else if(sn.toLowerCase().contains("instagram")){
            mGoToSN.setTextColor(getActivity().getResources().getColor(R.color.InstagramColor));
        }else if(sn.toLowerCase().contains("twitter")){
            mGoToSN.setTextColor(getActivity().getResources().getColor(R.color.TwitterColor));
        }

        mGoToSN.setOnClickListener(this);

        // Set mPostLink
        mPostLink = campaign.get(CampaignElement.POST_LINK);




    }

    @Override
    public void onClick(View view) {

        if(view == mGoToSN){

            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra(CampaignElement.POST_LINK.toString(), mPostLink);
            startActivity(intent);

        }
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    } */
}
