package com.example.cuee_mobile.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteConfig {
    private static Retrofit retrofit = null;
    private String url = "http://192.168.1.4/cuee_api/";

    public ClienteConfig(Context cont) {

    }

    public  <S> S CrearServicio(Class<S> claseServicio) {
        getCliente();
        return retrofit.create(claseServicio);
    }

    public void getCliente() {

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
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(cliente)
                    .build();
        }

    }
}
