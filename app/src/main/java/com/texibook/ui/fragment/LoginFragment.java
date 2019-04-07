package com.texibook.ui.fragment;

import android.app.Dialog;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.texibook.R;
import com.texibook.constant.Constant;
import com.texibook.retrofit_provider.RetrofitService;
import com.texibook.utils.BaseFragment;
import com.texibook.utils.ConnectionDirector;


import retrofit2.Response;

import static com.texibook.ui.Activity.LoginActivity.loginfragmentManager;


public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private View rootview;
    private String strEmail, strPassword;
    private EditText et_login_email, et_login_password;
    private String strEmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private TextView tv_signUp, tv_forgot_password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_login_layout, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
        return rootview;
    }

    private void init() {
        Button loginbutton = rootview.findViewById(R.id.btn_login);
        et_login_email = rootview.findViewById(R.id.et_login_email);
        et_login_password = rootview.findViewById(R.id.et_login_password);
        tv_signUp = rootview.findViewById(R.id.tv_signUp);
        tv_forgot_password = rootview.findViewById(R.id.tv_forgot_password);
        loginbutton.setOnClickListener(this);
        tv_signUp.setOnClickListener(this);
        tv_forgot_password.setOnClickListener(this);
    }

    private void startFragment(String tag, Fragment fragment) {
        loginfragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                .replace(R.id.login_frame, fragment, tag).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
               // loginApi();
                startFragment(Constant.SignUpFragment, new ForgotPasswordFragment());

                break;
            case R.id.tv_signUp:
                startFragment(Constant.SignUpFragment, new SignUpFragment());
                break;

                case R.id.tv_forgot_password:
                startFragment(Constant.SignUpFragment, new ForgotPasswordFragment1());
                break;



        }
    }

   /* private void loginApi() {
        if (cd.isNetWorkAvailable()) {
            strEmail = ((EditText) rootview.findViewById(R.id.et_login_email)).getText().toString();
            strPassword = ((EditText) rootview.findViewById(R.id.et_login_password)).getText().toString();
            if (strEmail.isEmpty()) {
                ((EditText) rootview.findViewById(R.id.et_login_email)).setError("Please enter email address");
            }else if (!EmailChecker.isValid(strEmail)) {
                et_login_email.setError("Please enter valid email address !!!");
            }
            else if (strPassword.isEmpty()) {
                ((EditText) rootview.findViewById(R.id.et_login_password)).setError("Please enter password");
            } else {
                RetrofitService.getLoginData(new Dialog(mContext), retrofitApiClient.loginData(strEmail, strPassword), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        LoginModel loginModel = (LoginModel) result.body();

                        if (!loginModel.getError())
                        {
                            Alerts.show(mContext, loginModel.getMessage());

                            AppPreference.setBooleanPreference(mContext, Constant.Is_Login , true);
                            AppPreference.setStringPreference(mContext, Constant.User_Id , loginModel.getUser().getId());

                            Gson gson = new GsonBuilder().setLenient().create();
                            String data = gson.toJson(loginModel);
                            AppPreference.setStringPreference(mContext, Constant.User_Data, data);
                            User.setUser(loginModel);

                            Intent intent = new Intent(mContext , HomeActivity.class);
                            mContext.startActivity(intent);
                            getActivity().finish();
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(mContext, error);
                    }
                });
            }
        }else {
            cd.show(mContext);
        }
    }*/
}
