package com.texibook.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.texibook.R;
import com.texibook.constant.Constant;
import com.texibook.model.main_category_modal.Subcategory;
import com.texibook.model.main_category_modal.Vehicle;
import com.texibook.utils.Alerts;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {

    private List<Subcategory> subcategoryList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public SubCategoryAdapter(List<Subcategory> subcategoryList, Context mContext, View.OnClickListener onClickListener) {
        this.subcategoryList = subcategoryList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.row_main_subcategory, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Subcategory subcategory = subcategoryList.get(position);
        if (subcategory.getImage() != null) {
            Glide.with(mContext).load(Constant.IMAGE_URL + subcategory.getImage()).into(holder.ivCategoryImage);
        } else {
            Alerts.show(mContext, "There is no image");
        }
        holder.tvCategroName.setText(subcategory.getName());
        holder.llSubCategory.setOnClickListener(onClickListener);
        holder.llSubCategory.setTag(position);
    }

    @Override
    public int getItemCount() {
        return subcategoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategroName;
        private LinearLayout llSubCategory;
        private ImageView ivCategoryImage;

        public MyViewHolder(View view) {
            super(view);

           tvCategroName = view.findViewById(R.id.tvCategroName1);
            tvCategroName = view.findViewById(R.id.tvCategroName);
            ivCategoryImage = view.findViewById(R.id.ivCategoryImage);
            llSubCategory = view.findViewById(R.id.llSubCategory);
        }
    }

}
