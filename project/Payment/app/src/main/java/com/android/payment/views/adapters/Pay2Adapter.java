package com.android.payment.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.payment.R;
import com.android.payment.models.MemberVO;

import java.util.ArrayList;

public class Pay2Adapter extends BaseAdapter {

    private ArrayList<MemberVO> listPay2 = new ArrayList<>();

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listPay2.size();
    }

    // 하나의 Item
    @Override
    public Object getItem(int position) {
        return listPay2.get(position);
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
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pay2, null, false);

            holder = new MemberViewHolder();
            holder.txt_pay2_name = view.findViewById(R.id.txt_pay2_name);
            holder.txt_pay2_sgroup = view.findViewById(R.id.txt_pay2_sgroup);
            holder.txt_pay2_pay2 = view.findViewById(R.id.txt_pay2_pay2);
            holder.txt_pay2_tempexcept = view.findViewById(R.id.txt_pay2_tempexcept);

            view.setTag(holder);

        }else{
            holder = (MemberViewHolder)view.getTag();
        }


        MemberVO memberVO = listPay2.get(position);

        holder.txt_pay2_name.setText(memberVO.getName());
        holder.txt_pay2_sgroup.setText(String.valueOf(memberVO.getSgroup()));
        holder.txt_pay2_pay2.setText(String.valueOf(memberVO.getPay2()));
        holder.txt_pay2_tempexcept.setText(String.valueOf(memberVO.getTempexcept()));
        return view;
    }


    class MemberViewHolder {
        TextView txt_pay2_name;
        TextView txt_pay2_sgroup;
        TextView txt_pay2_pay2;
        TextView txt_pay2_tempexcept;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(MemberVO memberVO) {
        listPay2.add(memberVO);
    }

}
