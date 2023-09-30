package com.example.cuee_mobile.controladores.comunicacion;

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
import com.example.cuee_mobile.clases.clsBeUsuarios_por_ruta;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.modelos.institucion.InstitucionDetalleModel;
import com.example.cuee_mobile.modelos.institucion.InstitucionModel;
import com.example.cuee_mobile.modelos.meses.MesProformaModel;
import com.example.cuee_mobile.modelos.meses.MoraPagadaModel;
import com.example.cuee_mobile.modelos.meses.MoraProformaModel;
import com.example.cuee_mobile.modelos.mnt.ContadoresModel;
import com.example.cuee_mobile.modelos.mnt.ParametrosModel;
import com.example.cuee_mobile.modelos.mnt.TecnicoModel;
import com.example.cuee_mobile.modelos.reporte.PagosDetModel;
import com.example.cuee_mobile.modelos.ruta.RutaLecturaModel;
import com.example.cuee_mobile.modelos.ruta.RutaTecnicoModel;
import com.example.cuee_mobile.modelos.ruta.UsuariosRutaModel;

import java.util.List;
import java.util.Objects;
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
    private InstitucionModel institucion;
    private InstitucionDetalleModel insDetalle;
    private RutaLecturaModel rutaLectura;
    private RutaTecnicoModel rutaTec;
    private UsuariosRutaModel rutaUsr;
    private TecnicoModel tecnico;
    private ParametrosModel prametro;
    private ContadoresModel contador;
    private MesProformaModel mesProf;
    private MoraPagadaModel moraPg;
    private MoraProformaModel moraProf;
    private PagosDetModel pagoDet;


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

        institucion = new InstitucionModel(this, Con, db);
        insDetalle = new InstitucionDetalleModel(this, Con, db);
        rutaLectura = new RutaLecturaModel(this, Con, db);
        tecnico = new TecnicoModel(this, Con, db);
        rutaTec = new RutaTecnicoModel(this, Con, db);
        rutaUsr = new UsuariosRutaModel(this, Con, db);

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

            Handler timer = new Handler();
            Runnable runner = this::AsyncCallRec;

            timer.postDelayed(runner, 500);

        } catch (Exception e) {

        }
    }

    public void getInstitucion() {

        msjProg = "Porcesando institución...";
        actualizaProgress();

        try {

            Institucion cliente = retrofit.CrearServicio(Institucion.class);
            Call<List<clsBeInstitucion>> call = cliente.getInstitucion();

            call.enqueue(new Callback<List<clsBeInstitucion>>() {
                @Override
                public void onResponse(Call<List<clsBeInstitucion>> call, Response<List<clsBeInstitucion>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeInstitucion> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeInstitucion obj : lista) {
                                institucion.guardar(obj);
                            }
                        }
                        getInstitucionDetalle();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeInstitucion>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getInstitucionDetalle() {

        msjProg = "Porcesando detalle institución...";
        actualizaProgress();

        try {

            Institucion cliente = retrofit.CrearServicio(Institucion.class);
            Call<List<clsBeInstitucion_detalle>> call = cliente.getInstitucionDetalle();

            call.enqueue(new Callback<List<clsBeInstitucion_detalle>>() {
                @Override
                public void onResponse(Call<List<clsBeInstitucion_detalle>> call, Response<List<clsBeInstitucion_detalle>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeInstitucion_detalle> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeInstitucion_detalle obj:lista) {
                                insDetalle.guardar(obj);
                            }
                        }
                        getRutaLectura();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeInstitucion_detalle>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getRutaLectura() {

        msjProg = "Porcesando rutas lectura...";
        actualizaProgress();

        try {

            Ruta cliente = retrofit.CrearServicio(Ruta.class);
            Call<List<clsBeRuta_lectura>> call = cliente.getRutaLectura();

            call.enqueue(new Callback<List<clsBeRuta_lectura>>() {
                @Override
                public void onResponse(Call<List<clsBeRuta_lectura>> call, Response<List<clsBeRuta_lectura>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeRuta_lectura> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeRuta_lectura obj:lista) {
                                rutaLectura.guardar(obj);
                            }
                        }
                    }
                    getTecnicos();
                }
                @Override
                public void onFailure(Call<List<clsBeRuta_lectura>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getTecnicos() {

        msjProg = "Porcesando tecnicos...";
        actualizaProgress();

        try {

            Tecnico cliente = retrofit.CrearServicio(Tecnico.class);
            Call<List<clsBeTecnicos>> call = cliente.getTecnicos();

            call.enqueue(new Callback<List<clsBeTecnicos>>() {
                @Override
                public void onResponse(Call<List<clsBeTecnicos>> call, Response<List<clsBeTecnicos>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeTecnicos> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeTecnicos obj:lista) {
                                tecnico.guardar(obj);
                            }
                        }
                        getRutaTecnico();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeTecnicos>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getRutaTecnico() {

        msjProg = "Porcesando tecnicos por ruta...";
        actualizaProgress();

        try {

            Ruta cliente = retrofit.CrearServicio(Ruta.class);
            Call<List<clsBeRuta_tecnico>> call = cliente.getRutaTecnico();

            call.enqueue(new Callback<List<clsBeRuta_tecnico>>() {
                @Override
                public void onResponse(Call<List<clsBeRuta_tecnico>> call, Response<List<clsBeRuta_tecnico>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeRuta_tecnico> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeRuta_tecnico obj:lista) {
                                rutaTec.guardar(obj);
                            }
                        }
                        getUsuariosRuta();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeRuta_tecnico>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getUsuariosRuta() {

        msjProg = "Porcesando usuarios por ruta...";
        actualizaProgress();

        try {

            Ruta cliente = retrofit.CrearServicio(Ruta.class);
            Call<List<clsBeUsuarios_por_ruta>> call = cliente.getUsuariosRuta();

            call.enqueue(new Callback<List<clsBeUsuarios_por_ruta>>() {
                @Override
                public void onResponse(Call<List<clsBeUsuarios_por_ruta>> call, Response<List<clsBeUsuarios_por_ruta>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeUsuarios_por_ruta> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeUsuarios_por_ruta obj:lista) {
                                rutaUsr.guardar(obj);
                            }
                        }
                        getRutaTecnico();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeUsuarios_por_ruta>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void AsyncCallRec() {
        try {
            Executor executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                getInstitucion();

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
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
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
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }
}