package com.android.popcorn;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.popcorn.databinding.FragmentMenu2Binding;


public class Menu2Fragment extends Fragment {

    FragmentMenu2Binding menu2Binding;

//    LinearLayout linlay1;
//    TextView txt_farm_title, txt_star_point,  txt_count_review, txt_count_comment;
    Bundle bundle = new Bundle();

    public static Menu2Fragment newInstance() {
        return new Menu2Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_menu2, container, false);
        View view = inflater.inflate(R.layout.fragment_menu2, container, false);

//        linlay1 = view.findViewById(R.id.linlay1);

//        txt_farm_title = view.findViewById(R.id.txt_farm_title);
//        txt_star_point = view.findViewById(R.id.txt_star_point);
//        txt_count_review = view.findViewById(R.id.txt_count_review);
//        txt_count_comment = view.findViewById(R.id.txt_count_comment);



        menu2Binding.linlay1.setVisibility(View.VISIBLE);

        menu2Binding.txtFarmTitle.setText("박보리 농부의 사과");
        menu2Binding.txtStarPoint.setText("3.5");
        menu2Binding.txtCountReview.setText("3+");
        menu2Binding.txtCountComment.setText("2+");





//        LinearLayout linlay1 = view.findViewById(R.id.linlay1);
        menu2Binding.linlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Detail1Fragment();
                bundle.putInt("code", 50);
                fragment.setArguments(bundle);
                ((MainActivity)getActivity()).replaceFragment(fragment);
            }
        });


        return view;
    }
}
