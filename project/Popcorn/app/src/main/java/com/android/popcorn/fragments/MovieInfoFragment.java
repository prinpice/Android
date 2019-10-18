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
import com.android.popcorn.activities.MovieActivity;
import com.android.popcorn.databinding.DialogRatingBinding;
import com.android.popcorn.databinding.FragmentMovieInfoBinding;
import com.bumptech.glide.Glide;


public class MovieInfoFragment extends Fragment {

    FragmentMovieInfoBinding movieInfoBinding;

    private DialogRatingBinding dialogRatingBinding;

    public static MovieInfoFragment newInstance() {
        return new MovieInfoFragment();
    }

    Dialog dialog;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        movieInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_info, container, false);

        Bundle bundle = getArguments();

        Glide.with(getActivity()).load(bundle.getString("poster_url")).into(movieInfoBinding.imgMovieinfoPoster);
        Glide.with(getActivity()).load(bundle.getString("image_url")).into(movieInfoBinding.imgMovieinfoThumbnailImage);
        movieInfoBinding.txtMovieinfoSimpleYear.setText(bundle.getString("year"));
        movieInfoBinding.txtMovieinfoSimpleNation.setText(bundle.getString("nation"));
        movieInfoBinding.txtMovieinfoSimpleGenre.setText(bundle.getString("genre"));
        movieInfoBinding.txtMovieinfoTitle.setText(bundle.getString("title"));
        movieInfoBinding.txtMovieinfoSummeryInfo.setText(bundle.getString("info"));
        movieInfoBinding.txtMovieinfoDirector.setText(bundle.getString("director"));
        movieInfoBinding.txtMovieinfoGenre.setText(bundle.getString("genre"));
        movieInfoBinding.txtMovieinfoYear.setText(bundle.getString("year"));

//        txt_comment_content = view.findViewById(R.id.txt_comment_content);
        movieInfoBinding.btnMovieinfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MovieActivity)getActivity()).replaceMovieFragment(MovieListFragment.newInstance());
            }
        });

        movieInfoBinding.layoutMovieinfoStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(getActivity());
                dialogRatingBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.dialog_rating, null, false);
                dialog.setContentView(dialogRatingBinding.getRoot());

//                rating = dialog.findViewById(R.id.rating);


//                et_comment = dialog.findViewById(R.id.et_comment);

                dialogRatingBinding.rating.setRating( 2.5f );
                dialogRatingBinding.rating.setIsIndicator(false);

//                btn_ok = dialog.findViewById(R.id.btn_ok);
                dialogRatingBinding.btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        movieInfoBinding.txtMovieinfoCommentContent.setText(dialogRatingBinding.etComment.getText().toString());
                        movieInfoBinding.layoutMovieinfoComment.setVisibility(View.VISIBLE);
                        movieInfoBinding.txtMovieinfoEvaluation.setText("평가완료");
                        movieInfoBinding.layoutMovieinfoStar.setEnabled(false);
                    }
                });

                dialogRatingBinding.rating.setStepSize(0.5f);

                dialogRatingBinding.rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        float st = 5f / dialogRatingBinding.rating.getNumStars();

                        String str = String.format( "%.1f", st * v );//소숫점 한자리
                        movieInfoBinding.txtMovieinfoAverage.setText( str );

                    }
                });


                dialog.show();
            }
        });

        return movieInfoBinding.getRoot();
    }//onCreateVIew


}
