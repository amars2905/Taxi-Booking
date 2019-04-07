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
import com.texibook.adapters.NotificationAdapter;
import com.texibook.adapters.RideHistoryAdapter;
import com.texibook.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class RecentNotificationFragment extends BaseFragment {
    private View rootView;
    private RecyclerView rvRecentNotification;
    private NotificationAdapter recentNotificationAdapter;
    private List<String> recentNotificationList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recent_notification, container, false);
        mContext = getActivity();

        init();
        return rootView;
    }

    private void init() {

        for (int i = 0; i <= 2; i++) {
            recentNotificationList.add("name");
        }

        rvRecentNotification = rootView.findViewById(R.id.rvRecentNotification);
        rvRecentNotification.setHasFixedSize(true);
        rvRecentNotification.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        recentNotificationAdapter = new NotificationAdapter(recentNotificationList, mContext);
        rvRecentNotification.setAdapter(recentNotificationAdapter);
        recentNotificationAdapter.notifyDataSetChanged();
    }
}
