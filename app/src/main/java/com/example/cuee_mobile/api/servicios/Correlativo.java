package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeCorrelativo_proforma;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Correlativo {
    @GET("CorrelativoProforma")
    Call<List<clsBeCorrelativo_proforma>> get_CorrelativosProforma_by_IdRuta(@Query("idruta") int IdRuta);
}
