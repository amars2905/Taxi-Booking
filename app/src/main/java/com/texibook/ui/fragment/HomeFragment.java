package com.texibook.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.texibook.constant.Constant;
import com.texibook.model.User;
import com.texibook.model.main_category_modal.Subcategory;
import com.texibook.model.main_category_modal.TaxiMainCategoryModal;
import com.texibook.model.main_category_modal.Vehicle;
import com.texibook.model.otp_responce.OtpModel;
import com.texibook.retrofit_provider.RetrofitService;
import com.texibook.retrofit_provider.WebResponse;
import com.texibook.ui.Activity.HomeActivity;
import com.texibook.utils.Alerts;
import com.texibook.utils.AppPreference;
import com.texibook.utils.BaseFragment;
import com.texibook.utils.ConnectionDirector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private ConnectionDirector cd;
    private List<Vehicle> categoryList = new ArrayList<>();
    private List<Subcategory> subCategoryList = new ArrayList<>();
    private MainCategoryAdapter categoryAdapter;
    private SubCategoryAdapter subCategoryAdapter;
    LinearLayout layoutBottomSheet;
    private BottomSheetBehavior sheetBehavior;
    private TaxiMainCategoryModal mainCategoryModal;
    MapView mMapView;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_home, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        categoryApi();
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(-34, 151);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        init();
        return rootView;
    }

    private void init() {
       /* View layoutBottomSheet = (View) rootView.findViewById(R.id.nestedScrollView);
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (i) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });*/

        RecyclerView rvCategory = rootView.findViewById(R.id.rvCategory);
        RecyclerView rvSubcategory = rootView.findViewById(R.id.rvSubcategory);
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
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
