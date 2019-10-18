package com.android.popcorn.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.popcorn.R;
import com.android.popcorn.activities.HomeActivity;
import com.android.popcorn.databinding.FragmentFarmerInfoBinding;

// Detail1Fragment
public class FarmerInfoFragment extends Fragment {

    FragmentFarmerInfoBinding farmerInfoBinding;

    public static FarmerInfoFragment newInstance() {
        return new FarmerInfoFragment();
    }

    int num = 0;
    Bundle bundle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        farmerInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_farmer_info, container, false);

        bundle = getArguments();

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        
        fragmentTransaction.add(R.id.sub_layout, new FarmSubInfoFragment()).commit();

        farmerInfoBinding.btnFarminfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bundle.getInt("code") == 50) {
                    ((HomeActivity) getActivity()).replaceFragment(FarmListFragment.newInstance(100, ""));
                }else if (bundle.getInt("code") != 0){
                    Fragment fragment = new FarmListFragment();
                    fragment.setArguments(bundle);
                    ((HomeActivity)getActivity()).replaceFragment(fragment);
                }else{
                    ((HomeActivity)getActivity()).replaceFragment(MainFragment.newInstance());
                }
            }
        });

        String str = bundle.getString("name") + " 농부의 " + bundle.getString("crop");
        farmerInfoBinding.txtFarminfoFarmTitle.setText(str);
        farmerInfoBinding.farminfoRating.setRating((float)3.5);
        farmerInfoBinding.txtFarminfoRating.setText("3");
        farmerInfoBinding.txtFarminfoReview.setText("최근리뷰 0+");
        farmerInfoBinding.txtFarminfoComment.setText("최근농부님댓글 0+");


        farmerInfoBinding.btnFarminfoInfo.setOnClickListener( click );
        farmerInfoBinding.btnFarminfoReview.setOnClickListener( click );



        farmerInfoBinding.btnFarminfoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //전화걸기 권한에 대한 수락여부 확인
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {//GRANTED : 수락 DENIED : 거절
                    ((HomeActivity)getActivity()).setPermission();
                    //onCreate()실행 안하고 그냥 종료
                } else {
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:010-111-2222"));
                    startActivity(i);
                }
            }
        });

        farmerInfoBinding.btnFarminfoTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putBoolean("take", true);
            }
        });
        
        
        
        return farmerInfoBinding.getRoot();
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.btn_farminfo_info:
                    farmerInfoBinding.btnFarminfoInfo.setBackgroundColor(getActivity().getApplicationContext().getResources().getColor(R.color.transparent));
                    farmerInfoBinding.btnFarminfoReview.setBackgroundColor(getActivity().getApplicationContext().getResources().getColor(R.color.graybackground));
                    Fragment fragment = new FarmSubInfoFragment();
                    fragment.setArguments(bundle);
                    setChildFragment(fragment);
                    break;


                case R.id.btn_farminfo_review:
                    farmerInfoBinding.btnFarminfoInfo.setBackgroundColor(getActivity().getApplicationContext().getResources().getColor(R.color.graybackground));
                    farmerInfoBinding.btnFarminfoReview.setBackgroundColor(getActivity().getApplicationContext().getResources().getColor(R.color.transparent));
                    setChildFragment(new FarmSubReviewFragment());
                    break;



            }
        }
    };//OnClickListener

    private void setChildFragment(Fragment child) {
        FragmentTransaction childFt = getChildFragmentManager().beginTransaction();

        if (!child.isAdded()) {
            childFt.replace(R.id.sub_layout, child);
            childFt.addToBackStack(null);
            childFt.commit();
        }
    }


}
