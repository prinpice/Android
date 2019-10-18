package com.android.popcorn;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.popcorn.databinding.DialogReviewBinding;
import com.android.popcorn.databinding.FragmentReviewBinding;


public class ReviewFragment extends Fragment {

    FragmentReviewBinding reviewBinding;

//    Button btn_review;
    Dialog dialog;
//    RatingBar rating, rating_user_review;
//    Button btn_ok, btn_no;
//    LinearLayout layout_review1;
//    TextView txt_user_review;
//    EditText et_review;

    private DialogReviewBinding dialogReviewBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_review, container, false);
        reviewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_review, container, false);

//        btn_review = view.findViewById(R.id.btn_review);
//        rating_user_review = view.findViewById(R.id.rating_user_review);
//        layout_review1 = view.findViewById(R.id.layout_review1);
//        txt_user_review= view.findViewById(R.id.txt_user_review);

        reviewBinding.btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(getActivity());
                dialogReviewBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.dialog_review, null, false);

                dialog.setContentView(dialogReviewBinding.getRoot());

//                rating = dialog.findViewById(R.id.rating);
//                et_review = dialog.findViewById(R.id.et_review);


                dialogReviewBinding.rating.setRating( 2.5f );
                dialogReviewBinding.rating.setIsIndicator(false);


//                btn_ok = dialog.findViewById(R.id.btn_ok);
                dialogReviewBinding.btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        reviewBinding.txtUserReview.setText(dialogReviewBinding.etReview.getText().toString());
                        reviewBinding.layoutReview1.setVisibility(View.VISIBLE);
                    }
                });


                dialogReviewBinding.rating.setStepSize(0.5f);

                dialogReviewBinding.rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {

                        reviewBinding.ratingUserReview.setRating(rating);

                    }
                });


                dialog.show();

            }
        });

        return reviewBinding.getRoot();
    }
}
