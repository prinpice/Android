package com.android.popcorn;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.popcorn.databinding.ActivityMovieBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MovieActivity extends AppCompatActivity {

    ActivityMovieBinding movieBinding;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MovieListFragment movieListFragment = new MovieListFragment();

    Context context;
    String receive;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);


        try {
//            Log.i("movie", new Task().execute().get());
            receive = new Task().execute().get();
//            Log.i("movie", receive);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        bundle = new Bundle();
        bundle.putString("js", receive);


//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        movieListFragment.setArguments(bundle);
//        transaction.replace(R.id.movie_frame_layout, movieListFragment).commitAllowingStateLoss();

        setDefaultMovieFragment();
    }

    // Fragment 초기 화면 고정
    public void setDefaultMovieFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout, movieListFragment);
        transaction.commit();
    }

    public void replaceMovieFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.movie_frame_layout, fragment).commit();
    }


    public class Task extends AsyncTask<String, Void, String> {

        private String str, receiveMsg;

        @Override
        protected String doInBackground(String... strings) {

            URL url;

            try {
//                url = new URL("https://mynongjak-cloned-piie.c9users.io/api/v1/movies/");
                url = new URL("http://10.0.2.2:8000/api/v1/movies/");

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                connection.setRequestMethod("GET");

                if (connection.getResponseCode() == connection.HTTP_OK){
                    InputStreamReader tmp = new InputStreamReader(connection.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while((str = reader.readLine()) != null){
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.i("receiveMsg", receiveMsg);
//                    Log.i("movie", receiveMsg);
//                    movielistjsonParser(receiveMsg);
//                    Log.i("receiveMsg", ""+movielistjsonParser(receiveMsg));
//                    Log.i("receiveMsg", String.valueOf(photoinfoList));
//                    receive = buffer.toString();


                    reader.close();

                    return receiveMsg;
                }else{
                    Log.i("result", connection.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



            return receiveMsg;

        }

    }
}
