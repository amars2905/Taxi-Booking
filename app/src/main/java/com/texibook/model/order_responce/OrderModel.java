
package com.texibook.model.order_responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<OrderModel> CREATOR = new Creator<OrderModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OrderModel createFromParcel(Parcel in) {
            return new OrderModel(in);
        }

        public OrderModel[] newArray(int size) {
            return (new OrderModel[size]);
        }

    }
    ;

    protected OrderModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OrderModel() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public OrderModel withError(Boolean error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderModel withMessage(String message) {
        this.message = message;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
