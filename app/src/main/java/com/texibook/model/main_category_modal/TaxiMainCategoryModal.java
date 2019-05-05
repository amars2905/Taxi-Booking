package com.texibook.model.main_category_modal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaxiMainCategoryModal implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("vehicle")
    @Expose
    private List<Vehicle> vehicle = new ArrayList<Vehicle>();
    public final static Parcelable.Creator<TaxiMainCategoryModal> CREATOR = new Creator<TaxiMainCategoryModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TaxiMainCategoryModal createFromParcel(Parcel in) {
            return new TaxiMainCategoryModal(in);
        }

        public TaxiMainCategoryModal[] newArray(int size) {
            return (new TaxiMainCategoryModal[size]);
        }

    }
            ;

    protected TaxiMainCategoryModal(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.vehicle, (com.texibook.model.main_category_modal.Vehicle.class.getClassLoader()));
    }

    public TaxiMainCategoryModal() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeList(vehicle);
    }

    public int describeContents() {
        return 0;
    }

}