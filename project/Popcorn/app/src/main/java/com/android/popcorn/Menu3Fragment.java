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


public class Menu3Fragment extends Fragment {
    LinearLayout layout_review1;
    TextView txt_username;

    public static Menu3Fragment newInstance() {
        return new Menu3Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu3, container, false);


        layout_review1 = view.findViewById(R.id.layout_review1);
        txt_username = view.findViewById(R.id.txt_username);




        txt_username.setText("박보리 농부의 사과");
        layout_review1.setVisibility(View.VISIBLE);



        return view;
    }
}
