package com.example.cuee_mobile.api.servicios;
import com.example.cuee_mobile.clases.clsBeCorrelativo_proforma;
import com.example.cuee_mobile.clases.clsBeLectura;
import com.example.cuee_mobile.clases.clsBeMeses_pago;
import com.example.cuee_mobile.clases.clsBeProforma;
import com.example.cuee_mobile.clases.clsBeRazon_no_lectura;
import com.example.cuee_mobile.clases.clsBeUsuario_sin_lectura;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Catalogo {
    @GET("MesesPago")
    Call<List<clsBeMeses_pago>> get_MesesPago(@Query("IdRuta") int IdRuta,
                                              @Query("IdItinerario") int IdItinerario);

    @GET("RazonNoLectura")
    Call<List<clsBeRazon_no_lectura>> get_razon_no_lectura();

    @POST("Proformas/guardar")
    Call<String> guardarProforma(@Body clsBeProforma beProforma);

    @POST("UsuarioSinLectura/guardar")
    Call<String> guardar(@Body clsBeUsuario_sin_lectura beProforma);


    @Headers("Content-Type:application/json; charset=UTF-8")
    @POST("CorrelativoProforma/actualizar_correlativo")
    Call<String> actualizar_correlativo(@Body clsBeCorrelativo_proforma beCorrelativo);
}
