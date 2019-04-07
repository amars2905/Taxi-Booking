package com.texibook.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.texibook.R;
import com.texibook.adapters.FragmentPagerAdapter;
import com.texibook.utils.BaseFragment;

public class NotificationFragment extends BaseFragment {
    private View rootView;
    private FragmentPagerAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        init();
        return rootView;
    }

    private void init() {
        viewPager = (ViewPager) rootView.findViewById(R.id.vpNotification);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tlNotification);
        adapter = new FragmentPagerAdapter(getFragmentManager());
        adapter.addFragment(new RecentNotificationFragment(), "Recent Notification");
        adapter.addFragment(new AllNotificationFragment(), "All Notification");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
