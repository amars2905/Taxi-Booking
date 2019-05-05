
package com.texibook.model.otp_responce;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpModel implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("loginStatus")
    @Expose
    private String loginStatus;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Creator<OtpModel> CREATOR = new Creator<OtpModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OtpModel createFromParcel(Parcel in) {
            return new OtpModel(in);
        }

        public OtpModel[] newArray(int size) {
            return (new OtpModel[size]);
        }

    }
    ;

    protected OtpModel(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.loginStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public OtpModel() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OtpModel withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OtpModel withMessage(String message) {
        this.message = message;
        return this;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public OtpModel withLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
        return this;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public OtpModel withData(Data data) {
        this.data = data;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(loginStatus);
        dest.writeValue(data);
    }

    public int describeContents() {
        return  0;
    }

}
