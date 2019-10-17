package com.android.popcorn;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Menu1SubFragment extends Fragment {
    TextView txt_title, txt_farm_title, txt_star_point, txt_count_review, txt_count_comment, txt_detail, txt_search;
    Button btn_search;

    LinearLayout layout_search;


    private String URL_JSON = "https://mynongjak-cloned-piie.c9users.io/api/v1/farmer/";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<FarmerListItem> lstFarmer;
    private RecyclerView recyclerView;



    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성
    public static Menu1SubFragment newInstance() {
        return new Menu1SubFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String search = "";

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_menu1_sub, container, false);
        View view = inflater.inflate(R.layout.fragment_menu1_sub, container, false);

        layout_search = view.findViewById(R.id.layout_search);

        txt_title = view.findViewById(R.id.txt_title);
        txt_search = view.findViewById(R.id.txt_search);

        btn_search = view.findViewById(R.id.btn_search);



        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 여기 페이지에서 결과 수정해서 보여주기
            }
        });


        final Bundle bundle = getArguments();

        Log.i("msg", String.valueOf(bundle.getInt("code")));



        if (bundle.getString("search") != null && bundle.getInt("code")== 0){
            txt_title.setText("검색 결과");
            search = bundle.getString("search");
            txt_search.setText("\""+search+"\" ");
            layout_search.setVisibility(View.VISIBLE);
        }


        recyclerView = view.findViewById(R.id.recyclerview_farm);


        int code = bundle.getInt("code");

        switch(code){
            case 100:
                txt_title.setText("곡식·콩");
                break;

            case 200:
                txt_title.setText("뿌리채소");
                break;

            case 300:
                txt_title.setText("잎·땅채소");
                break;

            case 400:
                txt_title.setText("과채소");
                break;

            case 500:
                txt_title.setText("과일·열매");
                break;

            case 600:
                txt_title.setText("버섯·기타");
                break;

        }

        ImageButton btn_back = view.findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(Menu1Fragment.newInstance());
            }
        });



        jsoncall(code, search);




        return view;


    }


    public void jsoncall(final int num, final String search) {


        ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                lstFarmer = new ArrayList<>();
                for (int i = 0 ; i<response.length();i++) {


                    try {

                        jsonObject = response.getJSONObject(i);
                        FarmerListItem farmerItem = new FarmerListItem();

                        farmerItem.setId(jsonObject.getInt("id"));
                        farmerItem.setEnroll_no(jsonObject.getInt("enroll_no"));
                        farmerItem.setEnroll_center(jsonObject.getString("enroll_center"));
                        farmerItem.setFarm(jsonObject.getString("farm"));
                        farmerItem.setCrop(jsonObject.getString("crop"));
                        farmerItem.setName(jsonObject.getString("name"));
                        farmerItem.setRegion(jsonObject.getString("region"));
                        farmerItem.setSort(jsonObject.getInt("sort"));
                        farmerItem.setCrop_image(jsonObject.getString("crop_image"));
                        farmerItem.setProfile_image(jsonObject.getString("profile_image"));
                        Log.i("movie", farmerItem.getName());


                        if (farmerItem.getSort() == num) {
                            lstFarmer.add(farmerItem);
                        }else if(farmerItem.getCrop() == search){
                            lstFarmer.add(farmerItem);
                        } else if (farmerItem.getName() == search) {
                            lstFarmer.add(farmerItem);
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                setRvadapter(lstFarmer);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(ArrayRequest);
    }



    public void setRvadapter (List<FarmerListItem> lst) {

        FarmerListAdapter myAdapter = new FarmerListAdapter(getActivity(),lst) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myAdapter);



    }
}
