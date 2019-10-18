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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.popcorn.databinding.DialogRatingBinding;
import com.android.popcorn.databinding.FragmentMovieDetailBinding;
import com.bumptech.glide.Glide;


public class MovieDetailFragment extends Fragment {

    FragmentMovieDetailBinding movieDetailBinding;

    private DialogRatingBinding dialogRatingBinding;

    public static MovieDetailFragment newInstance() {
        return new MovieDetailFragment();
    }

//    ImageButton btn_back;
//    LinearLayout layout_star;
    Dialog dialog;
//    Button btn_ok;
//    RatingBar rating;
//    EditText et_comment;
//    TextView txt_average, txt_eval, txt_country, txt_year, txt_comment_content, year, genre, nation, txt_tit, txt_info, txt_director, txt_genre;
//    ImageView img_poster, img_image;
//    RelativeLayout relay_comment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
        movieDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false);
        Bundle bundle = getArguments();


//        txt_average = view.findViewById(R.id.txt_average);
//        txt_eval = view.findViewById(R.id.txt_eval);
//        btn_back = view.findViewById(R.id.btn_back);
//        layout_star = view.findViewById(R.id.layout_star);
//        relay_comment = view.findViewById(R.id.relay_comment);
//        img_poster = view.findViewById(R.id.img_poster);
//        img_image = view.findViewById(R.id.img_image);
//        genre = view.findViewById(R.id.genre);
//        nation = view.findViewById(R.id.nation);
//        year = view.findViewById(R.id.year);
//        txt_tit = view.findViewById(R.id.txt_tit);
//        txt_info = view.findViewById(R.id.txt_info);
//        txt_director = view.findViewById(R.id.txt_director);
//        txt_genre = view.findViewById(R.id.txt_genre);
//        txt_country = view.findViewById(R.id.txt_country);
//        txt_year = view.findViewById(R.id.txt_year);
        Glide.with(getActivity()).load(bundle.getString("poster_url")).into(movieDetailBinding.imgPoster);
        Glide.with(getActivity()).load(bundle.getString("image_url")).into(movieDetailBinding.imgImage);
        movieDetailBinding.year.setText(bundle.getString("year"));
        movieDetailBinding.nation.setText(bundle.getString("nation"));
        movieDetailBinding.genre.setText(bundle.getString("genre"));
        movieDetailBinding.txtTit.setText(bundle.getString("title"));
        movieDetailBinding.txtInfo.setText(bundle.getString("info"));
        movieDetailBinding.txtDirector.setText(bundle.getString("director"));
        movieDetailBinding.txtGenre.setText(bundle.getString("genre"));
        movieDetailBinding.txtYear.setText(bundle.getString("year"));

//        txt_comment_content = view.findViewById(R.id.txt_comment_content);
        movieDetailBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MovieActivity)getActivity()).replaceMovieFragment(MovieListFragment.newInstance());
            }
        });

        movieDetailBinding.layoutStar.setOnClickListener(new View.OnClickListener() {
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
                        movieDetailBinding.txtCommentContent.setText(dialogRatingBinding.etComment.getText().toString());
                        movieDetailBinding.relayComment.setVisibility(View.VISIBLE);
                        movieDetailBinding.txtEval.setText("평가완료");
                        movieDetailBinding.layoutStar.setEnabled(false);
                    }
                });

                dialogRatingBinding.rating.setStepSize(0.5f);

                dialogRatingBinding.rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        float st = 5f / dialogRatingBinding.rating.getNumStars();

                        String str = String.format( "%.1f", st * v );//소숫점 한자리
                        movieDetailBinding.txtAverage.setText( str );

                    }
                });


                dialog.show();
            }
        });

        return movieDetailBinding.getRoot();

    }
}
