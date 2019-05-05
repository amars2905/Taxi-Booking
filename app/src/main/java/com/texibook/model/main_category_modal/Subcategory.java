package com.texibook.model.main_category_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subcategory implements Parcelable
{

@SerializedName("subcategory_id")
@Expose
private String subcategoryId;
@SerializedName("category_id")
@Expose
private String categoryId;
@SerializedName("name")
@Expose
private String name;
@SerializedName("status")
@Expose
private String status;
@SerializedName("created")
@Expose
private String created;
public final static Parcelable.Creator<Subcategory> CREATOR = new Creator<Subcategory>() {


@SuppressWarnings({
"unchecked"
})
public Subcategory createFromParcel(Parcel in) {
return new Subcategory(in);
}

public Subcategory[] newArray(int size) {
return (new Subcategory[size]);
}

}
;

protected Subcategory(Parcel in) {
this.subcategoryId = ((String) in.readValue((String.class.getClassLoader())));
this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
this.name = ((String) in.readValue((String.class.getClassLoader())));
this.status = ((String) in.readValue((String.class.getClassLoader())));
this.created = ((String) in.readValue((String.class.getClassLoader())));
}

public Subcategory() {
}

public String getSubcategoryId() {
return subcategoryId;
}

public void setSubcategoryId(String subcategoryId) {
this.subcategoryId = subcategoryId;
}

public String getCategoryId() {
return categoryId;
}

public void setCategoryId(String categoryId) {
this.categoryId = categoryId;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getCreated() {
return created;
}

public void setCreated(String created) {
this.created = created;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(subcategoryId);
dest.writeValue(categoryId);
dest.writeValue(name);
dest.writeValue(status);
dest.writeValue(created);
}

public int describeContents() {
return 0;
}

}