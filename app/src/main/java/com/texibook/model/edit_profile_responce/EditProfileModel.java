
package com.texibook.model.edit_profile_responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditProfileModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user")
    @Expose
    private User user;
    public final static Creator<EditProfileModel> CREATOR = new Creator<EditProfileModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public EditProfileModel createFromParcel(Parcel in) {
            return new EditProfileModel(in);
        }

        public EditProfileModel[] newArray(int size) {
            return (new EditProfileModel[size]);
        }

    }
    ;

    protected EditProfileModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.user = ((User) in.readValue((User.class.getClassLoader())));
    }

    public EditProfileModel() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public EditProfileModel withError(Boolean error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EditProfileModel withMessage(String message) {
        this.message = message;
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EditProfileModel withUser(User user) {
        this.user = user;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeValue(user);
    }

    public int describeContents() {
        return  0;
    }

}
