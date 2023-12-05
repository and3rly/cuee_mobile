package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeMeses_mora_pagada;
import com.example.cuee_mobile.clases.clsBeMeses_mora_proforma;
import com.example.cuee_mobile.clases.clsBeMeses_proforma;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MesProforma {
    @GET("MesesProforma")
    Call<List<clsBeMeses_proforma>> getMesProforma();

    @GET("MesesMoraProforma")
    Call<List<clsBeMeses_mora_proforma>> getMesesMoraProforma();

    @GET("MesesMoraPagada")
    Call<List<clsBeMeses_mora_pagada>> getMesesMoraPagada(@Query("IdRuta") int IdRuta,
                                                          @Query("IdItinerario") int IdItinerario);
}
