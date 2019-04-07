package com.texibook.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.texibook.R;
import com.texibook.adapters.RideHistoryAdapter;
import com.texibook.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class RideHistoryFragment extends BaseFragment {
    private View rootView;
    private RecyclerView rvRideHistory;
    private RideHistoryAdapter rideHistoryAdapter;
    private List<String> rideHistoryList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ride_history, container, false);
        mContext = getActivity();

        init();
        return rootView;
    }

    private void init() {

        for (int i = 0; i <= 5; i++) {
            rideHistoryList.add("name");
        }

        rvRideHistory = rootView.findViewById(R.id.rvRideHistory);
        rvRideHistory.setHasFixedSize(true);
        rvRideHistory.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        rideHistoryAdapter = new RideHistoryAdapter(rideHistoryList, mContext);
        rvRideHistory.setAdapter(rideHistoryAdapter);
        rideHistoryAdapter.notifyDataSetChanged();
    }
}
