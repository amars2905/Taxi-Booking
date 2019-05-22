package com.texibook.model.coupan_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coupon implements Parcelable
{

@SerializedName("coupon_id")
@Expose
private String couponId;
@SerializedName("code")
@Expose
private String code;
@SerializedName("description")
@Expose
private String description;
@SerializedName("status")
@Expose
private String status;
@SerializedName("created_date_time")
@Expose
private String createdDateTime;
public final static Parcelable.Creator<Coupon> CREATOR = new Creator<Coupon>() {


@SuppressWarnings({
"unchecked"
})
public Coupon createFromParcel(Parcel in) {
return new Coupon(in);
}

public Coupon[] newArray(int size) {
return (new Coupon[size]);
}

}
;

protected Coupon(Parcel in) {
this.couponId = ((String) in.readValue((String.class.getClassLoader())));
this.code = ((String) in.readValue((String.class.getClassLoader())));
this.description = ((String) in.readValue((String.class.getClassLoader())));
this.status = ((String) in.readValue((String.class.getClassLoader())));
this.createdDateTime = ((String) in.readValue((String.class.getClassLoader())));
}

public Coupon() {
}

public String getCouponId() {
return couponId;
}

public void setCouponId(String couponId) {
this.couponId = couponId;
}

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getCreatedDateTime() {
return createdDateTime;
}

public void setCreatedDateTime(String createdDateTime) {
this.createdDateTime = createdDateTime;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(couponId);
dest.writeValue(code);
dest.writeValue(description);
dest.writeValue(status);
dest.writeValue(createdDateTime);
}

public int describeContents() {
return 0;
}

}