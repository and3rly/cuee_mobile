package com.example.cuee_mobile.controladores.comunicacion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.api.servicios.Contador;
import com.example.cuee_mobile.api.servicios.Institucion;
import com.example.cuee_mobile.api.servicios.Lectura;
import com.example.cuee_mobile.api.servicios.MesProforma;
import com.example.cuee_mobile.api.servicios.Parametro;
import com.example.cuee_mobile.api.servicios.Renglon;
import com.example.cuee_mobile.api.servicios.Reporte;
import com.example.cuee_mobile.api.servicios.Ruta;
import com.example.cuee_mobile.api.servicios.ServiciosIns;
import com.example.cuee_mobile.api.servicios.Tecnico;
import com.example.cuee_mobile.api.servicios.Transformador;
import com.example.cuee_mobile.api.servicios.UsuarioServicio;
import com.example.cuee_mobile.clases.clsBeContadores;
import com.example.cuee_mobile.clases.clsBeInstitucion;
import com.example.cuee_mobile.clases.clsBeInstitucion_detalle;
import com.example.cuee_mobile.clases.clsBeLectura;
import com.example.cuee_mobile.clases.clsBeMeses_mora_pagada;
import com.example.cuee_mobile.clases.clsBeMeses_mora_proforma;
import com.example.cuee_mobile.clases.clsBeMeses_proforma;
import com.example.cuee_mobile.clases.clsBePagos_detalle_rep;
import com.example.cuee_mobile.clases.clsBeParametros;
import com.example.cuee_mobile.clases.clsBeRenglones;
import com.example.cuee_mobile.clases.clsBeRuta_lectura;
import com.example.cuee_mobile.clases.clsBeRuta_tecnico;
import com.example.cuee_mobile.clases.clsBeServicios_instalado;
import com.example.cuee_mobile.clases.clsBeTecnicos;
import com.example.cuee_mobile.clases.clsBeTransformadores;
import com.example.cuee_mobile.clases.clsBeUsuarios_por_ruta;
import com.example.cuee_mobile.clases.clsBeUsuarios_servicio;
import com.example.cuee_mobile.controladores.MainActivity;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.modelos.ServicioInsModel;
import com.example.cuee_mobile.modelos.institucion.InstitucionDetalleModel;
import com.example.cuee_mobile.modelos.institucion.InstitucionModel;
import com.example.cuee_mobile.modelos.lectura.LecturaModel;
import com.example.cuee_mobile.modelos.meses.MesProformaModel;
import com.example.cuee_mobile.modelos.meses.MoraPagadaModel;
import com.example.cuee_mobile.modelos.meses.MoraProformaModel;
import com.example.cuee_mobile.modelos.mnt.ContadoresModel;
import com.example.cuee_mobile.modelos.mnt.ParametrosModel;
import com.example.cuee_mobile.modelos.mnt.RenglonesModel;
import com.example.cuee_mobile.modelos.mnt.TecnicoModel;
import com.example.cuee_mobile.modelos.mnt.TransformadorModel;
import com.example.cuee_mobile.modelos.reporte.PagosDetModel;
import com.example.cuee_mobile.modelos.ruta.RutaLecturaModel;
import com.example.cuee_mobile.modelos.ruta.RutaTecnicoModel;
import com.example.cuee_mobile.modelos.ruta.UsuariosRutaModel;
import com.example.cuee_mobile.modelos.usuario.UsrServicioModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private ParametrosModel parametro;
    private ContadoresModel contador;
    private MesProformaModel mesProf;
    private MoraPagadaModel moraPg;
    private MoraProformaModel moraProf;
    private PagosDetModel pagoDet;
    private LecturaModel lectura;
    private RenglonesModel renglon;
    private TransformadorModel transformador;
    private UsrServicioModel usrServicio;
    private ServicioInsModel srInstalado;

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

        setModels();
        setHandlers();
    }

    private void setModels() {
        try {
            institucion = new InstitucionModel(this, Con, db);
            insDetalle = new InstitucionDetalleModel(this, Con, db);
            rutaLectura = new RutaLecturaModel(this, Con, db);
            tecnico = new TecnicoModel(this, Con, db);
            rutaTec = new RutaTecnicoModel(this, Con, db);
            rutaUsr = new UsuariosRutaModel(this, Con, db);
            parametro = new ParametrosModel(this, Con, db);
            contador = new ContadoresModel(this, Con, db);
            mesProf = new MesProformaModel(this, Con, db);
            moraPg = new MoraPagadaModel(this, Con, db);
            moraProf = new MoraProformaModel(this, Con, db);
            pagoDet = new PagosDetModel(this, Con, db);
            lectura = new LecturaModel(this, Con, db);
            renglon = new RenglonesModel(this, Con, db);
            transformador = new TransformadorModel(this, Con, db);
            usrServicio = new UsrServicioModel(this, Con, db);
            srInstalado = new ServicioInsModel(this, Con, db);
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void setHandlers() {
        btnRecibir.setOnClickListener(view -> {
            dialogo("Recepción de datos", "¿Está seguro de iniciar la recepción de datos?", 1);
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
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getInstitucion() {
        msjProg = "Procesando institución...";
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

                                institucion.getLista("WHERE IdInstitucion = "+obj.getIdInstitucion());

                                if (institucion.lista.size() == 0) {
                                    institucion.guardar(obj);
                                }
                            }
                        }
                        getInstitucionDetalle();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeInstitucion>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getInstitucionDetalle() {
        msjProg = "Procesando detalle institución...";
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

                                insDetalle.getLista("WHERE IdInstitucion = "+obj.IdInstitucion+" AND IdPeriodoParametros = "+obj.IdPeriodoParametros);

                                if (insDetalle.lista.size() == 0) {
                                    insDetalle.guardar(obj);
                                }
                            }
                        }
                        getRutaLectura();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeInstitucion_detalle>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getRutaLectura() {
        msjProg = "Procesando rutas lectura...";
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

                                rutaLectura.getLista("WHERE IdRuta = "+obj.IdRuta);

                                if (rutaLectura.lista.size() == 0) {
                                    rutaLectura.guardar(obj);
                                }
                            }
                        }
                    }
                    getTecnicos();
                }
                @Override
                public void onFailure(Call<List<clsBeRuta_lectura>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getTecnicos() {
        msjProg = "Procesando tecnicos...";
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
                                tecnico.getLista("WHERE IdTecnico = "+obj.IdTecnico);

                                if (tecnico.lista.size() == 0) {
                                    tecnico.guardar(obj);
                                }
                            }
                        }
                        getRutaTecnico();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeTecnicos>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getRutaTecnico() {
        msjProg = "Procesando tecnicos por ruta...";
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
                                rutaTec.getLista("WHERE IdRutaTecnico = "+obj.IdRutaTecnico);

                                if (rutaTec.lista.size() == 0) {
                                    rutaTec.guardar(obj);
                                }
                            }
                        }
                        getUsuariosRuta();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeRuta_tecnico>> call, Throwable t) {cancelarPeticion(call);}
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getUsuariosRuta() {
        msjProg = "Procesando usuarios por ruta...";
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
                                rutaUsr.getLista("WHERE IdRuta ="+obj.IdRuta+" AND IdItinerario ="+obj.IdItinerario+" AND IdUsuarioServicio ="+obj.IdUsuarioServicio);

                                if (rutaUsr.lista.size() == 0) {
                                    rutaUsr.guardar(obj);
                                }

                            }
                        }
                        getParametros();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeUsuarios_por_ruta>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getParametros() {
        msjProg = "Procesando parametros...";
        actualizaProgress();

        try {
            Parametro cliente = retrofit.CrearServicio(Parametro.class);
            Call<List<clsBeParametros>> call = cliente.getParametros();

            call.enqueue(new Callback<List<clsBeParametros>>() {
                @Override
                public void onResponse(Call<List<clsBeParametros>> call, Response<List<clsBeParametros>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeParametros> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeParametros obj:lista) {
                                parametro.getLista("WHERE idparametro = "+obj.idparametro);

                                if (parametro.lista.size() == 0) {
                                    parametro.guardar(obj);
                                }
                            }
                        }
                        getContadores();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeParametros>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getContadores() {
        msjProg = "Procesando contadores...";
        actualizaProgress();

        try {
            Contador cliente = retrofit.CrearServicio(Contador.class);
            Call<List<clsBeContadores>> call = cliente.getContadores();

            call.enqueue(new Callback<List<clsBeContadores>>() {
                @Override
                public void onResponse(Call<List<clsBeContadores>> call, Response<List<clsBeContadores>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeContadores> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeContadores obj:lista) {
                                contador.getLista("WHERE IdContador = '"+obj.IdContador+"'");

                                if (contador.lista.size() == 0) {
                                    contador.guardar(obj);
                                }
                            }
                        }
                    }
                    getLectura();
                }
                @Override
                public void onFailure(Call<List<clsBeContadores>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getLectura() {
        msjProg = "Procesando lecturas...";
        actualizaProgress();

        try {
            Lectura cliente = retrofit.CrearServicio(Lectura.class);
            Call<List<clsBeLectura>> call = cliente.getLectura();

            call.enqueue(new Callback<List<clsBeLectura>>() {
                @Override
                public void onResponse(Call<List<clsBeLectura>> call, Response<List<clsBeLectura>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeLectura> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeLectura obj:lista) {
                                lectura.getLista("WHERE IdLectura = "+obj.IdLectura);

                                if (lectura.lista.size() == 0) {
                                    lectura.guardar(obj, true);
                                }
                            }
                        }
                        getRenglones();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeLectura>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getRenglones() {
        msjProg = "Procesando renglones...";
        actualizaProgress();

        try {
            Renglon cliente = retrofit.CrearServicio(Renglon.class);
            Call<List<clsBeRenglones>> call = cliente.getRenglones();

            call.enqueue(new Callback<List<clsBeRenglones>>() {
                @Override
                public void onResponse(Call<List<clsBeRenglones>> call, Response<List<clsBeRenglones>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeRenglones> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeRenglones obj:lista) {
                                renglon.getLista("WHERE Idrenglon ="+obj.Idrenglon);

                                if (renglon.lista.size() == 0) {
                                    renglon.guardar(obj);
                                }
                            }
                        }
                        getTransformadores();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeRenglones>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getTransformadores() {
        msjProg = "Procesando transformadores...";
        actualizaProgress();

        try {
            Transformador cliente = retrofit.CrearServicio(Transformador.class);
            Call<List<clsBeTransformadores>> call = cliente.getTransformadores();

            call.enqueue(new Callback<List<clsBeTransformadores>>() {
                @Override
                public void onResponse(Call<List<clsBeTransformadores>> call, Response<List<clsBeTransformadores>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeTransformadores> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeTransformadores obj:lista) {
                                transformador.getLista("WHERE IdTransformador ="+ obj.IdTransformador);

                                if (transformador.lista.size() == 0) {
                                    transformador.guardar(obj);
                                }
                            }
                        }
                        getUsuarioServicio();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeTransformadores>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getUsuarioServicio() {
        msjProg = "Procesando usuario servicio...";
        actualizaProgress();

        try {
            UsuarioServicio cliente = retrofit.CrearServicio(UsuarioServicio.class);
            Call<List<clsBeUsuarios_servicio>> call = cliente.getUsuariosServicio();

            call.enqueue(new Callback<List<clsBeUsuarios_servicio>>() {
                @Override
                public void onResponse(Call<List<clsBeUsuarios_servicio>> call, Response<List<clsBeUsuarios_servicio>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeUsuarios_servicio> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeUsuarios_servicio obj:lista) {
                                usrServicio.getLista("WHERE IdUsuarioServicio = "+obj.IdUsuarioServicio);

                                if (usrServicio.lista.size() == 0) {
                                    usrServicio.guardar(obj);
                                }
                            }
                        }
                        getServiciosInstalados();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeUsuarios_servicio>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getServiciosInstalados() {
        msjProg = "Procesando servicios instalados...";
        actualizaProgress();

        try {
            ServiciosIns cliente = retrofit.CrearServicio(ServiciosIns.class);
            Call<List<clsBeServicios_instalado>> call = cliente.getServiciosInstalados();

            call.enqueue(new Callback<List<clsBeServicios_instalado>>() {
                @Override
                public void onResponse(Call<List<clsBeServicios_instalado>> call, Response<List<clsBeServicios_instalado>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeServicios_instalado> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeServicios_instalado obj:lista) {
                                srInstalado.getLista("WHERE IdInstalacion = "+obj.IdInstalacion);

                                if (srInstalado.lista.size() == 0) {
                                    srInstalado.guardar(obj);
                                }
                            }
                        }
                        terminaRecepcion();
                        startActivity(new Intent(ComApi.this, MainActivity.class));
                        finish();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeServicios_instalado>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getMesesProforma() {
        msjProg = "Procesando meses proforma...";
        actualizaProgress();

        try {
            MesProforma cliente = retrofit.CrearServicio(MesProforma.class);
            Call<List<clsBeMeses_proforma>> call = cliente.getMesProforma();

            call.enqueue(new Callback<List<clsBeMeses_proforma>>() {
                @Override
                public void onResponse(Call<List<clsBeMeses_proforma>> call, Response<List<clsBeMeses_proforma>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeMeses_proforma> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeMeses_proforma obj:lista) {
                                mesProf.guardar(obj);
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeMeses_proforma>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getMesesMoraProforma() {
        msjProg = "Procesando meses mora proforma...";
        actualizaProgress();

        try {
            MesProforma cliente = retrofit.CrearServicio(MesProforma.class);
            Call<List<clsBeMeses_mora_proforma>> call = cliente.getMesesMoraProforma();

            call.enqueue(new Callback<List<clsBeMeses_mora_proforma>>() {
                @Override
                public void onResponse(Call<List<clsBeMeses_mora_proforma>> call, Response<List<clsBeMeses_mora_proforma>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeMeses_mora_proforma> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeMeses_mora_proforma obj:lista) {
                                moraProf.guardar(obj);
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeMeses_mora_proforma>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getMesesMoraPagada() {
        msjProg = "Procesando meses mora pagada...";
        actualizaProgress();

        try {
            MesProforma cliente = retrofit.CrearServicio(MesProforma.class);
            Call<List<clsBeMeses_mora_pagada>> call = cliente.getMesesMoraPagada();

            call.enqueue(new Callback<List<clsBeMeses_mora_pagada>>() {
                @Override
                public void onResponse(Call<List<clsBeMeses_mora_pagada>> call, Response<List<clsBeMeses_mora_pagada>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeMeses_mora_pagada> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeMeses_mora_pagada obj:lista) {
                                moraPg.guardar(obj);
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeMeses_mora_pagada>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getPagosDetRep() {
        msjProg = "Procesando detalle de pagos...";
        actualizaProgress();

        try {
            Reporte cliente = retrofit.CrearServicio(Reporte.class);
            Call<List<clsBePagos_detalle_rep>> call = cliente.getPagosDet();

            call.enqueue(new Callback<List<clsBePagos_detalle_rep>>() {
                @Override
                public void onResponse(Call<List<clsBePagos_detalle_rep>> call, Response<List<clsBePagos_detalle_rep>> response) {
                    if (response.isSuccessful()) {
                        List<clsBePagos_detalle_rep> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBePagos_detalle_rep obj:lista) {
                                pagoDet.guardar(obj);
                            }
                        }
                    }
                    terminaRecepcion();
                }
                @Override
                public void onFailure(Call<List<clsBePagos_detalle_rep>> call, Throwable t) {

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

    private void dialogo(String titulo, String msg, int accion) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setPositiveButton("Si", (dialog1, id) -> {
            if (accion == 1) {
                recibirDatos();
            }
        });

        dialog.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog12, id) -> {
        });

        dialog.show();
    }

    private void terminaRecepcion() {
        ocupado = false;
        relPrg.setVisibility(View.GONE);
        btnRecibir.setVisibility(View.VISIBLE);
        btnEnviar.setEnabled(true);
    }

    private void cancelarPeticion(@NonNull Call call) {
        call.cancel();
        Toast.makeText(ComApi.this, "Problemas de conexión, intentelo de nuevo", Toast.LENGTH_LONG).show();
        terminaRecepcion();
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