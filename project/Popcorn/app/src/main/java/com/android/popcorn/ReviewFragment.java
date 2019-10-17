package com.android.popcorn;

import android.app.Dialog;
import android.content.Context;
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


public class ReviewFragment extends Fragment {
    Button btn_review;
    Dialog dialog;
    RatingBar rating, rating_user_review;
    Button btn_ok, btn_no;
    LinearLayout layout_review1;
    TextView txt_user_review;
    EditText et_review;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_review, container, false);
        View view = inflater.inflate(R.layout.fragment_review, container, false);

        btn_review = view.findViewById(R.id.btn_review);
        rating_user_review = view.findViewById(R.id.rating_user_review);
        layout_review1 = view.findViewById(R.id.layout_review1);
        txt_user_review= view.findViewById(R.id.txt_user_review);

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_review);

                rating = dialog.findViewById(R.id.rating);
                et_review = dialog.findViewById(R.id.et_review);


                rating.setRating( 2.5f );
                rating.setIsIndicator(false);


                btn_ok = dialog.findViewById(R.id.btn_ok);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        txt_user_review.setText(et_review.getText().toString());
                        layout_review1.setVisibility(View.VISIBLE);
                    }
                });


                rating.setStepSize(0.5f);

                rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {

                        rating_user_review.setRating(rating);

                    }
                });


                dialog.show();

            }
        });

        return view;
    }
}
