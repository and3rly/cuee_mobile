package com.example.cuee_mobile.controladores.comunicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.api.servicios.Institucion;
import com.example.cuee_mobile.api.servicios.Ruta;
import com.example.cuee_mobile.api.servicios.Tecnico;
import com.example.cuee_mobile.clases.clsBeInstitucion;
import com.example.cuee_mobile.clases.clsBeInstitucion_detalle;
import com.example.cuee_mobile.clases.clsBeRuta_lectura;
import com.example.cuee_mobile.clases.clsBeRuta_tecnico;
import com.example.cuee_mobile.clases.clsBeTecnicos;
import com.example.cuee_mobile.controladores.PBase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComApi extends PBase {

    private String msjProg = "";
    private boolean ocupado = false;

    private RelativeLayout btnRecibir, btnEnviar, relPrg;
    private EditText txtRuta;
    private TextView lblPgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_api);

        super.SetBase();

        btnRecibir = findViewById(R.id.btnRecibir);
        btnEnviar = findViewById(R.id.btnEnviar);
        relPrg = findViewById(R.id.relPrg);
        lblPgr = findViewById(R.id.lblPgr);
        txtRuta = findViewById(R.id.txtRuta);

        setHandlers();
    }

    private void setHandlers() {
        btnRecibir.setOnClickListener(view -> {
            recibirDatos();
        });
    }

    private void recibirDatos() {
        try {
            relPrg.setVisibility(View.VISIBLE);
            btnRecibir.setVisibility(View.GONE);
            btnEnviar.setEnabled(false);
            lblPgr.setText("Procesando...");

            Handler mtimer = new Handler();
            Runnable mrunner = () -> {
                AsyncCallRec();
            };

            mtimer.postDelayed(mrunner, 500);

        } catch (Exception e) {

        }
    }

    public void GetInstitucion() {

        msjProg = "Porcesando institución...";
        actualizaProgress();

        try {

            Institucion cliente = retrofit.CrearServicio(Institucion.class);
            Call<List<clsBeInstitucion>> call = cliente.getInstitucion();

            call.enqueue(new Callback<List<clsBeInstitucion>>() {
                @Override
                public void onResponse(Call<List<clsBeInstitucion>> call, Response<List<clsBeInstitucion>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeInstitucion> rutaLectura = response.body();

                        GetInstitucionDetalle();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeInstitucion>> call, Throwable t) {

                }
            });
        } catch (Exception ex) {
            Log.e("test", "onCreate: "+ ex);
        }
    }

    public void GetInstitucionDetalle() {

        msjProg = "Porcesando detalle institución...";
        actualizaProgress();

        try {

            Institucion cliente = retrofit.CrearServicio(Institucion.class);
            Call<List<clsBeInstitucion_detalle>> call = cliente.getInstitucionDetalle();

            call.enqueue(new Callback<List<clsBeInstitucion_detalle>>() {
                @Override
                public void onResponse(Call<List<clsBeInstitucion_detalle>> call, Response<List<clsBeInstitucion_detalle>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeInstitucion_detalle> rutaLectura = response.body();

                        GetRutaLectura();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeInstitucion_detalle>> call, Throwable t) {

                }
            });
        } catch (Exception ex) {
            Log.e("test", "onCreate: "+ ex);
        }
    }

    public void GetRutaLectura() {

        msjProg = "Porcesando rutas lectura...";
        actualizaProgress();

        try {

            Ruta cliente = retrofit.CrearServicio(Ruta.class);
            Call<List<clsBeRuta_lectura>> call = cliente.getRutaLectura();

            call.enqueue(new Callback<List<clsBeRuta_lectura>>() {
                @Override
                public void onResponse(Call<List<clsBeRuta_lectura>> call, Response<List<clsBeRuta_lectura>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeRuta_lectura> rutaLectura = response.body();

                    }

                    GetTecicos();
                }
                @Override
                public void onFailure(Call<List<clsBeRuta_lectura>> call, Throwable t) {

                }
            });
        } catch (Exception ex) {
            Log.e("test", "onCreate: "+ ex);
        }
    }

    public void GetRutaTecnico() {

        msjProg = "Porcesando tecnicos por ruta...";
        actualizaProgress();

        try {

            Ruta cliente = retrofit.CrearServicio(Ruta.class);
            Call<List<clsBeRuta_tecnico>> call = cliente.getRutaTecnico();

            call.enqueue(new Callback<List<clsBeRuta_tecnico>>() {
                @Override
                public void onResponse(Call<List<clsBeRuta_tecnico>> call, Response<List<clsBeRuta_tecnico>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeRuta_tecnico> rutaLectura = response.body();

                    }
                }
                @Override
                public void onFailure(Call<List<clsBeRuta_tecnico>> call, Throwable t) {

                }
            });
        } catch (Exception ex) {
            Log.e("test", "onCreate: "+ ex);
        }
    }

    public void GetTecicos() {

        msjProg = "Porcesando tecnicos...";
        actualizaProgress();

        try {

            Tecnico cliente = retrofit.CrearServicio(Tecnico.class);
            Call<List<clsBeTecnicos>> call = cliente.getTecnicos();

            call.enqueue(new Callback<List<clsBeTecnicos>>() {
                @Override
                public void onResponse(Call<List<clsBeTecnicos>> call, Response<List<clsBeTecnicos>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeTecnicos> rutaLectura = response.body();

                        GetRutaTecnico();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeTecnicos>> call, Throwable t) {

                }
            });
        } catch (Exception ex) {
            Log.e("test", "onCreate: "+ ex);
        }
    }

    private void AsyncCallRec() {
        try {
            Executor executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                GetInstitucion();

                try {
                    handler.post(() -> {
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.post(() -> {
                    });
                }
            });
        } catch (Exception e) {

        }
    }

    private void terminaRecepcion() {
        ocupado = false;
        relPrg.setVisibility(View.GONE);
        btnRecibir.setVisibility(View.VISIBLE);
        btnEnviar.setEnabled(true);
    }

    private void actualizaProgress() {
        try {
            runOnUiThread(() -> lblPgr.setText(msjProg));
        }   catch (Exception e) {

        }
    }
}