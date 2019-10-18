package com.android.popcorn;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.android.popcorn.databinding.FragmentMenu1Binding;
import com.bumptech.glide.Glide;


public class Menu1Fragment extends Fragment {

    FragmentMenu1Binding menu1Binding;

//    Button btn_account, btn_search;
//    ImageButton btn_grain, btn_root, btn_leaf_vegi, btn_fruit_vegi, btn_fruit, btn_mushroom;;
    Intent intent;
//    ImageView img_movie_list;
//    EditText et_search;

//    private ViewFlipper vf_banner;

    // 이미지 url연결

    Bundle bundle = new Bundle();


    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성
    public static Menu1Fragment newInstance() {
        return new Menu1Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        menu1Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu1, container, false);
//        btn_grain = view.findViewById(R.id.btn_grain);
//        btn_root = view.findViewById(R.id.btn_root);
//        btn_leaf_vegi = view.findViewById(R.id.btn_leaf_vegi);
//        btn_fruit_vegi = view.findViewById(R.id.btn_fruit_vegi);
//        btn_fruit = view.findViewById(R.id.btn_fruit);
//        btn_mushroom = view.findViewById(R.id.btn_mushroom);


//        btn_account = view.findViewById(R.id.btn_account);
//        btn_search = view.findViewById(R.id.btn_search);
//        img_movie_list = view.findViewById(R.id.img_movie_list);
//        et_search = view.findViewById(R.id.et_search);

        if (bundle.getString("id") != null) {
            menu1Binding.btnAccount.setText("안녕하세요 " + bundle.getString("id") + "님");
        }


        Glide.with(getActivity()).load("https://movie-phinf.pstatic.net/20180205_9/1517796368980sxuwQ_JPEG/movie_image.jpg?type=m665_443_2").into(menu1Binding.imgMovieList);



//        img_good_farmer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                bundle.putInt("fcode", 12);
////                bundle.putInt("code", 0);
////                bundle.putString("search", null);
//                Fragment fragment = new Detail1Fragment();
//                fragment.setArguments(bundle);
//                ((MainActivity)getActivity()).replaceFragment(fragment);
//            }
//        });


//        vf_banner = view.findViewById(R.id.vf_banner);

        int[] movie_images = {R.drawable.farmer_pic2, R.drawable.straw, R.drawable.couple};
        for (int i = 0; i < movie_images.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(movie_images[i]);
            menu1Binding.vfBanner.addView(imageView);

        }

        Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);


        menu1Binding.vfBanner.setInAnimation(in);
        menu1Binding.vfBanner.setOutAnimation(out);
        menu1Binding.vfBanner.setFlipInterval(3000);
        menu1Binding.vfBanner.setAutoStart(true);

        menu1Binding.btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        menu1Binding.btnSearch.setOnClickListener( click );

//        btn_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putString("search", et_search.getText().toString());
//                Fragment fragment = new Menu1SubFragment();
//                fragment.setArguments(bundle);
//                ((MainActivity)getActivity()).replaceFragment(fragment);
//            }
//        });

        menu1Binding.btnGrain.setOnClickListener( click );
        menu1Binding.btnRoot.setOnClickListener( click );
        menu1Binding.btnLeafVegi.setOnClickListener( click );
        menu1Binding.btnFruitVegi.setOnClickListener( click );
        menu1Binding.btnFruit.setOnClickListener( click );
        menu1Binding.btnMushroom.setOnClickListener( click );


        menu1Binding.imgMovieList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), MovieActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        return menu1Binding.getRoot();

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            switch (view.getId()){

                case R.id.btn_grain:
                    bundle.putInt("code", 100);
                    break;

                case R.id.btn_root:
                    bundle.putInt("code", 200);
                    break;

                case R.id.btn_leaf_vegi:
                    bundle.putInt("code", 300);
                    break;

                case R.id.btn_fruit_vegi:
                    bundle.putInt("code", 400);
                    break;

                case R.id.btn_fruit:
                    bundle.putInt("code", 500);
                    break;

                case R.id.btn_mushroom:
                    bundle.putInt("code", 600);
                    break;

                case R.id.btn_search:
                    bundle.putString("search", menu1Binding.etSearch.getText().toString());
                    break;

            }

            Fragment fragment = new Menu1SubFragment();
            fragment.setArguments(bundle);
            // getActivity()로 MainActivitiy의 replaceFragment를 불러옴
            ((MainActivity)getActivity()).replaceFragment(fragment);
        }
    };//click
}
