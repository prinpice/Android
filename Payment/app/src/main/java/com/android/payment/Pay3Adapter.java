package com.android.payment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Pay3Adapter extends BaseAdapter {

    private ArrayList<MemberVO> listPay3 = new ArrayList<>();

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listPay3.size();
    }

    // 하나의 Item
    @Override
    public Object getItem(int position) {
        return listPay3.get(position);
    }

    //Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 실제로 Item이 보여지는 부분
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        MemberViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pay3, null, false);

            holder = new MemberViewHolder();
            holder.txt_pay3_name = view.findViewById(R.id.txt_pay3_name);
            holder.txt_pay3_sgroup = view.findViewById(R.id.txt_pay3_sgroup);
            holder.txt_pay3_pay3 = view.findViewById(R.id.txt_pay3_pay3);
            holder.txt_pay3_tempexcept = view.findViewById(R.id.txt_pay3_tempexcept);

            view.setTag(holder);

        }else{
            holder = (MemberViewHolder)view.getTag();
        }


        MemberVO memberVO = listPay3.get(position);

        holder.txt_pay3_name.setText(memberVO.getName());
        holder.txt_pay3_sgroup.setText(String.valueOf(memberVO.getSgroup()));
        holder.txt_pay3_pay3.setText(String.valueOf(memberVO.getPay3()));
        holder.txt_pay3_tempexcept.setText(String.valueOf(memberVO.getTempexcept()));
        return view;
    }


    class MemberViewHolder {
        TextView txt_pay3_name;
        TextView txt_pay3_sgroup;
        TextView txt_pay3_pay3;
        TextView txt_pay3_tempexcept;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(MemberVO memberVO) {
        listPay3.add(memberVO);
    }

}
