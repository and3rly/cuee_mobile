package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeUsuarios_servicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuarioServicio {
    @GET("UsuarioServicio/{IdRuta}/{IdItinerario}")
    Call<List<clsBeUsuarios_servicio>> getUsuariosServicio(@Path("IdRuta") int IdRuta, @Path("IdItinerario")  int IdItinerario);
}
