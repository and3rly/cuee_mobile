package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBePagos_detalle_rep;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Reporte {
    @GET("PagosDetalleRep")
    Call<List<clsBePagos_detalle_rep>> getPagosDet();
}
