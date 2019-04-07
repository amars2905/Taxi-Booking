package com.texibook.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.texibook.R;
import com.texibook.ui.Activity.ActivityYourRides;

import java.util.List;

public class RideHistoryAdapter extends RecyclerView.Adapter<RideHistoryAdapter.MyViewHolder> {

    private List<String> rideHistoryList;
    private Context mContext;

    public RideHistoryAdapter(List<String> rideHistoryList, Context mContext) {
        this.rideHistoryList = rideHistoryList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.row_ride_history, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return rideHistoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cvRideDetail;

        public MyViewHolder(View view) {
            super(view);
            cvRideDetail = view.findViewById(R.id.cvRideDetail);
            cvRideDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, ActivityYourRides.class));
                }
            });
        }
    }

}
