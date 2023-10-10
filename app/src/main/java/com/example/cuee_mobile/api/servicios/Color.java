package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeColor;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Color {
    @GET("Color")
    Call<List<clsBeColor>> getColores();
}
