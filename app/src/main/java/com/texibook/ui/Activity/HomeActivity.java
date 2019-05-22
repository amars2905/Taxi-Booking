package com.texibook.ui.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.texibook.R;
import com.texibook.constant.Constant;
import com.texibook.ui.fragment.CoupanFragment;
import com.texibook.ui.fragment.FeedBackFragment;
import com.texibook.ui.fragment.HomeFragment;
import com.texibook.ui.fragment.NotificationFragment;
import com.texibook.ui.fragment.ProfileFragment;
import com.texibook.ui.fragment.ReferNEarnFragment;
import com.texibook.ui.fragment.RideHistoryFragment;
import com.texibook.utils.BaseActivity;
import com.texibook.utils.FragmentUtils;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentUtils fragmentUtils;
    public static FragmentManager fragmentManager;
    public static Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentUtils = new FragmentUtils(fragmentManager);
        fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.frameLayout);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            toolbar.setTitle(Constant.HomeFragment);
            fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.frameLayout);
        } else if (id == R.id.profile) {
            toolbar.setTitle(Constant.ProfileFragment);
            fragmentUtils.replaceFragment(new ProfileFragment(), Constant.ProfileFragment, R.id.frameLayout);
        } else if (id == R.id.coupan) {
            toolbar.setTitle(Constant.Coupan);
            fragmentUtils.replaceFragment(new CoupanFragment(), Constant.Coupan, R.id.frameLayout);

        } else if (id == R.id.free_rides) {
            toolbar.setTitle(Constant.ReferNEarnFragment);
            fragmentUtils.replaceFragment(new ReferNEarnFragment(), Constant.ReferNEarnFragment, R.id.frameLayout);
        } else if (id == R.id.setting) {

        } else if (id == R.id.notification) {
            toolbar.setTitle(Constant.NotificationFragment);
            fragmentUtils.replaceFragment(new NotificationFragment(), Constant.NotificationFragment, R.id.frameLayout);

        } else if (id == R.id.get_help) {

        } else if (id == R.id.feedback) {
            toolbar.setTitle(Constant.FeedBackFragment);
            fragmentUtils.replaceFragment(new FeedBackFragment(), Constant.FeedBackFragment, R.id.frameLayout);
            return true;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
