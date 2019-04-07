
package com.texibook.model.productdetail_responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("product")
    @Expose
    private Product product;
    public final static Creator<ProductDetailModel> CREATOR = new Creator<ProductDetailModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ProductDetailModel createFromParcel(Parcel in) {
            return new ProductDetailModel(in);
        }

        public ProductDetailModel[] newArray(int size) {
            return (new ProductDetailModel[size]);
        }

    }
    ;

    protected ProductDetailModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.product = ((Product) in.readValue((Product.class.getClassLoader())));
    }

    public ProductDetailModel() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ProductDetailModel withError(Boolean error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductDetailModel withMessage(String message) {
        this.message = message;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductDetailModel withProduct(Product product) {
        this.product = product;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeValue(product);
    }

    public int describeContents() {
        return  0;
    }

}
