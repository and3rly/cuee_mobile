package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeServicios_instalado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiciosIns {
    @GET("ServiciosInstalado")
    Call<List<clsBeServicios_instalado>> getServiciosInstalados();
}
