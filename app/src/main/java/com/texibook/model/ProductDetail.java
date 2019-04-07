package com.texibook.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductDetail implements Parcelable {

    private int keyId;
    private String id;
    private String title;
    private String price;
    private String order_quantity;
    private String quantity_type;
    private String min_quantity;
    private String availability;
    private String discount;
    private String image;
    private String rating;
    private String description;
    private String type;
    private int quantity;

    public ProductDetail() {

    }

    public ProductDetail(String id, String title, String price, String order_quantity, String quantity_type, String min_quantity, String availability, String discount, String image, String rating, String description, String type, int quantity) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.order_quantity = order_quantity;
        this.quantity_type = quantity_type;
        this.min_quantity = min_quantity;
        this.availability = availability;
        this.discount = discount;
        this.image = image;
        this.rating = rating;
        this.description = description;
        this.type = type;
        this.quantity = quantity;
    }

    public ProductDetail(int keyId, String id, String title, String description, String image, String quantity_type, String price, String availability, String discount, String rating, String min_quantity, String order_quantity, String type, int quantity) {
        this.keyId = keyId;
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.quantity_type = quantity_type;
        this.price = price;
        this.availability = availability;
        this.discount = discount;
        this.rating = rating;
        this.min_quantity = min_quantity;
        this.order_quantity = order_quantity;
        this.type = type;
        this.quantity = quantity;
    }


    protected ProductDetail(Parcel in) {
        keyId = in.readInt();
        id = in.readString();
        title = in.readString();
        description = in.readString();
        image = in.readString();
        price = in.readString();
        quantity_type = in.readString();
        availability = in.readString();
        discount = in.readString();
        rating = in.readString();
        min_quantity = in.readString();
        order_quantity = in.readString();
        type = in.readString();
        quantity = in.readInt();

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity_type() {
        return quantity_type;
    }

    public void setQuantity_type(String quantity_type) {
        this.quantity_type = quantity_type;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMin_quantity() {
        return min_quantity;
    }

    public void setMin_quantity(String min_quantity) {
        this.min_quantity = min_quantity;
    }

    public String getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(String order_quantity) {
        this.order_quantity = order_quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static final Creator<ProductDetail> CREATOR = new Creator<ProductDetail>() {
        @Override
        public ProductDetail createFromParcel(Parcel in) {
            return new ProductDetail(in);
        }

        @Override
        public ProductDetail[] newArray(int size) {
            return new ProductDetail[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeString(getOrder_quantity());
        parcel.writeString(quantity_type);
        parcel.writeString(availability);
        parcel.writeString(image);
        parcel.writeString(discount);
        parcel.writeString(rating);
        parcel.writeString(min_quantity);
        parcel.writeInt(quantity);
        parcel.writeString(type);
        parcel.writeInt(keyId);
    }
}
