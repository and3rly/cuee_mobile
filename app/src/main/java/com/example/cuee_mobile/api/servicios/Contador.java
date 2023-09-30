package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeContadores;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Contador {
    @GET("Contadores")
    Call<List<clsBeContadores>> getContadores();
}
