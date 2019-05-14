package com.texibook.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.texibook.R;
import com.texibook.model.main_category_modal.Vehicle;
import com.texibook.ui.Activity.ActivityYourRides;

import java.util.List;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.MyViewHolder> {

    private List<Vehicle> categoryList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public MainCategoryAdapter(List<Vehicle> categoryList, Context mContext, View.OnClickListener onClickListener) {
        this.categoryList = categoryList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.row_main_category, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Vehicle vehicleData = categoryList.get(position);
        holder.llCategory.setTag(position);
        holder.tvCategroName.setTag(position);
        holder.llCategory.setOnClickListener(onClickListener);

        holder.tvCategroName.setText(vehicleData.getName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategroName;
        private LinearLayout llCategory;
        private View viewDevide;

        public MyViewHolder(View view) {
            super(view);

            tvCategroName = view.findViewById(R.id.tvCategroName);
            llCategory = view.findViewById(R.id.llCategory);
            viewDevide = view.findViewById(R.id.viewDevide);
            viewDevide.setVisibility(View.GONE);
        }
    }

}
