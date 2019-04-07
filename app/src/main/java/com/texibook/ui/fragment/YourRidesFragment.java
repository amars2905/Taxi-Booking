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
import com.texibook.adapters.YourRidesFragmentAdapter;
import com.texibook.utils.BaseFragment;

public class YourRidesFragment extends BaseFragment {
    private View rootView;
    private YourRidesFragmentAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_your_rides, container, false);
        init();
        return rootView;
    }

    private void init() {
        viewPager = (ViewPager) rootView.findViewById(R.id.vpYourRides);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tlYourRides);
        adapter = new YourRidesFragmentAdapter(getFragmentManager());
        adapter.addFragment(new RideHistoryFragment(), "Current Ride");
        adapter.addFragment(new RideHistoryFragment(), "Ride History");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
