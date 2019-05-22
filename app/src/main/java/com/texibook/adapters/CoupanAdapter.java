package com.texibook.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.texibook.R;
import com.texibook.model.coupan_modal.Coupon;
import com.texibook.ui.Activity.ActivityYourRides;

import java.util.List;

public class CoupanAdapter extends RecyclerView.Adapter<CoupanAdapter.MyViewHolder> {

    private List<Coupon> couponList;
    private Context mContext;

    public CoupanAdapter(List<Coupon> couponList, Context mContext) {
        this.couponList = couponList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.row_coupan_list, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Coupon coupon = couponList.get(position);
        holder.tvCoupanCode.setText(coupon.getCode());
        holder.tvCoupanDescription.setText(coupon.getDescription());

    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCoupanCode, tvCoupanDescription;

        public MyViewHolder(View view) {
            super(view);
            tvCoupanCode = view.findViewById(R.id.tvCoupanCode);
            tvCoupanDescription = view.findViewById(R.id.tvCoupanDescription);
        }
    }
}