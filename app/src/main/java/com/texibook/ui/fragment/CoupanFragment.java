package com.texibook.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.texibook.R;
import com.texibook.adapters.CoupanAdapter;
import com.texibook.adapters.FragmentPagerAdapter;
import com.texibook.model.coupan_modal.CoupanMainModal;
import com.texibook.model.coupan_modal.Coupon;
import com.texibook.retrofit_provider.RetrofitService;
import com.texibook.retrofit_provider.WebResponse;
import com.texibook.utils.Alerts;
import com.texibook.utils.BaseFragment;
import com.texibook.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class CoupanFragment extends BaseFragment {
    private CoupanAdapter coupanAdapter;
    private View rootView;
    private List<Coupon> coupanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_coupan, container, false);
        mContext = getContext();
        mContext = getActivity();
        cd = new com.texibook.utils.ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        coupanApi();
    }

    private void init() {
        RecyclerView rvCoupan = rootView.findViewById(R.id.rvCoupan);
        rvCoupan.setHasFixedSize(true);
        rvCoupan.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        coupanAdapter = new CoupanAdapter(coupanList, mContext);
        rvCoupan.setAdapter(coupanAdapter);
        coupanAdapter.notifyDataSetChanged();
    }

    private void coupanApi() {
        if (cd.isNetWorkAvailable()) {
            RetrofitService.getCoupanData(new Dialog(mContext), retrofitApiClient.coupanData(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    coupanList.clear();
                    CoupanMainModal mainModal = (CoupanMainModal) result.body();

                    if (mainModal.getStatus() == 1) {
                        if (coupanList != null) {
                            coupanList.addAll(mainModal.getCoupon());
                            coupanAdapter.notifyDataSetChanged();
                        } else {
                            Alerts.show(mContext, mainModal.getMessage());
                        }
                    } else {
                        Alerts.show(mContext, mainModal.getMessage());
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        } else {
            cd.show(mContext);
        }
    }

}


   /*  viewPager = (ViewPager) rootView.findViewById(R.id.vpYourRides);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tlYourRides);
        adapter = new FragmentPagerAdapter(getFragmentManager());
        adapter.addFragment(new RideHistoryFragment(), "Current Ride");
        adapter.addFragment(new RideHistoryFragment(), "Ride History");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);*/