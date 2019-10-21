package com.android.payment.views.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.payment.R;
import com.android.payment.databinding.ItemManageBinding;
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

        ItemManageBinding itemManageBinding;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            itemManageBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_manage, viewGroup, false);
            view = itemManageBinding.getRoot();
            view.setTag(itemManageBinding);

        }else{
            itemManageBinding = (ItemManageBinding)view.getTag();
        }


        MemberVO memberVO = listMember.get(position);

        itemManageBinding.txtManageName.setText(memberVO.getName());
        itemManageBinding.txtManageSgroup.setText(String.valueOf(memberVO.getSgroup()));
        return view;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(MemberVO memberVO) {
        listMember.add(memberVO);
    }

}
