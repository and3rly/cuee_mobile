package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeRuta_lectura;
import com.example.cuee_mobile.clases.clsBeRuta_tecnico;
import com.example.cuee_mobile.clases.clsBeUsuarios_por_ruta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Ruta {
    @GET("RutaLectura/{IdRuta}")
    Call<List<clsBeRuta_lectura>> getRutaLectura(@Path("IdRuta") int IdRuta);

    @GET("RutaTecnico/{IdRuta}")
    Call<List<clsBeRuta_tecnico>> getRutaTecnico(@Path("IdRuta") int IdRuta);

    @GET("UsuariosRuta/{IdRuta}/{IdItinerario}")
    Call<List<clsBeUsuarios_por_ruta>> getUsuariosRuta(@Path("IdRuta") int IdRuta, @Path("IdItinerario")  int IdItinerario);
}
