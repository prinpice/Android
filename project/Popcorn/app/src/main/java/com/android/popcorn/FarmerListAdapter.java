package com.android.popcorn;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class FarmerListAdapter extends RecyclerView.Adapter<FarmerListAdapter.MyViewHolder> {



    RequestOptions options ;
    private Context mContext ;
    private List<FarmerListItem> mData ;



    public FarmerListAdapter(Context mContext, List lst) {


        this.mContext = mContext;
        this.mData = lst;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.icons8_farmer_96)
                .error(R.drawable.icons8_farmer_96);

    }

    @Override
    public FarmerListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.farm_item,parent,false);
        // click listener here
        return new FarmerListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FarmerListAdapter.MyViewHolder holder, final int position) {

        holder.txt_farm_title.setText(mData.get(position).getName()+" 농부의 "+mData.get(position).getCrop());
        //TODO: farm정보에 평균평점 넣기, 리뷰개수 넣기
        holder.txt_star_point.setText("3.5");

        // load image from the internet using Glide
        Glide.with(mContext).load(mData.get(position).getProfile_image()).error(R.drawable.icons8_farmer_96).apply(options).into(holder.img_profile);
        Log.i("msg", mData.get(position).getCrop());
        holder.layout_farm_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("farm", mData.get(position).getFarm());
                bundle.putString("name", mData.get(position).getName());
                bundle.putString("region", mData.get(position).getRegion());
                bundle.putString("crop", mData.get(position).getCrop());
                bundle.putString("enroll_center", mData.get(position).getEnroll_center());
                bundle.putString("farm", mData.get(position).getFarm());
                bundle.putString("crop_image", mData.get(position).getCrop_image());
                bundle.putString("profile_image", mData.get(position).getProfile_image());
                bundle.putInt("sort", mData.get(position).getSort());
                bundle.putInt("id", mData.get(position).getId());
                bundle.putInt("enroll_no", mData.get(position).getEnroll_no());
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                Fragment fragment = new Detail1Fragment();
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).addToBackStack(null).commit();


            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_farm_title, txt_star_point;
        ImageView img_profile;
        LinearLayout layout_farm_item;


        public MyViewHolder(View itemView) {
            super(itemView);
            txt_farm_title = itemView.findViewById(R.id.txt_farm_title);
            txt_star_point = itemView.findViewById(R.id.txt_star_point);
            img_profile = itemView.findViewById(R.id.img_profile);
            layout_farm_item = itemView.findViewById(R.id.layout_farm_item);

        }
    }


}

