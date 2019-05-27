package com.texibook.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.texibook.R;
import com.texibook.adapters.MainCategoryAdapter;
import com.texibook.adapters.RideHistoryAdapter;
import com.texibook.adapters.SubCategoryAdapter;
import com.texibook.model.main_category_modal.Subcategory;
import com.texibook.model.main_category_modal.TaxiMainCategoryModal;
import com.texibook.model.main_category_modal.Vehicle;
import com.texibook.model.otp_responce.OtpModel;
import com.texibook.retrofit_provider.RetrofitService;
import com.texibook.retrofit_provider.WebResponse;
import com.texibook.ui.Activity.HomeActivity;
import com.texibook.utils.Alerts;
import com.texibook.utils.AppPreference;
import com.texibook.utils.AppProgressDialog;
import com.texibook.utils.BaseFragment;
import com.texibook.utils.ConnectionDirector;
import com.texibook.utils.GpsTracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Response;

public class HomeFragment extends BaseFragment implements View.OnClickListener, OnMapReadyCallback {
    private View rootView;
    private ConnectionDirector cd;
    private List<Vehicle> categoryList = new ArrayList<>();
    private List<Subcategory> subCategoryList = new ArrayList<>();
    private MainCategoryAdapter categoryAdapter;
    private SubCategoryAdapter subCategoryAdapter;
    private BottomSheetBehavior sheetBehavior;
    private TaxiMainCategoryModal mainCategoryModal;
    MapView mMapView;
    private GoogleMap mMap;
    private RecyclerView rvCategory, rvSubcategory;

    private double latitude = 0.0;
    private double longitude = 0.0;
    private Dialog dialog, dialogPaid;
    private Button btnConfirm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_home, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        categoryApi();

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        init();
        return rootView;
    }

    private void init() {
        View layoutBottomSheet = (View) rootView.findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (i) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        ((TextView) rootView.findViewById(R.id.tvCategoryName)).setVisibility(View.VISIBLE);
                        rvSubcategory.setVisibility(View.VISIBLE);
                        rvCategory.setVisibility(View.VISIBLE);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        ((TextView) rootView.findViewById(R.id.tvCategoryName)).setVisibility(View.VISIBLE);
                        rvSubcategory.setVisibility(View.VISIBLE);
                        rvCategory.setVisibility(View.VISIBLE);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        ((TextView) rootView.findViewById(R.id.tvCategoryName)).setVisibility(View.VISIBLE);
                        rvSubcategory.setVisibility(View.VISIBLE);
                        rvCategory.setVisibility(View.VISIBLE);
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        btnConfirm = rootView.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(this);
        rvCategory = rootView.findViewById(R.id.rvCategory);
        rvSubcategory = rootView.findViewById(R.id.rvSubcategory);
        rvCategory.setHasFixedSize(true);

        rvCategory.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new MainCategoryAdapter(categoryList, mContext, this);
        rvCategory.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        rvSubcategory.setHasFixedSize(true);

        rvSubcategory.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        subCategoryAdapter = new SubCategoryAdapter(subCategoryList, mContext, this);
        rvSubcategory.setAdapter(subCategoryAdapter);
        subCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        categoryApi();
    }

    private void firstCategory(List<Subcategory> arrayList) {
        subCategoryList.clear();
        subCategoryList.addAll(arrayList);
        subCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llCategory:
                subCategoryList.clear();
                int pos = (int) view.getTag();
                subCategoryList.addAll(mainCategoryModal.getVehicle().get(pos).getSubcategory());
                subCategoryAdapter.notifyDataSetChanged();
                break;
            case R.id.llSubCategory:
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    rvSubcategory.setVisibility(View.GONE);
                    rvCategory.setVisibility(View.GONE);
                    ((TextView) rootView.findViewById(R.id.tvCategoryName)).setVisibility(View.GONE);
                } else {
                    ((TextView) rootView.findViewById(R.id.tvCategoryName)).setVisibility(View.VISIBLE);
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    rvSubcategory.setVisibility(View.VISIBLE);
                    rvCategory.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.btnConfirm :

                break;
        }
    }

    private void categoryApi() {
        if (cd.isNetWorkAvailable()) {
            RetrofitService.getCategoryData(new Dialog(mContext), retrofitApiClient.mainCategoryData(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    categoryList.clear();
                    mainCategoryModal = (TaxiMainCategoryModal) result.body();

                    if (mainCategoryModal.getStatus() == 1) {
                        categoryList.addAll(mainCategoryModal.getVehicle());
                        if (categoryList.size() > 0) {
                            TextView categoryName = (TextView) rootView.findViewById(R.id.tvCategoryName);
                            categoryName.setVisibility(View.VISIBLE);
                            categoryName.setText(categoryList.get(0).getName() + " : Subcategories");
                            firstCategory(categoryList.get(0).getSubcategory());
                            categoryAdapter.notifyDataSetChanged();
                        } else {
                            Alerts.show(mContext, mainCategoryModal.getMessage());
                        }
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





    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void getLatLong() {
        GpsTracker gpsTracker = new GpsTracker(mContext);
        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();
        refreshLocation();
    }

    private void refreshLocation() {
        AppProgressDialog.show(dialog);
        if (latitude == 0) {
            AppProgressDialog.show(dialog);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getLatLong();
                }
            }, 3000);
        } else {
            //deliveryJobApi();
        }
    }
}
