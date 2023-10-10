package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeMarcas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Marca {
    @GET("Marca")
    Call<List<clsBeMarcas>> getMarcas();
}
