package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeRuta_lectura;
import com.example.cuee_mobile.clases.clsBeRuta_tecnico;
import com.example.cuee_mobile.clases.clsBeUsuarios_por_ruta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Ruta {
    @GET("RutaLectura")
    Call<List<clsBeRuta_lectura>> getRutaLectura();

    @GET("RutaTecnico")
    Call<List<clsBeRuta_tecnico>> getRutaTecnico();

    @GET("UsuariosRuta/{IdRuta}/{IdItinerario}")
    Call<List<clsBeUsuarios_por_ruta>> getUsuariosRuta(@Path("IdRuta") int IdRuta, @Path("IdItinerario")  int IdItinerario);
}
