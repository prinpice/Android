package com.android.payment.views.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.payment.R;
import com.android.payment.databinding.ItemPay1Binding;
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

        ItemPay1Binding itemPay1Binding;

        if (view == null) {
            itemPay1Binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_pay1, viewGroup, false);

            view = itemPay1Binding.getRoot();
            view.setTag(itemPay1Binding);

        }else{
            itemPay1Binding = (ItemPay1Binding) view.getTag();
        }


        MemberVO memberVO = listPay1.get(position);

        itemPay1Binding.txtPay1Name.setText(memberVO.getName());
        itemPay1Binding.txtPay1Sgroup.setText(String.valueOf(memberVO.getSgroup()));
        itemPay1Binding.txtPay1Pay1.setText(String.valueOf(memberVO.getPay1()));
        itemPay1Binding.txtPay1Tempexcept.setText(String.valueOf(memberVO.getTempexcept()));
        return view;
    }


    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(MemberVO memberVO) {
        listPay1.add(memberVO);
    }

}
