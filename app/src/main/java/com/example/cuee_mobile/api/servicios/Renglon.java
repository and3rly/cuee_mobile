package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeRenglones;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Renglon {
    @GET("Renglones")
    Call<List<clsBeRenglones>> getRenglones();
}
