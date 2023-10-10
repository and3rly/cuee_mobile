package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeServicios_instalado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiciosIns {
    @GET("ServiciosInstalado/{IdRuta}/{IdItinerario}")
    Call<List<clsBeServicios_instalado>> getServiciosInstalados(@Path("IdRuta") int IdRuta, @Path("IdItinerario")  int IdItinerario);
}
