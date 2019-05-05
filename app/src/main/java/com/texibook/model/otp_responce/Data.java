
package com.texibook.model.otp_responce;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("fcm_id")
    @Expose
    private String fcmId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("otp_verification")
    @Expose
    private String otpVerification;
    @SerializedName("verified")
    @Expose
    private String verified;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;

    protected Data(Parcel in) {
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.dob = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.profilePic = ((String) in.readValue((String.class.getClassLoader())));
        this.fcmId = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.otp = ((String) in.readValue((String.class.getClassLoader())));
        this.otpVerification = ((String) in.readValue((String.class.getClassLoader())));
        this.verified = ((String) in.readValue((String.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.created = ((String) in.readValue((String.class.getClassLoader())));
        this.modified = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Data() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Data withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Data withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Data withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Data withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Data withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Data withMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Data withDob(String dob) {
        this.dob = dob;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Data withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Data withProfilePic(String profilePic) {
        this.profilePic = profilePic;
        return this;
    }

    public String getFcmId() {
        return fcmId;
    }

    public void setFcmId(String fcmId) {
        this.fcmId = fcmId;
    }

    public Data withFcmId(String fcmId) {
        this.fcmId = fcmId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Data withOtp(String otp) {
        this.otp = otp;
        return this;
    }

    public String getOtpVerification() {
        return otpVerification;
    }

    public void setOtpVerification(String otpVerification) {
        this.otpVerification = otpVerification;
    }

    public Data withOtpVerification(String otpVerification) {
        this.otpVerification = otpVerification;
        return this;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public Data withVerified(String verified) {
        this.verified = verified;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Data withToken(String token) {
        this.token = token;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Data withCreated(String created) {
        this.created = created;
        return this;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Data withModified(String modified) {
        this.modified = modified;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userId);
        dest.writeValue(firstName);
        dest.writeValue(lastName);
        dest.writeValue(email);
        dest.writeValue(gender);
        dest.writeValue(mobileNumber);
        dest.writeValue(dob);
        dest.writeValue(address);
        dest.writeValue(profilePic);
        dest.writeValue(fcmId);
        dest.writeValue(status);
        dest.writeValue(otp);
        dest.writeValue(otpVerification);
        dest.writeValue(verified);
        dest.writeValue(token);
        dest.writeValue(created);
        dest.writeValue(modified);
    }

    public int describeContents() {
        return  0;
    }

}
