package com.android.payment.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.payment.R;
import com.android.payment.models.MemberVO;

import java.util.ArrayList;

public class ManageAdapter extends BaseAdapter {

    private ArrayList<MemberVO> listMember = new ArrayList<>();

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listMember.size();
    }

    // 하나의 Item
    @Override
    public Object getItem(int position) {
        return position;
    }

    //Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 실제로 item이 보여지는 부분
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        MemberViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_manage, null, false);

            holder = new MemberViewHolder();
            holder.txt_manage_name = view.findViewById(R.id.txt_manage_name);
            holder.txt_manage_sgroup = view.findViewById(R.id.txt_manage_sgroup);

            view.setTag(holder);

        }else{
            holder = (MemberViewHolder)view.getTag();
        }


        MemberVO memberVO = listMember.get(position);

        holder.txt_manage_name.setText(memberVO.getName());
        holder.txt_manage_sgroup.setText(String.valueOf(memberVO.getSgroup()));
        return view;
    }

    class MemberViewHolder {
        TextView txt_manage_name;
        TextView txt_manage_sgroup;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(MemberVO memberVO) {
        listMember.add(memberVO);
    }

}
