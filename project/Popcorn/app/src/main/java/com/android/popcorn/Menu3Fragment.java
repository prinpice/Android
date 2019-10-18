package com.android.popcorn;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.popcorn.databinding.FragmentMenu3Binding;


public class Menu3Fragment extends Fragment {

    FragmentMenu3Binding menu3Binding;

//    LinearLayout layout_review1;
//    TextView txt_username;

    public static Menu3Fragment newInstance() {
        return new Menu3Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        menu3Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu3, container, false);


//        layout_review1 = view.findViewById(R.id.layout_review1);
//        txt_username = view.findViewById(R.id.txt_username);




        menu3Binding.txtUsername.setText("박보리 농부의 사과");
        menu3Binding.layoutReview1.setVisibility(View.VISIBLE);



        return menu3Binding.getRoot();
    }
}
