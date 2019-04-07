package com.texibook.ui.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.texibook.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btn_daily_rides, btn_outstation_booking, btn_rentals, btn_bus, btn_truck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        btn_daily_rides = findViewById(R.id.btn_daily_rides);
        btn_bus = findViewById(R.id.btn_bus);
        btn_outstation_booking = findViewById(R.id.btn_outstation_booking);
        btn_rentals = findViewById(R.id.btn_rentals);
        btn_truck = findViewById(R.id.btn_truck);

        btn_daily_rides.setOnClickListener(this);
        btn_bus.setOnClickListener(this);
        btn_truck.setOnClickListener(this);
        btn_rentals.setOnClickListener(this);
        btn_outstation_booking.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_bus :
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_daily_rides :
                Intent intent1 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_outstation_booking :
                Intent intent2 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent2);
                break;

            case R.id.btn_rentals :
                Intent intent3 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent3);
                break;

            case R.id.btn_truck :
                Intent intent4 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent4);
                break;

        }
    }
}
