package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeTecnicos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Tecnico {
    @GET("Tecnico/{IdRuta}")
    Call<List<clsBeTecnicos>> getTecnicos(@Path("IdRuta") int IdRuta);
}
