package com.mussiocardenas.voxfeed.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mussiocardenas.voxfeed.R;
import com.mussiocardenas.voxfeed.presenters.CampaignElement;
import com.mussiocardenas.voxfeed.presenters.CampaignsPresenter;
import com.mussiocardenas.voxfeed.views.activities.CampaignsActivity;
import com.mussiocardenas.voxfeed.views.adapters.CampaignsAdapter;
import com.mussiocardenas.voxfeed.views.interfaces.CampaignsInteraction;
import com.mussiocardenas.voxfeed.views.interfaces.CampaignsViewInterface;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CampaignsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CampaignsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CampaignsFragment extends Fragment implements CampaignsViewInterface, CampaignsInteraction {


    private OnFragmentInteractionListener mListener;

    private CampaignsPresenter presenter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public CampaignsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CampaignsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CampaignsFragment newInstance() {
        CampaignsFragment fragment = new CampaignsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v  = inflater.inflate(R.layout.fragment_campaigns, container, false);

        presenter = new CampaignsPresenter(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.campaignRecycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);



        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void showCampaigns(final List<HashMap<CampaignElement, String>> campaigns) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter = new CampaignsAdapter(getActivity(), campaigns, CampaignsFragment.this);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

    }

    @Override
    public void goToDetail(int campaignId) {
        mListener.onFragmentInteraction(campaignId);
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int campaignId);
    }

}
