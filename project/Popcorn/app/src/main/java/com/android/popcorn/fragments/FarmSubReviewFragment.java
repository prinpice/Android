package com.android.popcorn.fragments;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.android.popcorn.R;
import com.android.popcorn.databinding.DialogReviewBinding;
import com.android.popcorn.databinding.FragmentFarmSubReviewBinding;

//ReviewFragment
public class FarmSubReviewFragment extends Fragment {

    FragmentFarmSubReviewBinding farmSubReviewBinding;

    Dialog dialog;

    private DialogReviewBinding dialogReviewBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        farmSubReviewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_sub_review, container, false);

        farmSubReviewBinding.btnFarmsubreviewReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(getActivity());
                dialogReviewBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.dialog_review, null, false);

                dialog.setContentView(dialogReviewBinding.getRoot());

                dialogReviewBinding.rating.setRating( 2.5f );
                dialogReviewBinding.rating.setIsIndicator(false);


//                btn_ok = dialog.findViewById(R.id.btn_ok);
                dialogReviewBinding.btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        farmSubReviewBinding.txtFarmsubreviewUserReview.setText(dialogReviewBinding.etReview.getText().toString());
                        farmSubReviewBinding.layoutFarmsubreview.setVisibility(View.VISIBLE);
                    }
                });


                dialogReviewBinding.rating.setStepSize(0.5f);

                dialogReviewBinding.rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {

                        farmSubReviewBinding.ratingFarmsubreviewUserReview.setRating(rating);

                    }
                });


                dialog.show();

            }
        });

        return farmSubReviewBinding.getRoot();
    }

}
