package com.texibook.ui.Activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.texibook.R;
import com.texibook.constant.Constant;
import com.texibook.retrofit_provider.RetrofitService;
import com.texibook.retrofit_provider.WebResponse;
import com.texibook.utils.Alerts;
import com.texibook.utils.AppPreference;
import com.texibook.utils.BaseActivity;
import com.texibook.utils.ConnectionDetector;
import com.texibook.utils.ConnectionDirector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {
    private EditText etFirstName, etLastName, etDateofBirth, etEmailAddress, etUserNumber;
    private CircleImageView ivProfileImage;
    private String strGender;
    private Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
    private ConnectionDetector cd;
    private static final int LOAD_IMAGE_GALLERY = 123;
    private static int PICK_IMAGE_CAMERA = 124;
    private static int PERMISSION_REQUEST_CODE = 456;
    private File finalFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
    }

    private void init() {
        if (checkPermission()) {
            Alerts.show(mContext, "Permission granted");
        } else {
            requestPermission();
        }

        String strMobile = AppPreference.getStringPreference(mContext, Constant.MobileNumber);

        ivProfileImage = findViewById(R.id.ivProfileImage);
        ivProfileImage.setOnClickListener(this);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etDateofBirth = findViewById(R.id.etDateofBirth);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etUserNumber = findViewById(R.id.etUserNumber);
        etUserNumber.setText(strMobile);

        ((Button) findViewById(R.id.btnUpdateProfile)).setOnClickListener(this);

        etDateofBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(etDateofBirth);
            }
        });
        radioGroupData();

    }

    private void radioGroupData() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.rgGender);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                strGender = rb.getText().toString();
            }
        });
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            Alerts.show(mContext, "Permission not granted");
            return false;
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (mContext.getContentResolver() != null) {
            Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    private void selectImage() {
        try {
            PackageManager pm = mContext.getPackageManager();
            int permission = pm.checkPermission(Manifest.permission.CAMERA, mContext.getPackageName());
            if (permission == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] choose = {"Pick From Camera", "Choose From Gallery", "Cancel"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
                builder.setTitle("Select Option");
                builder.setItems(choose, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (choose[which].equals("Pick From Camera")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        } else if (choose[which].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent i = new Intent(
                                    Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(i, LOAD_IMAGE_GALLERY);
                        } else if (choose[which].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(mContext, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(mContext, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_CAMERA) {
            try {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                ivProfileImage.setImageBitmap(photo);
                Uri tempUri = getImageUri(mContext, photo);
                finalFile = new File(getRealPathFromURI(tempUri));
                //api hit
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == LOAD_IMAGE_GALLERY && resultCode == RESULT_OK && null != data) {
            final Uri uriImage = data.getData();
            final InputStream inputStream;
            try {
                inputStream = mContext.getContentResolver().openInputStream(uriImage);
                final Bitmap imageMap = BitmapFactory.decodeStream(inputStream);
                ivProfileImage.setImageBitmap(imageMap);

                String imagePath2 = getPath(uriImage);
                File imageFile = new File(imagePath2);
                finalFile = imageFile;

                //api hit
            } catch (FileNotFoundException e) {
                Toast.makeText(mContext, "Image not found", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {

        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = mContext.getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String strPath = cursor.getString(column_index);
        cursor.close();
        return strPath;
    }

    private void openDatePicker(final EditText etDate) {
        int dobYear = Calendar.getInstance().get(Calendar.YEAR);
        int dobMonth = Calendar.getInstance().get(Calendar.MONTH);
        int dobDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(mContext, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String sDay;
                String sMonth;

                if (day <= 2) {
                    sDay = "0" + day;
                } else {
                    sDay = String.valueOf(day);
                }
                if ((month + 1) <= 9) {
                    sMonth = "0" + (month + 1);
                } else {
                    sMonth = String.valueOf((month + 1));
                }

                etDate.setText(sDay + "/" + sMonth + "/" + year);

            }
        }, dobYear, dobMonth, dobDay);
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        dialog.setTitle("");
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivProfileImage:
                try {
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, LOAD_IMAGE_GALLERY);
                    } else {
                        selectImage();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnUpdateProfile:
                updateProfileApi();
                break;
        }
    }

    private void updateProfileApi() {
        if (cd.isNetworkAvailable()) {
            String strUserId = AppPreference.getStringPreference(mContext, Constant.User_Id);
            String strFirstName = etFirstName.getText().toString();
            String strLastName = etLastName.getText().toString();
            String strEmail = etEmailAddress.getText().toString();
            String strDob = etDateofBirth.getText().toString();
            String strAddress = "abc";

            RequestBody str_UserId = RequestBody.create(MediaType.parse("text/plain"), strUserId);
            final RequestBody str_FirstName = RequestBody.create(MediaType.parse("text/plain"), strFirstName);
            final RequestBody str_LastName = RequestBody.create(MediaType.parse("text/plain"), strLastName);
            final RequestBody str_EmailAddress = RequestBody.create(MediaType.parse("text/plain"), strEmail);
            RequestBody str_Gender = RequestBody.create(MediaType.parse("text/plain"), strGender);
            final RequestBody str_DOB = RequestBody.create(MediaType.parse("text/plain"), strDob);
            RequestBody str_Address = RequestBody.create(MediaType.parse("text/plain"), strAddress);
            RequestBody imageBodyFile = RequestBody.create(MediaType.parse("image/*"), finalFile);
            MultipartBody.Part fileToUpload;
            if (imageBodyFile != null) {
                fileToUpload = MultipartBody.Part.createFormData("avtar_img", finalFile.getName(), imageBodyFile);
            } else {
                fileToUpload = MultipartBody.Part.createFormData("avtar_img", "");
            }
            if (strFirstName.isEmpty()) {
                Alerts.show(mContext, "Firstname should not be empty!!!");
            } else if (strLastName.isEmpty()) {
                Alerts.show(mContext, "Lastname should not be empty!!!");
            } else if (strEmail.isEmpty()) {
                Alerts.show(mContext, "Email address should not be empty!!!");
            } else if (strDob.isEmpty()) {
                Alerts.show(mContext, "Email address should not be empty!!!");
            } else {
                RetrofitService.getResponce(new Dialog(mContext), retrofitApiClient.updateProfile(str_UserId, str_FirstName,
                        str_LastName, str_DOB, str_EmailAddress, str_Gender, str_Address, fileToUpload), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) throws IOException {
                        ResponseBody mainModal = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(mainModal.string());
                            if (jsonObject.getString("status").equalsIgnoreCase("1")) {
                                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                    /*        AppPreference.setStringPreference(mContext,Constant.FirstName, str_FirstName);
                            AppPreference.setStringPreference(mContext,Constant.FirstName, str_LastName);
                            AppPreference.setStringPreference(mContext,Constant.FirstName, str_DOB);
                            AppPreference.setStringPreference(mContext,Constant.FirstName, str_EmailAddress);
                            AppPreference.setStringPreference(mContext,Constant.FirstName, str);*/
                                Alerts.show(mContext, jsonObject.getString("message"));
                            } else {
                                onBackPressed();
                                Alerts.show(mContext, jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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

}
