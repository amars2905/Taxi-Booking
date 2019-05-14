package com.texibook.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.texibook.R;
import com.texibook.model.main_category_modal.Subcategory;
import com.texibook.model.main_category_modal.Vehicle;

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
        View itemView = li.inflate(R.layout.row_main_category, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Subcategory subcategory = subcategoryList.get(position);
        holder.tvCategroName.setText(subcategory.getName());
    }

    @Override
    public int getItemCount() {
        return subcategoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategroName;
        private LinearLayout llCategory;

        public MyViewHolder(View view) {
            super(view);

            tvCategroName = view.findViewById(R.id.tvCategroName);
        }
    }

}
