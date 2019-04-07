package com.texibook.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.texibook.R;
import com.texibook.ui.Activity.ProfileActivity;
import com.texibook.utils.BaseFragment;

public class ProfileFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private Button btnEditProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        mContext = getActivity();
        init();
        return rootView;
    }

    private void init() {
        btnEditProfile = rootView.findViewById(R.id.btnEditProfile);
        btnEditProfile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditProfile:
                startActivity(new Intent(mContext, ProfileActivity.class));
                break;
        }
    }
}
