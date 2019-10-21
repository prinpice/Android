package com.android.payment.views.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.payment.R;
import com.android.payment.databinding.ItemPay3Binding;
import com.android.payment.models.MemberVO;

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
        ItemPay3Binding itemPay3Binding;

        if (view == null) {
            itemPay3Binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_pay3, viewGroup, false);
            view = itemPay3Binding.getRoot();
            view.setTag(itemPay3Binding);

        }else{
            itemPay3Binding = (ItemPay3Binding) view.getTag();
        }


        MemberVO memberVO = listPay3.get(position);

        itemPay3Binding.txtPay3Name.setText(memberVO.getName());
        itemPay3Binding.txtPay3Sgroup.setText(String.valueOf(memberVO.getSgroup()));
        itemPay3Binding.txtPay3Pay3.setText(String.valueOf(memberVO.getPay3()));
        itemPay3Binding.txtPay3Tempexcept.setText(String.valueOf(memberVO.getTempexcept()));
        return view;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(MemberVO memberVO) {
        listPay3.add(memberVO);
    }

}
