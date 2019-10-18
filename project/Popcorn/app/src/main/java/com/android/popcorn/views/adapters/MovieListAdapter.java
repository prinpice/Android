package com.android.popcorn.views.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.popcorn.R;
import com.android.popcorn.fragments.MovieInfoFragment;
import com.android.popcorn.models.MovieItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    RequestOptions options ;
    private Context mContext ;
    private List<MovieItem> mData ;


    public MovieListAdapter(Context mContext, List lst) {


        this.mContext = mContext;
        this.mData = lst;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

    }

    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.movie_list_item,parent,false);
        // click listener here
        return new MovieListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txt_movie_title.setText(mData.get(position).getTitle_ko());
        holder.txt_movie_year.setText(mData.get(position).getOpen_year());
        holder.txt_movie_nation.setText(mData.get(position).getNation());

        // load image from the internet using Glide
        Glide.with(mContext).load(mData.get(position).getPoster_url()).apply(options).into(holder.img_movie_poster);
        holder.layout_movie_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("title", mData.get(position).getTitle_ko());
                bundle.putString("year", mData.get(position).getOpen_year());
                bundle.putString("nation", mData.get(position).getNation());
                bundle.putString("genre", mData.get(position).getGenre());
                bundle.putString("info", mData.get(position).getDescription());
                bundle.putString("director", mData.get(position).getDirector());
                bundle.putString("poster_url", mData.get(position).getPoster_url());
                bundle.putString("image_url", mData.get(position).getImage_url());
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                Fragment fragment = new MovieInfoFragment();
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.layout_movie, fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_movie_title, txt_movie_year, txt_movie_nation;
        ImageView img_movie_poster;
        LinearLayout layout_movie_item;


        public MyViewHolder(View itemView) {
            super(itemView);
//            txt_movie_title = itemView.findViewById(R.id.txt_movie_title);
//            txt_movie_year = itemView.findViewById(R.id.txt_movie_year);
//            txt_movie_nation = itemView.findViewById(R.id.txt_movie_nation);
//            img_movie_poster = itemView.findViewById(R.id.img_movie_poster);
//            layout_movie_item = itemView.findViewById(R.id.layout_movie_item);
        }
    }

}
