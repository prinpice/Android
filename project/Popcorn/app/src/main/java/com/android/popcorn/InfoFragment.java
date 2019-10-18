package com.android.popcorn;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.popcorn.databinding.FragmentInfoBinding;


public class InfoFragment extends Fragment {

    FragmentInfoBinding infoBinding;

//    TextView txt_farm, txt_crop, txt_enroll_no, txt_enroll_center, txt_region;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_info, container, false);
        infoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);
//        txt_farm = view.findViewById(R.id.txt_farm);
//        txt_crop = view.findViewById(R.id.txt_crop);
//        txt_enroll_no = view.findViewById(R.id.txt_enroll_no);
//        txt_enroll_center = view.findViewById(R.id.txt_enroll_center);
//        txt_region = view.findViewById(R.id.txt_region);
        Bundle bundle = getArguments();

        infoBinding.txtFarm.setText(bundle.getString("farm"));
        infoBinding.txtCrop.setText(bundle.getString("crop"));
        infoBinding.txtEnrollNo.setText(bundle.getInt("enroll_no")+"");
        infoBinding.txtEnrollCenter.setText(bundle.getString("enroll_center"));
        infoBinding.txtRegion.setText(bundle.getString("region"));




        return infoBinding.getRoot();

    }

}
