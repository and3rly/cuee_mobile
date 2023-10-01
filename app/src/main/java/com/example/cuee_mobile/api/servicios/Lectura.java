package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeLectura;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Lectura {
    @GET("Lectura")
    Call<List<clsBeLectura>> getLectura();
}
