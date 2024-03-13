package com.example.cuee_mobile.controladores.comunicacion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.api.servicios.Catalogo;
import com.example.cuee_mobile.api.servicios.Color;
import com.example.cuee_mobile.api.servicios.Contador;
import com.example.cuee_mobile.api.servicios.Correlativo;
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
import com.example.cuee_mobile.clases.clsBeCorrelativo_proforma;
import com.example.cuee_mobile.clases.clsBeErrorResponse;
import com.example.cuee_mobile.clases.clsBeInstitucion;
import com.example.cuee_mobile.clases.clsBeInstitucion_detalle;
import com.example.cuee_mobile.clases.clsBeLectura;
import com.example.cuee_mobile.clases.clsBeMarcas;
import com.example.cuee_mobile.clases.clsBeMeses_mora_pagada;
import com.example.cuee_mobile.clases.clsBeMeses_mora_proforma;
import com.example.cuee_mobile.clases.clsBeMeses_pago;
import com.example.cuee_mobile.clases.clsBeMeses_proforma;
import com.example.cuee_mobile.clases.clsBePagos_detalle_rep;
import com.example.cuee_mobile.clases.clsBeParametros;
import com.example.cuee_mobile.clases.clsBeProforma;
import com.example.cuee_mobile.clases.clsBeRazon_no_lectura;
import com.example.cuee_mobile.clases.clsBeRenglones;
import com.example.cuee_mobile.clases.clsBeRutaSincronizacion;
import com.example.cuee_mobile.clases.clsBeRuta_lectura;
import com.example.cuee_mobile.clases.clsBeRuta_tecnico;
import com.example.cuee_mobile.clases.clsBeServicios_instalado;
import com.example.cuee_mobile.clases.clsBeTecnicos;
import com.example.cuee_mobile.clases.clsBeTransformadores;
import com.example.cuee_mobile.clases.clsBeUsuario_sin_lectura;
import com.example.cuee_mobile.clases.clsBeUsuarios_por_ruta;
import com.example.cuee_mobile.clases.clsBeUsuarios_servicio;
import com.example.cuee_mobile.controladores.MainActivity;
import com.example.cuee_mobile.controladores.Menu;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.modelos.CorrelativoModel;
import com.example.cuee_mobile.modelos.MesesPagoModel;
import com.example.cuee_mobile.modelos.ProformaModel;
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
import com.example.cuee_mobile.modelos.mnt.RazonNoCierreModel;
import com.example.cuee_mobile.modelos.mnt.RenglonesModel;
import com.example.cuee_mobile.modelos.mnt.TecnicoModel;
import com.example.cuee_mobile.modelos.mnt.TransformadorModel;
import com.example.cuee_mobile.modelos.reporte.PagosDetModel;
import com.example.cuee_mobile.modelos.ruta.RutaLecturaModel;
import com.example.cuee_mobile.modelos.ruta.RutaTecnicoModel;
import com.example.cuee_mobile.modelos.ruta.UsuariosRutaModel;
import com.example.cuee_mobile.modelos.usuario.UsinLecturaModel;
import com.example.cuee_mobile.modelos.usuario.UsrServicioModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
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
    private TextView lblPgr, lblVersion;
    private ImageView btnRegresar;
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
    private RazonNoCierreModel rNoCierre;
    private ColorModel color;
    private MarcaModel marca;
    private CorrelativoModel correlativo;
    private ProformaModel proformaModel;
    private UsinLecturaModel pendientesModel;
    private MesesPagoModel mspagos;
    private String msjProg = "";
    private int IdRuta, IdItinerario, idx, cant;
    private clsBeRuta_lectura objRutaLectura = null;
    private clsBeErrorResponse errorResponse;
    private boolean enviando = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_api);
        super.SetBase();

        btnRecibir = findViewById(R.id.btnRecibir);
        btnEnviar = findViewById(R.id.btnEnviar);
        relPrg = findViewById(R.id.relPrg);
        lblPgr = findViewById(R.id.lblPgr);
        lblVersion = findViewById(R.id.lblVersion);
        txtRuta = findViewById(R.id.txtRuta);
        txtItinerario = findViewById(R.id.txtItinerario);
        txtUrl = findViewById(R.id.txtUrl);
        btnRegresar = findViewById(R.id.btnRegresar);

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
            correlativo = new CorrelativoModel(this, Con, db);
            mspagos = new MesesPagoModel(this, Con, db);
            rNoCierre = new RazonNoCierreModel(this, Con, db);
            proformaModel = new ProformaModel(this, Con, db);
            pendientesModel = new UsinLecturaModel(this, Con, db);

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

        btnRegresar.setOnClickListener(v -> regresar());
    }

    private void cargar() {
        try {
            setModels();
            rutaLectura.getLinea();
            objRutaLectura = rutaLectura.objRutaLec;

            getUrlApi();
            setHandlers();
            existeDatosEnvio();
            lblVersion.setText("Versión" + gl.version+" / "+gl.vFecha);
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

                if (objRutaLectura == null) {
                    btnRecibir.setVisibility(View.VISIBLE);
                    txtRuta.setEnabled(true);
                    txtItinerario.setEnabled(true);
                }

                btnEnviar.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private boolean pendientesEnvio() {
        boolean pendiente = false;
        String mensaje = "";
        try {
            lectura.getLista("WHERE StatCom = 0");
            if (lectura.filas > 0)  {
                mensaje +="- Tiene facturas pendientes por enviar.\n";
                pendiente = true;
            }

            proformaModel.getLista("WHERE StatCom = 0");
            if (proformaModel.lista.size() > 0)  {

                for (clsBeProforma obj:proformaModel.lista) {
                    obj.detalle = proformaModel.getDetalle(obj.idproforma);
                    obj.MesesProforma = proformaModel.getMesesProforma(obj.idproforma);
                }
                mensaje +="- Tiene proformas pendientes por enviar.\n";
                pendiente = true;
            }

            pendientesModel.getLista(" WHERE StatCom = 0");
            if (pendientesModel.lista.size() > 0)  {
                mensaje +="- Tiene razones de no lectura pendientes por enviar.\n";
                pendiente = true;
            }

            correlativo.getLista(" WHERE StatCom = 0");
            /*if (correlativo.lista.size() > 0)  {
                mensaje +="- Correlativo proforma pendiente por enviar.\n";
                pendiente = true;
            }*/

            if (!mensaje.isEmpty()) helper.toast(mensaje);

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
            } else if (!gl.urlApi.equals(txtUrl.getText().toString())) {
                gl.urlApi = "";
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
        try {
            relPrg.setVisibility(View.VISIBLE);
            btnEnviar.setEnabled(false);
            txtRuta.setEnabled(false);
            txtItinerario.setEnabled(false);
            lblPgr.setText("Enviando...");

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

    private void validaRutaItinerario() {
        msjProg = "Validando ruta e itinerario...";
        actualizaProgress();

        try {
            Ruta cliente = retrofit.CrearServicio(Ruta.class);
            Call<Boolean> call = cliente.validaRutaItinerario(IdRuta, IdItinerario);

            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (response.isSuccessful()) {
                        boolean existe = response.body();

                        if (existe) {
                            getFechaServidor();
                        } else {
                            cancelarPeticion(call);
                            helper.toast("Ruta o itinerario incorrecto.");
                        }
                    } else {
                        mostrarError(response, call, "validaRutaItinerario");
                    }
                }
                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
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
                            helper.toast("La fecha del servidor o HH, no es válida.");
                            cancelarPeticion(call);
                        }
                    } else {
                        mostrarError(response, call, "getFechaServidor");
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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
                    } else {
                        mostrarError(response, call, "getColor");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeColor>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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
                    } else {
                        mostrarError(response, call, "getMarca");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeMarcas>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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
                    } else {
                        mostrarError(response, call, "getInstitucion");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeInstitucion>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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
                    } else {
                        mostrarError(response, call, "getInstitucionDetalle");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeInstitucion_detalle>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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
                        getTecnicos();
                    } else {
                        mostrarError(response, call, "getRutaLectura");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeRuta_lectura>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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
                    } else {
                        mostrarError(response, call, "getTecnicos");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeTecnicos>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

                    cancelarPeticion(call);;
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
                    } else {
                        mostrarError(response, call, "getRutaTecnico");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeRuta_tecnico>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

                    cancelarPeticion(call);
                }
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
                    } else {
                        mostrarError(response, call, "getUsuariosRuta");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeUsuarios_por_ruta>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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
                    } else {
                        mostrarError(response, call, "getParametros");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeParametros>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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

                        getLectura();
                    } else {
                        mostrarError(response, call, "getContadores");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeContadores>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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
                        mostrarError(response, call, "getLectura");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeLectura>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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
                    } else {
                        mostrarError(response, call, "getRenglones");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeRenglones>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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
                    } else {
                        mostrarError(response, call, "getTransformadores");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeTransformadores>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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
                    } else {
                        mostrarError(response, call, "getUsuarioServicio");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeUsuarios_servicio>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

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

                        getMesesPago();
                    } else {
                        mostrarError(response, call, "getServiciosInstalados");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeServicios_instalado>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getMesesPago() {
        msjProg = "Procesando meses pagados...";
        actualizaProgress();

        try {
            Catalogo cliente = retrofit.CrearServicio(Catalogo.class);
            Call<List<clsBeMeses_pago>> call = cliente.get_MesesPago(IdRuta, IdItinerario);

            call.enqueue(new Callback<List<clsBeMeses_pago>>() {
                @Override
                public void onResponse(Call<List<clsBeMeses_pago>> call, Response<List<clsBeMeses_pago>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeMeses_pago> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeMeses_pago obj:lista) {
                                mspagos.getLista("WHERE IdMesPago = "+obj.IdMesPago);

                                if (mspagos.lista.size() == 0) {
                                    mspagos.guardar(obj);
                                }
                            }
                        }

                        getMesesMoraPagada();
                    } else {
                        mostrarError(response, call, "getMesesPago");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeMeses_pago>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

                    cancelarPeticion(call);
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
            Call<List<clsBeMeses_mora_pagada>> call = cliente.getMesesMoraPagada(IdRuta, IdItinerario);

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

                        getRazonNoLectura();
                    } else {
                        mostrarError(response, call, "getMesesMoraPagada");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeMeses_mora_pagada>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper.msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper.msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper.msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getRazonNoLectura() {
        msjProg = "Procesando razones no lectura...";
        actualizaProgress();

        try {
            Catalogo cliente = retrofit.CrearServicio(Catalogo.class);
            Call<List<clsBeRazon_no_lectura>> call = cliente.get_razon_no_lectura();

            call.enqueue(new Callback<List<clsBeRazon_no_lectura>>() {
                @Override
                public void onResponse(Call<List<clsBeRazon_no_lectura>> call, Response<List<clsBeRazon_no_lectura>> response) {
                    response.body();
                    if (response.isSuccessful()) {
                        List<clsBeRazon_no_lectura> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeRazon_no_lectura obj:lista) {
                                rNoCierre.getLista(" WHERE IdRazonNoLectura = "+obj.IdRazonNoLectura);

                                if (rNoCierre.lista.size() == 0) {
                                    rNoCierre.guardar(obj);
                                }
                            }
                        }

                        getCorrelativoProforma();
                    } else {
                        mostrarError(response, call, "getRazonNoLectura");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeRazon_no_lectura>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper.msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper.msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper.msgbox("Ocurrió un error: \n" + t.getMessage());
                    }

                    cancelarPeticion(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    public void getCorrelativoProforma() {
        msjProg = "Procesando correlativos proforma...";
        actualizaProgress();

        try {
            Correlativo cliente = retrofit.CrearServicio(Correlativo.class);
            Call<List<clsBeCorrelativo_proforma>> call = cliente.get_CorrelativosProforma_by_IdRuta(IdRuta);


            call.enqueue(new Callback<List<clsBeCorrelativo_proforma>>() {
                @Override
                public void onResponse(Call<List<clsBeCorrelativo_proforma>> call, Response<List<clsBeCorrelativo_proforma>> response) {
                    if (response.isSuccessful()) {
                        List<clsBeCorrelativo_proforma> lista = response.body();

                        if (lista != null && lista.size() > 0) {
                            for (clsBeCorrelativo_proforma obj:lista) {
                                correlativo.getLista("WHERE idCorrelativoProforma = "+obj.idCorrelativoProforma);

                                if (correlativo.lista.size() == 0) {
                                    correlativo.guardar(obj);
                                }
                            }
                        }

                        //Pensar como mejorar el proceso
                        terminaRecepcion();
                        helper.toast("Recepción completa");

                        if (gl.ruta == null || gl.ruta.IdRuta == 0) {
                            finish();
                            startActivity(new Intent(ComApi.this, MainActivity.class));
                        } else {
                            finish();
                        }
                    } else {
                        mostrarError(response, call, "getCorrelativoProforma");
                    }
                }
                @Override
                public void onFailure(Call<List<clsBeCorrelativo_proforma>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper.msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper.msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper.msgbox("Error: " + t.getMessage());
                    }
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
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    }
                    cancelarPeticion(call);
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
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    }
                    cancelarPeticion(call);
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
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("¡Connection Timeout!\n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    }
                    cancelarPeticion(call);
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
                validaRutaItinerario();

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

            if (lectura.lista.size() > 0) {
                enviarLecturas(lectura.lista.get(idx));

            } else if (proformaModel.lista.size() > 0) {
                enviaProforma(proformaModel.lista.get(idx));

            } else if (pendientesModel.lista.size() > 0) {
                enviaRazonesNoLectura(pendientesModel.lista.get(idx));
            } else if (correlativo.lista.size() > 0) {
                enviaCorrelativo(correlativo.lista.get(idx));
            }

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
                        mostrarError(response, call, "enviarLecturas");
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("Connection Timeout \n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox(t.getMessage());
                    }
                    cancelarEnvio(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void enviaProforma(clsBeProforma obj) {
        msjProg = "Enviando proforma(s) "+cant+"/"+proformaModel.lista.size()+" ...";
        actualizaProgress();
        try {

            Catalogo cliente = retrofit.CrearServicio(Catalogo.class);
            Call call = cliente.guardarProforma(obj);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        proformaModel.actualizaEstado(obj.idproforma);
                        envioCompletoProformas();
                    } else {
                        mostrarError(response, call, "enviaProforma");
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper.msgbox("Connection Timeout \n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper.msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper.msgbox(t.getMessage());
                    }
                    cancelarEnvio(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void enviaRazonesNoLectura(clsBeUsuario_sin_lectura obj) {
        msjProg = "Enviando razones no lectura "+cant+"/"+pendientesModel.lista.size()+" ...";
        actualizaProgress();
        try {

            Catalogo cliente = retrofit.CrearServicio(Catalogo.class);
            Call call = cliente.guardar(obj);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        pendientesModel.actualizaEstado(obj.IdUsuarioSinLectura);
                        envioCompletoRazones();
                    } else {
                        mostrarError(response, call, "enviaRazonesNoLectura");
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("Connection Timeout \n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox(t.getMessage());
                    }
                    cancelarEnvio(call);
                }
            });
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void enviaCorrelativo(clsBeCorrelativo_proforma obj) {
        msjProg = "Enviando correlativo proforma "+cant+"/"+correlativo.lista.size()+" ...";
        actualizaProgress();
        try {
            obj.fec_mod = du.getFechaCompleta();
            obj.user_mod = ""+gl.tecnico.IdTecnico;
            Catalogo cliente = retrofit.CrearServicio(Catalogo.class);
            Call call = cliente.actualizar_correlativo(obj);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        //correlativo.actualizaEstado(obj.idCorrelativoProforma);
                        envioCorrelativoCompleto();
                    } else {
                        mostrarError(response, call, "enviaCorrelativo");
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        helper. msgbox("Connection Timeout \n\n" + t.getMessage());
                    } else if (t instanceof ConnectException) {
                        helper. msgbox("¡Problemas de conexión!\nInténtelo de nuevo\n\n" + t.getMessage());
                    } else {
                        helper. msgbox(t.getMessage());
                    }
                    cancelarEnvio(call);
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
                lectura.lista.clear();

                if (proformaModel.lista.size() > 0) {
                    idx = 0;
                    cant = 1;
                    enviaProforma(proformaModel.lista.get(idx));
                } else {
                    terminaEnvio();
                }
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

    private void envioCompletoProformas() {
        try {
            if (cant == proformaModel.lista.size()) {
                proformaModel.lista.clear();

                if (pendientesModel.lista.size() > 0) {
                    idx = 0;
                    cant = 1;
                    enviaRazonesNoLectura(pendientesModel.lista.get(idx));
                } else {
                    terminaEnvio();
                }
            } else {
                idx++;
                cant++;
                enviaProforma(proformaModel.lista.get(idx));
            }
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void envioCompletoRazones() {
        try {
            if (cant == pendientesModel.lista.size()) {
                pendientesModel.lista.clear();

                if (correlativo.lista.size() > 0) {
                    idx = 0;
                    cant = 1;
                    enviaCorrelativo(correlativo.lista.get(idx));
                } else {
                    terminaEnvio();
                }
            } else {
                idx++;
                cant++;
                enviaRazonesNoLectura(pendientesModel.lista.get(idx));
            }
        } catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void envioCorrelativoCompleto() {
        try {
            if (cant == correlativo.lista.size()) {
                helper.toast("Completo");
                terminaEnvio();
            } else {
                idx++;
                cant++;
                enviaCorrelativo(correlativo.lista.get(idx));
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
                enviando = false;
                recibirDatos();
            } else if (accion == 2) {
                enviando = true;
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

        if (gl.cierreRuta) {
            catalogo.eliminarDatosTalbas();
            objRutaLectura = null;
            gl.ruta = null;
            gl.itinerario = 0;

            txtRuta.setText("");
            txtRuta.setEnabled(true);
            txtItinerario.setText("");
            txtItinerario.setEnabled(true);
            txtRuta.requestFocus();

            existeDatosEnvio();
        } else {
            startActivity(new Intent(this, Menu.class));
            super.finish();
        }
    }

    private void cancelarPeticion(@NonNull Call call) {
        call.cancel();
        terminaRecepcion();
    }

    private void cancelarEnvio(Call call) {
        relPrg.setVisibility(View.GONE);
        btnEnviar.setVisibility(View.VISIBLE);
        btnEnviar.setEnabled(true);
        call.cancel();
    }
    private void mostrarError(Response response, Call call, String metodo) {
        errorResponse = helper.setError(response);
        helper.msgbox("Método HH: "+ metodo + "\n" + errorResponse.CodeError + "\n\n" +errorResponse.ErrorMessage);

        if (enviando) {
            cancelarEnvio(call);
        } else {
            cancelarPeticion(call);
            helper.toast("Recepción incompleta");
        }
    }

    private void actualizaProgress() {
        try {
            runOnUiThread(() -> lblPgr.setText(msjProg));
        }   catch (Exception e) {
            helper.msgbox(Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingClass()).getName() +" - "+ e);
        }
    }

    private void regresar() {
        if (gl.cierreRuta) {
            if (objRutaLectura != null) {
                startActivity(new Intent(this, Menu.class));
                super.finish();
            }
        } else {
            super.finish();
        }
    }

    @Override
    public void onBackPressed() {
        regresar();
    }
}