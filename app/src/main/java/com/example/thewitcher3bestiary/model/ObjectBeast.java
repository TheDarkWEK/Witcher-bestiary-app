package com.example.thewitcher3bestiary.model;
import com.google.gson.annotations.SerializedName;

public class ObjectBeast {
    @SerializedName("desc")
    public String desc;
    @SerializedName("image")
    public String image;
    @SerializedName("location")
    public String location;
    @SerializedName("loot")
    public String loot;
    @SerializedName("name")
    public String name;
    @SerializedName("type")
    public String type;
    @SerializedName("weakness")
    public String weakness;

    public String getDesc() {
        return desc;
    }

    public String getImage() {
        return image;
    }

    public String getLocation() {
        return location;
    }

    public String getLoot() {
        return loot;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getWeakness() {
        return weakness;
    }
}
