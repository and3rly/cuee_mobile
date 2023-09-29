package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeInstitucion;
import com.example.cuee_mobile.clases.clsBeInstitucion_detalle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Institucion {
    @GET("Institucion")
    Call<List<clsBeInstitucion>> getInstitucion();

    @GET("InstitucionDetalle")
    Call<List<clsBeInstitucion_detalle>> getInstitucionDetalle();
}
