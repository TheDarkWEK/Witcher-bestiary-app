package com.example.thewitcher3bestiary.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Response {
    @SerializedName("bestiary")
    private List<ObjectBeast> bests;
    public List<ObjectBeast> getBests() {
        return bests;
    }
}
