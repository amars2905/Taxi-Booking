
package com.texibook.model.address_add_responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AddAddressModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("address")
    @Expose
    private List<Address> address = new ArrayList<Address>();
    public final static Creator<AddAddressModel> CREATOR = new Creator<AddAddressModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AddAddressModel createFromParcel(Parcel in) {
            return new AddAddressModel(in);
        }

        public AddAddressModel[] newArray(int size) {
            return (new AddAddressModel[size]);
        }

    }
    ;

    protected AddAddressModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.address, (com.texibook.model.address_add_responce.Address.class.getClassLoader()));
    }

    public AddAddressModel() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public AddAddressModel withError(Boolean error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AddAddressModel withMessage(String message) {
        this.message = message;
        return this;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public AddAddressModel withAddress(List<Address> address) {
        this.address = address;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(address);
    }

    public int describeContents() {
        return  0;
    }

}
