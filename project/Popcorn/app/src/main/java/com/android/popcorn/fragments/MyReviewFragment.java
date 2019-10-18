package com.android.popcorn.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.popcorn.R;
import com.android.popcorn.databinding.FragmentMyReviewBinding;

// Menu3Fragment
public class MyReviewFragment extends Fragment {

    FragmentMyReviewBinding myReviewBinding;

    public static MyReviewFragment newInstance() {
        return new MyReviewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myReviewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_review, container, false);

        myReviewBinding.txtMyreviewUsername.setText("박보리 농부의 사과");
        myReviewBinding.layoutMyreview.setVisibility(View.VISIBLE);

        return myReviewBinding.getRoot();
    }


}
