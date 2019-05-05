
package com.texibook.model.login_responce;


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("otp")
    @Expose
    private String otp;
    public final static Parcelable.Creator<LoginModel> CREATOR = new Creator<LoginModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LoginModel createFromParcel(Parcel in) {
            return new LoginModel(in);
        }

        public LoginModel[] newArray(int size) {
            return (new LoginModel[size]);
        }

    }
            ;

    protected LoginModel(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.otp = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LoginModel() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LoginModel withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginModel withMessage(String message) {
        this.message = message;
        return this;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LoginModel withOtp(String otp) {
        this.otp = otp;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(otp);
    }

    public int describeContents() {
        return 0;
    }

}