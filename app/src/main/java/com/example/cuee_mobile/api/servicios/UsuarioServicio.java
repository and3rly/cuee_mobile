package com.example.cuee_mobile.api.servicios;

import com.example.cuee_mobile.clases.clsBeUsuarios_servicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsuarioServicio {
    @GET("UsuarioServicio")
    Call<List<clsBeUsuarios_servicio>> getUsuariosServicio();
}
