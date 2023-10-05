package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeLectura;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Lectura {
    @GET("Lectura/{IdRuta}/{IdItinerario}")
    Call<List<clsBeLectura>> getLectura(@Path("IdRuta") int IdRuta,
                                        @Path("IdItinerario") int IdItinerario);
}
