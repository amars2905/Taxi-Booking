package com.texibook.ui.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.texibook.R;
import com.texibook.model.login_responce.LoginModel;
import com.texibook.retrofit_provider.RetrofitService;
import com.texibook.retrofit_provider.WebResponse;
import com.texibook.utils.Alerts;
import com.texibook.utils.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class SignInActivity extends BaseActivity {

    private EditText et_login_email;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initViews();
    }

    private void initViews() {
        et_login_email = findViewById(R.id.et_login_email);
        buttonLogin = findViewById(R.id.btn_login);
       /* AppSignatureHashHelper appSignatureHashHelper = new AppSignatureHashHelper(this);

        // This code requires one time to get Hash keys do comment and share key
        Log.e("Key", appSignatureHashHelper.getAppSignatures().get(0));*/

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String mobileNumber = et_login_email.getText().toString().trim();

                if (mobileNumber.isEmpty()) {
                    Toast.makeText(mContext, "Mobile number should not be empty.", Toast.LENGTH_SHORT).show();
                } else if (mobileNumber.length() < 10) {
                    Toast.makeText(mContext, "Please enter a valid 10 digit mobile number.", Toast.LENGTH_SHORT).show();
                } else if (cd.isNetWorkAvailable()) {
                    RetrofitService.getLoginData(new Dialog(mContext), retrofitApiClient.loginData(mobileNumber), new WebResponse() {
                        @Override
                        public void onResponseSuccess(Response<?> result) {
                            LoginModel loginModel = (LoginModel) result.body();
                            if (loginModel.getStatus() == 1) {
                                Alerts.show(mContext, loginModel.getMessage());

                                Intent intent = new Intent(SignInActivity.this, VerifyOtpActivity.class);
                                intent.putExtra("otp", loginModel.getOtp());
                                intent.putExtra("mobile", mobileNumber);

                                startActivity(intent);

                            } else {
                                Alerts.show(mContext, loginModel.getMessage());
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
        });
    }
}
