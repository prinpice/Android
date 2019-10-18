package com.android.popcorn;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.android.popcorn.databinding.FragmentMovieListBinding;
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


public class MovieListFragment extends Fragment {
//    ImageButton img_btn_back;

    FragmentMovieListBinding movieListBinding;

//    private ViewFlipper vf_banner;

    private String URL_JSON = "https://mynongjak-cloned-piie.c9users.io/api/v1/movies/";
    private JsonArrayRequest ArrayRequest ;
    private RequestQueue requestQueue ;
    private List<MovieListItem> lstMovie = new ArrayList<>();
//    private RecyclerView recyclerView ;






    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_movie_list, container, false);

        movieListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false);


//        vf_banner = view.findViewById(R.id.vf_banner);

        int[] movie_images = {R.drawable.movie1, R.drawable.movie2, R.drawable.movie3, R.drawable.movie4};
        for (int i = 0; i < movie_images.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(movie_images[i]);
            movieListBinding.vfBanner.addView(imageView);

        }

        Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);


        movieListBinding.vfBanner.setInAnimation(in);
        movieListBinding.vfBanner.setOutAnimation(out);
        movieListBinding.vfBanner.setFlipInterval(3000);
        movieListBinding.vfBanner.setAutoStart(true);


        // 뒤로가기
//        img_btn_back = view.findViewById(R.id.img_btn_back);
        movieListBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });

//        recyclerView = view.findViewById(R.id.recyclerview);
        jsoncall();




        return movieListBinding.getRoot();

    }

    public void jsoncall() {


        ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;


                for (int i = 0 ; i<response.length();i++) {


                    try {

                        jsonObject = response.getJSONObject(i);
                        MovieListItem movieItem = new MovieListItem();

                        movieItem.setId(jsonObject.getInt("id"));
                        movieItem.setTitle_ko(jsonObject.getString("title_ko"));
                        movieItem.setTitle_en(jsonObject.getString("title_en"));
                        movieItem.setOpen_year(jsonObject.getString("open_year"));
                        movieItem.setGenre(jsonObject.getString("genre"));
                        movieItem.setNation(jsonObject.getString("nation"));
                        movieItem.setDirector(jsonObject.getString("director"));
                        movieItem.setDescription(jsonObject.getString("description"));
                        movieItem.setPoster_url(jsonObject.getString("poster_url"));
                        movieItem.setImage_url(jsonObject.getString("image_url"));
                        Log.i("movie", movieItem.getTitle_ko());

                        lstMovie.add(movieItem);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                setRvadapter(lstMovie);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(ArrayRequest);
    }



    public void setRvadapter (List<MovieListItem> lst) {

        MovieListAdapter myAdapter = new MovieListAdapter(getActivity(),lst) ;
        movieListBinding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieListBinding.recyclerview.setAdapter(myAdapter);




    }
}
