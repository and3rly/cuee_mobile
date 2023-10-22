package com.example.cuee_mobile.controladores.comunicacion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.apache.commons.io.FileUtils;

import androidx.annotation.NonNull;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.api.servicios.Color;
import com.example.cuee_mobile.api.servicios.Contador;
import com.example.cuee_mobile.api.servicios.Institucion;
import com.example.cuee_mobile.api.servicios.Lectura;
import com.example.cuee_mobile.api.servicios.Marca;
import com.example.cuee_mobile.api.servicios.MesProforma;
import com.example.cuee_mobile.api.servicios.Parametro;
import com.example.cuee_mobile.api.servicios.Renglon;
import com.example.cuee_mobile.api.servicios.Reporte;
import com.example.cuee_mobile.api.servicios.Ruta;
import com.example.cuee_mobile.api.servicios.ServiciosIns;
import com.example.cuee_mobile.api.servicios.Tecnico;
import com.example.cuee_mobile.api.servicios.Transformador;
import com.example.cuee_mobile.api.servicios.UsuarioServicio;
import com.example.cuee_mobile.clases.clsBeColor;
import com.example.cuee_mobile.clases.clsBeContadores;
import com.example.cuee_mobile.clases.clsBeInstitucion;
import com.example.cuee_mobile.clases.clsBeInstitucion_detalle;
import com.example.cuee_mobile.clases.clsBeLectura;
import com.example.cuee_mobile.clases.clsBeMarcas;
import com.example.cuee_mobile.clases.clsBeMeses_mora_pagada;
import com.example.cuee_mobile.clases.clsBeMeses_mora_proforma;
import com.example.cuee_mobile.clases.clsBeMeses_proforma;
import com.example.cuee_mobile.clases.clsBePagos_detalle_rep;
import com.example.cuee_mobile.clases.clsBeParametros;
import com.example.cuee_mobile.clases.clsBeRenglones;
import com.example.cuee_mobile.clases.clsBeRutaSincronizacion;
import com.example.cuee_mobile.clases.clsBeRuta_lectura;
import com.example.cuee_mobile.clases.clsBeRuta_tecnico;
import com.example.cuee_mobile.clases.clsBeServicios_instalado;
import com.example.cuee_mobile.clases.clsBeTecnicos;
import com.example.cuee_mobile.clases.clsBeTransformadores;
import com.example.cuee_mobile.clases.clsBeUsuarios_por_ruta;
import com.example.cuee_mobile.clases.clsBeUsuarios_servicio;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.modelos.RutaSincModel;
import com.example.cuee_mobile.modelos.ServicioInsModel;
import com.example.cuee_mobile.modelos.institucion.InstitucionDetalleModel;
import com.example.cuee_mobile.modelos.institucion.InstitucionModel;
import com.example.cuee_mobile.modelos.lectura.LecturaModel;
import com.example.cuee_mobile.modelos.meses.MesProformaModel;
import com.example.cuee_mobile.modelos.meses.MoraPagadaModel;
import com.example.cuee_mobile.modelos.meses.MoraProformaModel;
import com.example.cuee_mobile.modelos.mnt.ColorModel;
import com.example.cuee_mobile.modelos.mnt.ContadoresModel;
import com.example.cuee_mobile.modelos.mnt.MarcaModel;
import com.example.cuee_mobile.modelos.mnt.ParametrosModel;
import com.example.cuee_mobile.modelos.mnt.RenglonesModel;
import com.example.cuee_mobile.modelos.mnt.TecnicoModel;
import com.example.cuee_mobile.modelos.mnt.TransformadorModel;
import com.example.cuee_mobile.modelos.reporte.PagosDetModel;
import com.example.cuee_mobile.modelos.ruta.RutaLecturaModel;
import com.example.cuee_mobile.modelos.ruta.RutaTecnicoModel;
import com.example.cuee_mobile.modelos.ruta.UsuariosRutaModel;
import com.example.cuee_mobile.modelos.usuario.UsrServicioModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComApi extends PBase {
    private RelativeLayout btnRecibir, btnEnviar, relPrg;
    private EditText txtRuta, txtItinerario, txtUrl;
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
    private RutaSincModel runtaSinc;
    private ColorModel color;
    private MarcaModel marca;
    private String msjProg = "";
    private int IdRuta, IdItinerario, idx, cant;

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
        txtItinerario = findViewById(R.id.txtItinerario);
        txtUrl = findViewById(R.id.txtUrl);

        cargar();
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
            runtaSinc = new RutaSincModel(this, Con, db);
            color = new ColorModel(this, Con, db);
            marca = new MarcaModel(this, Con, db);

            txtRuta.requestFocus();
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void setHandlers() {
        btnRecibir.setOnClickListener(view -> {

            if (txtRuta.getText().toString().isEmpty()) {
                helper.toast("Ingrese ruta.");
                return;
            } else {
                IdRuta = Integer.parseInt(txtRuta.getText().toString());
            }

            if (txtItinerario.getText().toString().isEmpty()) {
                helper.toast("Ingrese itinerario.");
                return;
            } else {
                IdItinerario = Integer.parseInt(txtItinerario.getText().toString());
            }

            if (txtUrl.getText().toString().isEmpty()) {
                helper.toast("Ingrese url api.");
                return;
            }

            dialogo("Recepción de datos", "¿Está seguro de iniciar la recepción de datos?", 1);
        });

        btnEnviar.setOnClickListener(view -> {
            dialogo("Envio de datos", "¿Está seguro de iniciar el envio de datos?", 2);
        });
    }

    private void cargar() {
        try {
            getUrlApi();
            setModels();
            setHandlers();
            existeDatosEnvio();

            if (gl.ruta != null) {
                if (gl.ruta.IdRuta != 0) {
                    txtRuta.setText("" + gl.ruta.IdRuta);
                }

                if (gl.itinerario != 0) {
                    txtItinerario.setText("" + gl.itinerario);
                }
            }
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }
    private void existeDatosEnvio() {
        try {
            if (pendientesEnvio()) {
                btnEnviar.setVisibility(View.VISIBLE);
                btnRecibir.setVisibility(View.GONE);
                txtRuta.setEnabled(false);
                txtItinerario.setEnabled(false);
            } else {
                btnRecibir.setVisibility(View.VISIBLE);
                btnEnviar.setVisibility(View.GONE);
                txtRuta.setEnabled(true);
                txtItinerario.setEnabled(true);
            }
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private boolean pendientesEnvio() {
        boolean pendiente = false;
        try {
            lectura.getLista("WHERE StatCom = 0");
            if (lectura.filas > 0)  {
                helper.toast("Tiene lecturas pendientes de enviar.");
                pendiente = true;
            }
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }

        return pendiente;
    }

    private void recibirDatos() {
        try {

            if (gl.urlApi.isEmpty()) {
                setUrlApi();
            }

            relPrg.setVisibility(View.VISIBLE);
            btnRecibir.setVisibility(View.GONE);
            btnEnviar.setEnabled(false);
            txtRuta.setEnabled(false);
            txtItinerario.setEnabled(false);
            lblPgr.setText("Procesando...");

            if (!pendientesEnvio()) {
                catalogo.eliminarDatosTalbas();
            }

            Handler timer = new Handler();
            Runnable runner = this::AsyncCallRec;
            timer.postDelayed(runner, 500);

        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void enviarDatos() {
        String fecha = du.getFecha();
        try {

            relPrg.setVisibility(View.VISIBLE);
            btnEnviar.setEnabled(false);
            txtRuta.setEnabled(false);
            txtItinerario.setEnabled(false);
            lblPgr.setText("Enviando...");

            try {
                File f1 = new File(gl.path + "/database/db_cuee.db");
                File f2 = new File(gl.path + "/database/db_cuee_" + fecha + ".db");
                FileUtils.copyFile(f1, f2);
            } catch (Exception e) {
                helper.msgbox("No se puede generar respaldo : " + e.getMessage());
            }

            if (pendientesEnvio()) {
                Handler timer = new Handler();
                Runnable runner = this::AsyncCallSend;
                timer.postDelayed(runner, 500);
            } else {
                helper.toast("No tiene datos pendientes para enviar.");
            }

        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void setUrlApi() {
        BufferedWriter writer;
        FileWriter wfile;

        try {
            String fname = gl.path+"/cuee_api.txt";
            File archivo= new File(fname);

            if (archivo.exists()){
                archivo.delete();
            }
            gl.urlApi = txtUrl.getText().toString();

            wfile=new FileWriter(fname,true);
            writer = new BufferedWriter(wfile);
            writer.write(gl.urlApi + "\n");
            writer.close();

        } catch (Exception e) {
            helper.msgbox("Error guardarUrlApi: " + e.getMessage());
        }
    }

    private void getUrlApi() {
        gl.urlApi="";

        try {

            String pathText = gl.path + "/cuee_api.txt";
            File file1 = new File(pathText);

            if (file1.exists()) {
                FileInputStream fIn = new FileInputStream(file1);
                BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                gl.urlApi = myReader.readLine();
                myReader.close();

                txtUrl.setText(gl.urlApi);
            }

        } catch (Exception e) {
            gl.urlApi ="";
            helper.msgbox("Error getUrlApi: " + e.getMessage());
        }
    }

    private void getFechaServidor() {
        msjProg = "Comprobando fecha servidor...";
        actualizaProgress();

        try {
            Ruta cliente = retrofit.CrearServicio(Ruta.class);
            Call<String> call = cliente.getFechaServidor();

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        String fechaRes = response.body();
                        String[] fechaServidor = fechaRes.split("T", 2);
                        String fechaHH = du.getFecha();

                        if (fechaHH.equals(fechaServidor[0])) {

                            clsBeRutaSincronizacion item = new clsBeRutaSincronizacion();
                            item.Ruta = Integer.parseInt(txtRuta.getText().toString());
                            item.Fecha_carga_datos = du.getFechaCompleta();
                            item.Fecha_servidor = fechaServidor[0];
                            runtaSinc.guardar(item);

                            getColor();
                        } else {
                            cancelarPeticion(call);
                            helper.toast("Fecha del dispositivo no coincide con fecha servidor.");
                        }
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void getColor() {
        msjProg = "Procesando colores...";
        actualizaProgress();

        try {
            Color cliente = retrofit.CrearServicio(Color.class);
            Call<List<clsBeColor>> call = cliente.getColores();

            call.enqueue(new Callback<List<clsBeColor>>() {
                @Override
                public void onResponse(Call<List<clsBeColor>> call, Response<List<clsBeColor>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeColor> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeColor obj : lista) {

                                color.getLista("WHERE Idcolor = "+obj.Idcolor);

                                if (color.lista.size() == 0) {
                                    color.guardar(obj);
                                }
                            }
                        }

                        getMarca();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeColor>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void getMarca() {
        msjProg = "Procesando  marcas...";
        actualizaProgress();

        try {
            Marca cliente = retrofit.CrearServicio(Marca.class);
            Call<List<clsBeMarcas>> call = cliente.getMarcas();

            call.enqueue(new Callback<List<clsBeMarcas>>() {
                @Override
                public void onResponse(Call<List<clsBeMarcas>> call, Response<List<clsBeMarcas>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeMarcas> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeMarcas obj : lista) {

                                marca.getLista("WHERE IdMarca = "+obj.IdMarca);

                                if (marca.lista.size() == 0) {
                                    marca.guardar(obj);
                                }
                            }
                        }
                        getInstitucion();
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeMarcas>> call, Throwable t) {
                    cancelarPeticion(call);
                }
            });
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
            Call<List<clsBeRuta_lectura>> call = cliente.getRutaLectura(IdRuta);

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
            Call<List<clsBeTecnicos>> call = cliente.getTecnicos(IdRuta);

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
            Call<List<clsBeRuta_tecnico>> call = cliente.getRutaTecnico(IdRuta);

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
            Call<List<clsBeUsuarios_por_ruta>> call = cliente.getUsuariosRuta(IdRuta, IdItinerario);

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
            Call<List<clsBeLectura>> call = cliente.getLectura(IdRuta, IdItinerario);

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
                    } else {
                        helper.msgbox("");
                        terminaRecepcion();
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
            Call<List<clsBeUsuarios_servicio>> call = cliente.getUsuariosServicio(IdRuta, IdItinerario);

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
            Call<List<clsBeServicios_instalado>> call = cliente.getServiciosInstalados(IdRuta, IdItinerario);

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
                getFechaServidor();

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

    private void AsyncCallSend() {
        try {
            Executor executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                enviar();
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

    public void enviar() {
        try {
            idx = 0;
            cant = 1;
            enviarLecturas(lectura.lista.get(idx));
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void enviarLecturas(clsBeLectura obj) {
        msjProg = "Enviando lecturas "+cant+"/"+lectura.lista.size()+" ...";
        actualizaProgress();
        try {

            Lectura cliente = retrofit.CrearServicio(Lectura.class);
            Call call = cliente.guardarLectura(obj);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        lectura.actualizaEstado(obj.IdLectura);
                        envioCompletoLecutura();
                    } else {
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void envioCompletoLecutura() {
        try {
            if (cant == lectura.lista.size()) {
                helper.toast("Completo");
                lectura.lista.clear();
                terminaEnvio();
            } else {
                idx++;
                cant++;
                enviarLecturas(lectura.lista.get(idx));
            }
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void dialogo(String titulo, String msg, int accion) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setIcon(R.drawable.logo);
        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setPositiveButton("Si", (dialog1, id) -> {
            if (accion == 1) {
                recibirDatos();
            } else if (accion == 2) {
                enviarDatos();
            }
        });

        dialog.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog12, id) -> {
        });

        dialog.show();
    }

    private void terminaRecepcion() {
        relPrg.setVisibility(View.GONE);
        btnRecibir.setVisibility(View.VISIBLE);
        txtRuta.setEnabled(true);
        txtItinerario.setEnabled(true);
        btnEnviar.setEnabled(true);
    }

    private void terminaEnvio() {
        relPrg.setVisibility(View.GONE);
        existeDatosEnvio();
    }

    private void cancelarPeticion(@NonNull Call call) {
        call.cancel();
        //Toast.makeText(ComApi.this, "Problemas de conexión, intentelo de nuevo", Toast.LENGTH_LONG).show();
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