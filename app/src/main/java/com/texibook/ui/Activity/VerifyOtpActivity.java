package com.texibook.ui.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.texibook.R;
import com.texibook.constant.Constant;
import com.texibook.model.User;
import com.texibook.model.login_responce.LoginModel;
import com.texibook.model.otp_responce.OtpModel;
import com.texibook.retrofit_provider.RetrofitService;
import com.texibook.retrofit_provider.WebResponse;
import com.texibook.utils.Alerts;
import com.texibook.utils.AppPreference;
import com.texibook.utils.BaseActivity;
import com.texibook.utils.pinview.Pinview;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.texibook.ui.Activity.LoginActivity.loginfragmentManager;


public class VerifyOtpActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_fplogin;
    private TextView otpTime, tvChangeMobile, btnResend;
    private LinearLayout resendLayout;
    private Pinview pinview1;
    private String strMobile, strOtp, strUserProfile;
    // private SMSReceiver smsReceiver;
    public static final String TAG = "VerifyOtpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        strMobile = getIntent().getExtras().getString("mobile");
        strOtp = getIntent().getExtras().getString("otp");

        initViews();

        //   startSMSListener();
    }

    private void initViews() {
        ((Button) findViewById(R.id.btn_fplogin)).setOnClickListener(this);
        btn_fplogin = findViewById(R.id.btn_fplogin);
        pinview1 = findViewById(R.id.pinview1);
        tvChangeMobile = findViewById(R.id.tvChangeMobile);
        otpTime = (TextView) findViewById(R.id.otpTime);
        resendLayout = (LinearLayout) findViewById(R.id.resendLayout);
        btn_fplogin.setOnClickListener(this);
        tvChangeMobile.setOnClickListener(this);
        btnResend = findViewById(R.id.btnResend);
        btnResend.setOnClickListener(this);

        pinview1.setValue(strOtp);
        otpTime();
    }

    private void startFragment(String tag, Fragment fragment) {
        loginfragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.login_frame, fragment, tag).commit();
    }

    private void otpTime() {
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                otpTime.setVisibility(View.VISIBLE);
                otpTime.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                otpTime.setVisibility(View.GONE);
                resendLayout.setVisibility(View.VISIBLE);
                pinview1.setValue("");
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fplogin:
              otpApi();
                //startFragment(Constant.SignUpFragment,new SignUpFragment());
                break;
            case R.id.tvChangeMobile:
                startActivity(new Intent(mContext, SignInActivity.class));
                finish();
                break;

            case R.id.btnResend:
                resendApi();
                otpTime();
                break;
        }
    }

    private void otpApi() {
        if (cd.isNetWorkAvailable()) {
            //strMobile = ((EditText) findViewById(R.id.et_login_email)).getText().toString();
            strOtp = pinview1.getValue();
            if (strOtp.length() != 6) {
                /*((EditText) findViewById(R.id.et_login_password)).setError("Please enter otp");*/
                Toast.makeText(this, "Please enter otp", Toast.LENGTH_SHORT).show();
            } else {
                RetrofitService.getOtpData(new Dialog(mContext), retrofitApiClient.otpApi(strMobile, strOtp, "1"), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        OtpModel loginModel = (OtpModel) result.body();
                        if (loginModel.getStatus() == 1) {
                            Alerts.show(mContext, loginModel.getMessage());
                            AppPreference.setBooleanPreference(mContext, Constant.Is_Login, true);
                            AppPreference.setStringPreference(mContext, Constant.User_Id, loginModel.getData().getUserId());
                            AppPreference.setStringPreference(mContext, Constant.MobileNumber, strMobile);
                            Gson gson = new GsonBuilder().setLenient().create();
                            String data = gson.toJson(loginModel);
                            AppPreference.setStringPreference(mContext, Constant.User_Data, data);
                            User.setUser(loginModel);

                            if (loginModel.getLoginStatus().equals("1")) {
                                Intent intent = new Intent(mContext, HomeActivity.class);
                                // intent.putExtra("Mobile_Number", strMobile);
                                mContext.startActivity(intent);
                                finish();
                            } else {
                                Alerts.show(mContext, "New User");
                                Intent intent = new Intent(mContext, ProfileActivity.class);
                                // intent.putExtra("Mobile_Number", strMobile);
                                mContext.startActivity(intent);
                                finish();
                            }

                        } else {
                            Alerts.show(mContext, loginModel.getMessage());
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(mContext, error);
                    }
                });
            }
        } else {
            cd.show(mContext);
        }
    }


    private void resendApi() {
        RetrofitService.getLoginData(new Dialog(mContext), retrofitApiClient.loginData(strMobile), new WebResponse() {
            @Override
            public void onResponseSuccess(Response<?> result) {

                LoginModel loginModel = (LoginModel) result.body();
                if (loginModel.getStatus() == 1) {
                    Alerts.show(mContext, loginModel.getMessage());


                } else {
                    Alerts.show(mContext, loginModel.getMessage());
                }
            }

            @Override
            public void onResponseFailed(String error) {
                Alerts.show(mContext, error);
            }
        });
    }

   /* private void startSMSListener() {
        try {
            smsReceiver = new SMSReceiver();
            smsReceiver.setOTPListener(this);

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
            this.registerReceiver(smsReceiver, intentFilter);

            SmsRetrieverClient client = SmsRetriever.getClient(this);

            Task<Void> task = client.startSmsRetriever();
            task.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // API successfully started
                }
            });

            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Fail to start API
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onOTPReceived(String otp) {
        showToast(otp);

        if (smsReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(smsReceiver);
        }
    }

    @Override
    public void onOTPTimeOut() {
        showToast("OTP Time out");

    }

    @Override
    public void onOTPReceivedError(String error) {
        showToast(error);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (smsReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(smsReceiver);
        }
    }


    private void showToast(String msg) {
        String numberOnly= msg.replaceAll("[^0-9]", "");
        pinview1.setValue(numberOnly);
       // Toast.makeText(this, numberOnly, Toast.LENGTH_SHORT).show();
    }*/

}
