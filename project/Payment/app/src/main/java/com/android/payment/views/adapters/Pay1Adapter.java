package com.android.payment.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.payment.R;
import com.android.payment.models.MemberVO;

import java.util.ArrayList;

public class Pay1Adapter extends BaseAdapter {

    private ArrayList<MemberVO> listPay1 = new ArrayList<>();

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listPay1.size();
    }

    // 하나의 Item
    @Override
    public Object getItem(int position) {
        return listPay1.get(position);
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
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pay1, null, false);

            holder = new MemberViewHolder();
            holder.txt_pay1_name = view.findViewById(R.id.txt_pay1_name);
            holder.txt_pay1_sgroup = view.findViewById(R.id.txt_pay1_sgroup);
            holder.txt_pay1_pay1 = view.findViewById(R.id.txt_pay1_pay1);
            holder.txt_pay1_tempexcept = view.findViewById(R.id.txt_pay1_tempexcept);

            view.setTag(holder);

        }else{
            holder = (MemberViewHolder)view.getTag();
        }


        MemberVO memberVO = listPay1.get(position);

        holder.txt_pay1_name.setText(memberVO.getName());
        holder.txt_pay1_sgroup.setText(String.valueOf(memberVO.getSgroup()));
        holder.txt_pay1_pay1.setText(String.valueOf(memberVO.getPay1()));
        holder.txt_pay1_tempexcept.setText(String.valueOf(memberVO.getTempexcept()));
        return view;
    }


    class MemberViewHolder {
        TextView txt_pay1_name;
        TextView txt_pay1_sgroup;
        TextView txt_pay1_pay1;
        TextView txt_pay1_tempexcept;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(MemberVO memberVO) {
        listPay1.add(memberVO);
    }

}
