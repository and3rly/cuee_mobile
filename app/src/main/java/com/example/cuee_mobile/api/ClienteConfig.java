package com.example.cuee_mobile.api;

import android.app.Activity;
import android.content.Context;

import com.example.cuee_mobile.base.VarGlobal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteConfig {
    private static Retrofit retrofit = null;
    private VarGlobal gl;
    public ClienteConfig(Context cont) {
        gl = ((VarGlobal) (((Activity) cont).getApplication()));
    }

    public  <S> S CrearServicio(Class<S> claseServicio) {
        getCliente();
        return retrofit.create(claseServicio);
    }

    public void getCliente() {
        //gl.urlApi = "http://192.168.1.3/cuee_api/";
        OkHttpClient cliente = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(gl.urlApi)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(cliente)
                    .build();
        }

    }
}
