package com.texibook.model.coupan_modal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoupanMainModal implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("coupon")
    @Expose
    private List<Coupon> coupon = new ArrayList<Coupon>();
    public final static Parcelable.Creator<CoupanMainModal> CREATOR = new Creator<CoupanMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CoupanMainModal createFromParcel(Parcel in) {
            return new CoupanMainModal(in);
        }

        public CoupanMainModal[] newArray(int size) {
            return (new CoupanMainModal[size]);
        }

    }
            ;

    protected CoupanMainModal(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.coupon, (com.texibook.model.coupan_modal.Coupon.class.getClassLoader()));
    }

    public CoupanMainModal() {
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

    public List<Coupon> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<Coupon> coupon) {
        this.coupon = coupon;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeList(coupon);
    }

    public int describeContents() {
        return 0;
    }

}