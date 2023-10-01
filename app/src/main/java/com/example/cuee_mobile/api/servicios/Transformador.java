package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeTransformadores;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Transformador {

    @GET("Transformadores")
    Call<List<clsBeTransformadores>> getTransformadores();
}
