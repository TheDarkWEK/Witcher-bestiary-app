package com.example.thewitcher3bestiary.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
@GET("Witcher_Bestiary_JSON.json")
    Call<Response> getBeasts();
}
