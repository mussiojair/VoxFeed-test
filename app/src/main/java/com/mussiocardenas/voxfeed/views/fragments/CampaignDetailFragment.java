package com.mussiocardenas.voxfeed.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mussiocardenas.voxfeed.R;
import com.mussiocardenas.voxfeed.presenters.CampaignElement;
import com.mussiocardenas.voxfeed.presenters.CampaignPresenter;
import com.mussiocardenas.voxfeed.views.adapters.CampaignStats;
import com.mussiocardenas.voxfeed.views.adapters.CampaignStatsAdapter;
import com.mussiocardenas.voxfeed.views.interfaces.CampaignViewInterface;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@ link CampaignDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CampaignDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CampaignDetailFragment extends Fragment implements CampaignViewInterface{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "id";

    // TODO: Rename and change types of parameters
    private int mId;
    private HashMap<CampaignElement, String> campaign;
    private CampaignPresenter presenter;

    private Button mGoToSN;
    private ListView mCampaignStats;
    private ImageView mCoverImage;
    private TextView mLikes, mClicks, mComments, mShares, mAudience;
    private TextView mBrandName, mCampaignName;
    private TextView mEarnings;
    private ImageView mBrandLogo;
    private CampaignStatsAdapter mAdapter;

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
        View v =  inflater.inflate(R.layout.fragment_campaign_detail, container, false);;

        final TextView textViewTest = (TextView) v.findViewById(R.id.textViewTest);
        mGoToSN = (Button) v.findViewById(R.id.goToSN);
        mCampaignStats = (ListView) v.findViewById(R.id.campaignStats);
        mLikes = (TextView) v.findViewById(R.id.stats_likes_value);
        mShares = (TextView) v.findViewById(R.id.stats_shares_value);
        mComments = (TextView) v.findViewById(R.id.stats_comments_value);
        mClicks = (TextView) v.findViewById(R.id.stats_clicks_value);
        mAudience = (TextView) v.findViewById(R.id.stats_audience_value);
        mCoverImage = ((AppCompatActivity)getActivity()).findViewById(R.id.toolbar_image);
        mBrandLogo = ((AppCompatActivity)getActivity()).findViewById(R.id.brand_logo);
        mBrandName = ((AppCompatActivity)getActivity()).findViewById(R.id.brand_name);
        mCampaignName = ((AppCompatActivity)getActivity()).findViewById(R.id.campaign_name);
        mEarnings = ((AppCompatActivity)getActivity()).findViewById(R.id.earnings);

        // Start presenter
        presenter = new CampaignPresenter(mId, this);

        return v;
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
        mEarnings.setText("$ " + campaign.get(CampaignElement.ID) + " USD");

        // Paint Cover Image
        Glide.with(getActivity()).load(campaign.get(CampaignElement.CAMPAIGN_COVER_IMAGE)).into(mCoverImage);

        // Paint Campaign Stats
        mLikes.setText(campaign.get(CampaignElement.STATS_LIKES));
        mClicks.setText(campaign.get(CampaignElement.STATS_CLICK));
        mComments.setText(campaign.get(CampaignElement.STATS_COMMENTS));
        mShares.setText(campaign.get(CampaignElement.STATS_SHARES));
        mAudience.setText(campaign.get(CampaignElement.STATS_AUDIENCE));


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
