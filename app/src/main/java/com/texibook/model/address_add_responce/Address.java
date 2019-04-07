
package com.texibook.model.address_add_responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable
{

    @SerializedName("address_id")
    @Expose
    private String addressId;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("house_number")
    @Expose
    private String houseNumber;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address_type  ")
    @Expose
    private String addressType;
    @SerializedName("zipcode  ")
    @Expose
    private String zipcode;
    @SerializedName("user_city")
    @Expose
    private String userCity;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    public final static Creator<Address> CREATOR = new Creator<Address>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return (new Address[size]);
        }

    }
    ;

    protected Address(Parcel in) {
        this.addressId = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((String) in.readValue((String.class.getClassLoader())));
        this._long = ((String) in.readValue((String.class.getClassLoader())));
        this.houseNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.addressType = ((String) in.readValue((String.class.getClassLoader())));
        this.zipcode = ((String) in.readValue((String.class.getClassLoader())));
        this.userCity = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Address() {
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Address withAddressId(String addressId) {
        this.addressId = addressId;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Address withLocation(String location) {
        this.location = location;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Address withLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public Address withLong(String _long) {
        this._long = _long;
        return this;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Address withHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Address withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public Address withAddressType(String addressType) {
        this.addressType = addressType;
        return this;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Address withZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public Address withUserCity(String userCity) {
        this.userCity = userCity;
        return this;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Address withState(String state) {
        this.state = state;
        return this;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Address withCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(addressId);
        dest.writeValue(location);
        dest.writeValue(lat);
        dest.writeValue(_long);
        dest.writeValue(houseNumber);
        dest.writeValue(address);
        dest.writeValue(addressType);
        dest.writeValue(zipcode);
        dest.writeValue(userCity);
        dest.writeValue(state);
        dest.writeValue(createdDate);
    }

    public int describeContents() {
        return  0;
    }

}
