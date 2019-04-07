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

public class AllNotificationFragment extends BaseFragment {
    private View rootView;
    private RecyclerView rvAllNotification;
    private NotificationAdapter allnotificationAdapter;
    private List<String> allNotificationList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_all_notification, container, false);
        mContext = getActivity();

        init();
        return rootView;
    }

    private void init() {

        for (int i = 0; i <= 5; i++) {
            allNotificationList.add("name");
        }

        rvAllNotification = rootView.findViewById(R.id.rvAllNotification);
        rvAllNotification.setHasFixedSize(true);
        rvAllNotification.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        allnotificationAdapter = new NotificationAdapter(allNotificationList, mContext);
        rvAllNotification.setAdapter(allnotificationAdapter);
        allnotificationAdapter.notifyDataSetChanged();
    }
}
