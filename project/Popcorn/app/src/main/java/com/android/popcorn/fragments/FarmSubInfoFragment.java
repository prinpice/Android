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
import com.android.popcorn.databinding.FragmentFarmSubInfoBinding;

// InfoFRagment
public class FarmSubInfoFragment extends Fragment {

    FragmentFarmSubInfoBinding farmSubInfoBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        farmSubInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_sub_info, container, false);

        Bundle bundle = getArguments();

        farmSubInfoBinding.txtFarmsubinfoFarmTitle.setText(bundle.getString("farm"));
        farmSubInfoBinding.txtFarmsubinfoCropMain.setText(bundle.getString("crop"));
        farmSubInfoBinding.txtFarmsubinfoEnrollNo.setText(bundle.getInt("enroll_no")+"");
        farmSubInfoBinding.txtFarmsubinfoEnrollCenter.setText(bundle.getString("enroll_center"));
        farmSubInfoBinding.txtFarmsubinfoRegion.setText(bundle.getString("region"));



        return farmSubInfoBinding.getRoot();
    }

}
