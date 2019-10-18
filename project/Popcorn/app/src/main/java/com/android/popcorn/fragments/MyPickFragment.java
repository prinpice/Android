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
import com.android.popcorn.activities.HomeActivity;
import com.android.popcorn.databinding.FragmentMyPickBinding;

// Menu2Fragment
public class MyPickFragment extends Fragment {

    FragmentMyPickBinding myPickBinding;

    Bundle bundle = new Bundle();

    public static MyPickFragment newInstance() {
        return new MyPickFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myPickBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_pick, container, false);

        myPickBinding.txtMypickFarmTitle.setText("박보리 농부의 사과");
        myPickBinding.txtMypickStarPoint.setText(("3.5"));
        myPickBinding.txtMypickCountReview.setText("3+");
        myPickBinding.txtMypickCountComment.setText("2+");

        myPickBinding.layoutMypickFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FarmerInfoFragment();
                bundle.putInt("code", 50);
                fragment.setArguments(bundle);
                ((HomeActivity)getActivity()).replaceFragment(fragment);
            }
        });

        return myPickBinding.getRoot();
    }

}
