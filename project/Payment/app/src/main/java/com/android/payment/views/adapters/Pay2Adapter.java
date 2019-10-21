package com.android.payment.views.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.payment.R;
import com.android.payment.databinding.ItemPay2Binding;
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

        ItemPay2Binding itemPay2Binding;

        if (view == null) {
            itemPay2Binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_pay2, viewGroup, false);
            view = itemPay2Binding.getRoot();
            view.setTag(itemPay2Binding);

        }else{
            itemPay2Binding = (ItemPay2Binding) view.getTag();
        }


        MemberVO memberVO = listPay2.get(position);

        itemPay2Binding.txtPay2Name.setText(memberVO.getName());
        itemPay2Binding.txtPay2Sgroup.setText(String.valueOf(memberVO.getSgroup()));
        itemPay2Binding.txtPay2Pay2.setText(String.valueOf(memberVO.getPay2()));
        itemPay2Binding.txtPay2Tempexcept.setText(String.valueOf(memberVO.getTempexcept()));
        return view;
    }


    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(MemberVO memberVO) {
        listPay2.add(memberVO);
    }

}
