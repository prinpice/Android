package com.android.popcorn.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.popcorn.R;
import com.android.popcorn.activities.HomeActivity;
import com.android.popcorn.databinding.FragmentFarmListBinding;
import com.android.popcorn.models.FarmerItem;
import com.android.popcorn.views.adapters.FarmerListAdapter;
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

// Menu1SubFragment
public class FarmListFragment extends Fragment {

    FragmentFarmListBinding farmListBinding;

    int code = 0;
    String search = "";

//    private String URL_JSON = "https://mynongjak-cloned-piie.c9users.io/api/v1/farmer/";
    private String URL_JSON = "http://10.0.2.2:8000/api/v1/farmer/";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<FarmerItem> lstFarmer;

    public static FarmListFragment newInstance(int code, String search) {
        FarmListFragment fragment = new FarmListFragment();
        Bundle bundle = new Bundle();
        Log.i("movie", code + "");
        bundle.putInt("code", code);
        bundle.putString("search", search);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        farmListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_list, container, false);

        if (getArguments() != null){
            code = getArguments().getInt("code");
            search = getArguments().getString("search");
            if (code == 0){
                farmListBinding.txtFarmlistTitle.setText("검색 결과");
                farmListBinding.txtFarmlistSearch.setText("\""+search+"\" ");
                farmListBinding.layoutFarmlistSearch.setVisibility(View.VISIBLE);
            }else{
                switch (code){
                    case 100:
                        farmListBinding.txtFarmlistTitle.setText("곡식·콩");
                        break;

                    case 200:
                        farmListBinding.txtFarmlistTitle.setText("뿌리채소");
                        break;

                    case 300:
                        farmListBinding.txtFarmlistTitle.setText("잎·땅채소");
                        break;

                    case 400:
                        farmListBinding.txtFarmlistTitle.setText("과채소");
                        break;

                    case 500:
                        farmListBinding.txtFarmlistTitle.setText("과일·열매");
                        break;

                    case 600:
                        farmListBinding.txtFarmlistTitle.setText("버섯·기타");
                        break;
                }
            }
        }

        farmListBinding.btnFarmlistSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 여기 페이지에서 결과 수정해서 보여주기
            }
        });

        farmListBinding.btnFarmlistBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farmListBinding.layoutFarmlistSearch.setVisibility(View.GONE);
                ((HomeActivity)getActivity()).replaceFragment(MainFragment.newInstance());
            }
        });

        jsoncall(code, search);

        return farmListBinding.getRoot();
    }//onCreateView


    public void jsoncall(final int num, final String search) {


        ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                lstFarmer = new ArrayList<>();
                for (int i = 0 ; i<response.length();i++) {


                    try {

                        jsonObject = response.getJSONObject(i);
                        FarmerItem farmerItem = new FarmerItem();

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
//                        Log.i("movie", farmerItem.getName());


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

                farmListBinding.layoutFarmlistSearch.setVisibility(View.GONE);
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



    public void setRvadapter (List<FarmerItem> lst) {

        FarmerListAdapter myAdapter = new FarmerListAdapter(getActivity(),lst) ;
        farmListBinding.recyclerviewFarm.setLayoutManager(new LinearLayoutManager(getActivity()));
        farmListBinding.recyclerviewFarm.setAdapter(myAdapter);



    }


}
