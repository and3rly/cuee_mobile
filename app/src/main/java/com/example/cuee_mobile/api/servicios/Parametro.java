package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeParametros;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Parametro {
    @GET("Parametros")
    Call<List<clsBeParametros>> getParametros();
}
