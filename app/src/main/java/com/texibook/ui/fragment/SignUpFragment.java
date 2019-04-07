package com.texibook.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.texibook.ui.Activity.HomeActivity;
import com.texibook.ui.Activity.MainActivity;
import com.texibook.R;
import com.texibook.constant.Constant;
import com.texibook.retrofit_provider.RetrofitService;
import com.texibook.utils.BaseFragment;
import com.texibook.utils.ConnectionDirector;

import static com.texibook.ui.Activity.LoginActivity.loginfragmentManager;


public class SignUpFragment extends BaseFragment implements View.OnClickListener {
    private View rootview;
    private Button btn_signUp;
    private EditText fullname, emailAddress, password, cPassword, cPhone;
    private String strName, strMobile, strEmailAddress, strPassword, strConfirmPassword;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_signup_layout, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
        return rootview;
    }

    private void init() {
        btn_signUp = rootview.findViewById(R.id.btn_signUp);
        fullname = rootview.findViewById(R.id.et_fullname);
        emailAddress = rootview.findViewById(R.id.et_email_address);
        password = rootview.findViewById(R.id.et_password);
        cPassword = rootview.findViewById(R.id.et_cpassword);
        cPhone = rootview.findViewById(R.id.et_mobile);
        btn_signUp.setOnClickListener(this);
        ((TextView) rootview.findViewById(R.id.tv_Login)).setOnClickListener(this);
    }

    private void startFragment(String tag, Fragment fragment) {
        loginfragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.login_frame, fragment, tag).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signUp:
                // signUpApi();
                Intent intent = new Intent(mContext, HomeActivity.class);
                startActivity(intent);
                activity.finish();
                break;
            case R.id.tv_Login:
                startFragment(Constant.LoginFragment, new LoginFragment());
                break;
        }
    }

    /*private void signUpApi() {
        if (cd.isNetWorkAvailable()) {
            strName = fullname.getText().toString();
            strEmailAddress = emailAddress.getText().toString();
            strPassword = password.getText().toString();
            strConfirmPassword = cPassword.getText().toString();
            strMobile = cPhone.getText().toString();
            if (strName.isEmpty()) {
                fullname.setError("Please enter fullname !!!");
            } else if (strMobile.isEmpty()) {
                cPhone.setError("Please enter Mobile !!!");
            } else if (strEmailAddress.isEmpty()) {
                emailAddress.setError("Please enter email address !!!");
            } else if (!EmailChecker.isValid(strEmailAddress)) {
                emailAddress.setError("Please enter valid email address !!!");
            } else if (strPassword.isEmpty()) {
                password.setError("Please enter password !!!");
            } else if (strConfirmPassword.isEmpty()) {
                password.setError("please reter password!!!");
            } else if (!strPassword.equalsIgnoreCase(strConfirmPassword)) {
                password.setError("Password not match !!!");
            } else {
                RetrofitService.getSignData(new Dialog(mContext), retrofitApiClient.signUp(strName, strEmailAddress , strMobile, strPassword), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        SignUpModel responseBody = (SignUpModel) result.body();

                        if (!responseBody.getError())
                        {
                            Alerts.show(mContext , responseBody.getMessage());


                            ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("Mobile_Number", strMobile);
                            forgotPasswordFragment.setArguments(bundle);
                            startFragment(Constant.ForgotPasswordFragment, forgotPasswordFragment);

                        }else {
                            Alerts.show(mContext , responseBody.getMessage());
                        }

                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(mContext, error);
                    }
                });
            }
        }
    }*/
}
