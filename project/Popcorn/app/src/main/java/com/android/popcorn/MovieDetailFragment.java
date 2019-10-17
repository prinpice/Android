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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class MovieDetailFragment extends Fragment {
    public static MovieDetailFragment newInstance() {
        return new MovieDetailFragment();
    }

    ImageButton btn_back;
    LinearLayout layout_star;
    Dialog dialog;
    Button btn_ok;
    RatingBar rating;
    EditText et_comment;
    TextView txt_average, txt_eval, txt_country, txt_year, txt_comment_content, year, genre, nation, txt_tit, txt_info, txt_director, txt_genre;
    ImageView img_poster, img_image;
    RelativeLayout relay_comment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        Bundle bundle = getArguments();


        txt_average = view.findViewById(R.id.txt_average);
        txt_eval = view.findViewById(R.id.txt_eval);
        btn_back = view.findViewById(R.id.btn_back);
        layout_star = view.findViewById(R.id.layout_star);
        relay_comment = view.findViewById(R.id.relay_comment);
        img_poster = view.findViewById(R.id.img_poster);
        img_image = view.findViewById(R.id.img_image);
        genre = view.findViewById(R.id.genre);
        nation = view.findViewById(R.id.nation);
        year = view.findViewById(R.id.year);
        txt_tit = view.findViewById(R.id.txt_tit);
        txt_info = view.findViewById(R.id.txt_info);
        txt_director = view.findViewById(R.id.txt_director);
        txt_genre = view.findViewById(R.id.txt_genre);
        txt_country = view.findViewById(R.id.txt_country);
        txt_year = view.findViewById(R.id.txt_year);
        Glide.with(getActivity()).load(bundle.getString("poster_url")).into(img_poster);
        Glide.with(getActivity()).load(bundle.getString("image_url")).into(img_image);
        year.setText(bundle.getString("year"));
        nation.setText(bundle.getString("nation"));
        genre.setText(bundle.getString("genre"));
        txt_tit.setText(bundle.getString("title"));
        txt_info.setText(bundle.getString("info"));
        txt_director.setText(bundle.getString("director"));
        txt_genre.setText(bundle.getString("genre"));
        txt_year.setText(bundle.getString("year"));

        txt_comment_content = view.findViewById(R.id.txt_comment_content);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MovieActivity)getActivity()).replaceMovieFragment(MovieListFragment.newInstance());
            }
        });

        layout_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_rating);

                rating = dialog.findViewById(R.id.rating);

                et_comment = dialog.findViewById(R.id.et_comment);

                rating.setRating( 2.5f );
                rating.setIsIndicator(false);

                btn_ok = dialog.findViewById(R.id.btn_ok);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        txt_comment_content.setText(et_comment.getText().toString());
                        relay_comment.setVisibility(View.VISIBLE);
                        txt_eval.setText("평가완료");
                        layout_star.setEnabled(false);
                    }
                });

                rating.setStepSize(0.5f);

                rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        float st = 5f / rating.getNumStars();

                        String str = String.format( "%.1f", st * v );//소숫점 한자리
                        txt_average.setText( str );

                    }
                });


                dialog.show();
            }
        });

        return view;

    }
}
