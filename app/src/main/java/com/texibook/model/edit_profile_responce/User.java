
package com.texibook.model.edit_profile_responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_contact")
    @Expose
    private String userContact;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_profile_picture")
    @Expose
    private String userProfilePicture;
    @SerializedName("user_gender")
    @Expose
    private String userGender;
    @SerializedName("user_date_of_birth")
    @Expose
    private String userDateOfBirth;
    @SerializedName("user_created_date")
    @Expose
    private String userCreatedDate;
    public final static Creator<User> CREATOR = new Creator<User>() {


        @SuppressWarnings({
            "unchecked"
        })
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    }
    ;

    protected User(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.userEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.userContact = ((String) in.readValue((String.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.userProfilePicture = ((String) in.readValue((String.class.getClassLoader())));
        this.userGender = ((String) in.readValue((String.class.getClassLoader())));
        this.userDateOfBirth = ((String) in.readValue((String.class.getClassLoader())));
        this.userCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User withId(String id) {
        this.id = id;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public User withUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public User withUserContact(String userContact) {
        this.userContact = userContact;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    public User withUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
        return this;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public User withUserGender(String userGender) {
        this.userGender = userGender;
        return this;
    }

    public String getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(String userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public User withUserDateOfBirth(String userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
        return this;
    }

    public String getUserCreatedDate() {
        return userCreatedDate;
    }

    public void setUserCreatedDate(String userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
    }

    public User withUserCreatedDate(String userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(userEmail);
        dest.writeValue(userContact);
        dest.writeValue(userName);
        dest.writeValue(userProfilePicture);
        dest.writeValue(userGender);
        dest.writeValue(userDateOfBirth);
        dest.writeValue(userCreatedDate);
    }

    public int describeContents() {
        return  0;
    }

}
