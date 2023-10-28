package com.example.cuee_mobile.api;

import android.app.Activity;
import android.content.Context;

import com.example.cuee_mobile.base.VarGlobal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        OkHttpClient cliente = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Interceptor.Chain newChain = chain.withReadTimeout(10,TimeUnit.SECONDS);
                    return newChain.proceed(request);
                })
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
