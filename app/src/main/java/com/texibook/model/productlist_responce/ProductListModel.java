
package com.texibook.model.productlist_responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductListModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("product")
    @Expose
    private List<Product> product = new ArrayList<Product>();
    public final static Creator<ProductListModel> CREATOR = new Creator<ProductListModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ProductListModel createFromParcel(Parcel in) {
            return new ProductListModel(in);
        }

        public ProductListModel[] newArray(int size) {
            return (new ProductListModel[size]);
        }

    }
    ;

    protected ProductListModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.product, (Product.class.getClassLoader()));
    }

    public ProductListModel() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ProductListModel withError(Boolean error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductListModel withMessage(String message) {
        this.message = message;
        return this;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public ProductListModel withProduct(List<Product> product) {
        this.product = product;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(product);
    }

    public int describeContents() {
        return  0;
    }

}
