package com.texibook.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.texibook.R;
import com.texibook.utils.BaseFragment;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private LinearLayout btn_truck, btn_bus_rentls, btn_out_ride_booking, btn_daily_raids, btn_rentls;

    private String[] dailyrideList = new String[] {"Auto", "van", "Hatchback", "Sedan", "SUV"};
    private String[] outstationList = new String[] {"Hatchback", "Sedan", "SUV", "Van", "Tempo Traveller", "Luxury Class"};
    private String[] reantalsList = new String[] {"Hatchback", "Sedan", "SUV", "Van", "Tempo Traveller", "Luxury Class"};
    private String[] busrentalList = new String[] {"A/C Passenger Bus", "A/C Tour Bus", "non A/C Passenger Bus", "Non A/C Tour Bus"};
    private String[] truckList = new String[] {"Fully Truck", "Paitial Truck", "Loading Auto"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_home, container, false);
        init();
        return rootView;
    }

    private void init() {
        btn_daily_raids = rootView.findViewById(R.id.btn_daily_raids);
        btn_out_ride_booking = rootView.findViewById(R.id.btn_out_ride_booking);
        btn_bus_rentls = rootView.findViewById(R.id.btn_bus_rentls);
        btn_truck = rootView.findViewById(R.id.btn_truck);
        btn_rentls = rootView.findViewById(R.id.btn_rentls);

        btn_truck.setOnClickListener(this);
        btn_bus_rentls.setOnClickListener(this);
        btn_out_ride_booking.setOnClickListener(this);
        btn_daily_raids.setOnClickListener(this);
        btn_rentls.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_bus_rentls :
                showDialog(getActivity(), "Bus", busrentalList);
                break;
            case R.id.btn_out_ride_booking :
                showDialog(getActivity(), "OutStation Booking", outstationList);

                break;
            case R.id.btn_daily_raids :
                showDialog(getActivity(), "Daily Ride", dailyrideList);

                break;

            case R.id.btn_truck :
                showDialog(getActivity(), "Truck", truckList);

                break;

            case R.id.btn_rentls :
                showDialog(getActivity(), "Rentals", reantalsList);

                break;
        }
    }

    public void showDialog(Activity activity, String msg, String[] list){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialogbox_categary);

        TextView text = (TextView) dialog.findViewById(R.id.tv_category_name);
        text.setText(msg);

        ListView category_list = (ListView) dialog.findViewById(R.id.category_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.custom_list, R.id.tv_item_name, list);

        category_list.setAdapter(adapter);

        category_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dialog.dismiss();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dialog.show();

    }

}
