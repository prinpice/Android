package com.android.popcorn.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.popcorn.R;
import com.android.popcorn.activities.HomeActivity;
import com.android.popcorn.activities.LoginActivity;
import com.android.popcorn.activities.MovieActivity;
import com.android.popcorn.databinding.FragmentMainBinding;
import com.bumptech.glide.Glide;

// Menu1Fragment
public class MainFragment extends Fragment {

    FragmentMainBinding mainBinding;
    Intent intent;
    String userId = "";

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    public static MainFragment newInstance(String userID) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", userID);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        if (getArguments() != null){
            userId = getArguments().getString("userId");
            mainBinding.btnMainLogin.setText("안녕하세요 " + userId + "님");
        }
        Glide.with(getActivity()).load("https://movie-phinf.pstatic.net/20180205_9/1517796368980sxuwQ_JPEG/movie_image.jpg?type=m665_443_2").into(mainBinding.imgMainMovieList);

        int[] movie_images = {R.drawable.farmer_pic2, R.drawable.straw, R.drawable.couple};
        for (int i = 0; i < movie_images.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(movie_images[i]);
            mainBinding.vfBanner.addView(imageView);

        }

        Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);


        mainBinding.vfBanner.setInAnimation(in);
        mainBinding.vfBanner.setOutAnimation(out);
        mainBinding.vfBanner.setFlipInterval(3000);
        mainBinding.vfBanner.setAutoStart(true);

        mainBinding.btnMainLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        mainBinding.btnMainSearch.setOnClickListener( click );

        mainBinding.btnMainGrain.setOnClickListener( click );
        mainBinding.btnMainRoot.setOnClickListener( click );
        mainBinding.btnMainLeafVegi.setOnClickListener( click );
        mainBinding.btnMainFruitVegi.setOnClickListener( click );
        mainBinding.btnMainFruit.setOnClickListener( click );
        mainBinding.btnMainMushroom.setOnClickListener( click );


        mainBinding.imgMainMovieList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), MovieActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        
        return mainBinding.getRoot();
    }//onCreateView

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int code = 0;
            String search = "";
            switch (view.getId()){

                case R.id.btn_main_grain:
                    code = 100;
                    break;

                case R.id.btn_main_root:
                    code = 200;
                    break;

                case R.id.btn_main_leaf_vegi:
                    code = 300;
                    break;

                case R.id.btn_main_fruit_vegi:
                    code = 400;
                    break;

                case R.id.btn_main_fruit:
                    code = 500;
                    break;

                case R.id.btn_main_mushroom:
                    code = 600;
                    break;

                case R.id.btn_main_search:
                    search = mainBinding.etMainSearch.getText().toString();
                    break;

            }



            // getActivity()로 HomeActivitiy의 replaceFragment를 불러옴
            ((HomeActivity)getActivity()).replaceFragment(FarmListFragment.newInstance(code, search));
        }
    };//click

}
