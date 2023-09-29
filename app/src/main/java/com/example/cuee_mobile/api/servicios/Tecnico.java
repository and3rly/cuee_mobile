package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeTecnicos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Tecnico {
    @GET("Tecnico")
    Call<List<clsBeTecnicos>> getTecnicos();
}
