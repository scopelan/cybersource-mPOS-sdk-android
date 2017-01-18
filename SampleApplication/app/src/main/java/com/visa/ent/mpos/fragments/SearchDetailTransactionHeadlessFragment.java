package com.visa.ent.mpos.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.visa.ent.mpos.BaseActivity;
import com.visa.ent.mpos.R;
import com.visa.ent.mpos.receivers.TransactionResultReceiver;
import com.visa.ent.mpos.services.TransactionIntentService;
import com.visainc.mpos.sdk.datamodel.VMposTransactionSearchQuery;

/*
 * Copyright © 2016 CyberSource. All rights reserved.
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDetailTransactionHeadlessFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_RESULT_RECEIVER = "result_receiver";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String SEARCH_TYPE="searchType";
    public static final String MAIN="main";
    public static final String DETAIL="detail";
    public static final String NAVIGATION = "navigation";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String searchType;

    private OnFragmentInteractionListener mListener;
    private TransactionResultReceiver mReceiver;
    VMposTransactionSearchQuery searchQuery;

    public SearchDetailTransactionHeadlessFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchTransactionHeadlessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchDetailTransactionHeadlessFragment newInstance(String param1, String param2) {
        SearchDetailTransactionHeadlessFragment fragment = new SearchDetailTransactionHeadlessFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mReceiver = getArguments().getParcelable(ARG_RESULT_RECEIVER);
            searchType = getArguments().getString(SEARCH_TYPE);
        }
        setRetainInstance(true);
        startServiceWithReceiver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void startServiceWithReceiver() {
        Intent msgIntent = new Intent(getActivity(), TransactionIntentService.class);
        msgIntent.putExtra(TransactionIntentService.SERVICE_ACTION_TAG, BaseActivity.SEARCH_ACTION_DETAIL);
        msgIntent.putExtra(TransactionIntentService.SERVICE_RESULT_RECEIVER, mReceiver);
        getActivity().startService(msgIntent);
    }

}