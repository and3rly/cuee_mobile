package com.example.cuee_mobile.controladores.lectura;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.clases.clsBeContadores;
import com.example.cuee_mobile.clases.clsBeCorrelativo_proforma;
import com.example.cuee_mobile.clases.clsBeInstitucion_detalle;
import com.example.cuee_mobile.clases.clsBeLectura;
import com.example.cuee_mobile.clases.clsBeLecturaImp;
import com.example.cuee_mobile.clases.clsBeMeses_pago;
import com.example.cuee_mobile.clases.clsBeMeses_proforma;
import com.example.cuee_mobile.clases.clsBeProforma;
import com.example.cuee_mobile.clases.clsBeProformaImp;
import com.example.cuee_mobile.clases.clsBeProforma_detalle;
import com.example.cuee_mobile.clases.clsBeRenglones;
import com.example.cuee_mobile.clases.clsBeServicios_instalado;
import com.example.cuee_mobile.clases.clsBeTmpProformaUs;
import com.example.cuee_mobile.clases.clsBeUsuarios_servicio;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.modelos.CorrelativoModel;
import com.example.cuee_mobile.modelos.MesesPagoModel;
import com.example.cuee_mobile.modelos.ServicioInsModel;
import com.example.cuee_mobile.modelos.institucion.InstitucionDetalleModel;
import com.example.cuee_mobile.modelos.lectura.LecturaModel;
import com.example.cuee_mobile.modelos.mnt.ContadoresModel;
import com.example.cuee_mobile.modelos.mnt.RenglonesModel;
import com.example.cuee_mobile.modelos.usuario.UsinLecturaModel;
import com.example.cuee_mobile.modelos.usuario.UsrServicioModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import static com.example.cuee_mobile.controladores.lectura.Lectura.auxLectura;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class LecturaForm extends PBase {

    private TextView lblTecnico, txtUsuario, txtContador, txtRuta, txtItinerario, lblProforma;
    private DatePicker datePicker;
    private ImageView btnRegresar, btnFecha;
    private EditText txtFecha, txtLectura, txtConsumo, txtLecKW;
    private DatePickerDialog datePickerDialog;
    private FloatingActionButton btnGuardar, btnImprimir;
    private clsBeLectura objLectura;
    private clsBeProforma proforma;
    private clsBeProforma_detalle dproforma;
    private LecturaModel lecturaModel;
    private ServicioInsModel serviciosModel;
    private ContadoresModel contadorModel;
    private MesesPagoModel mpago;
    private RenglonesModel renglonModel;
    private InstitucionDetalleModel insDetModel;
    private int dia, mes, anio, pk, IdUsuarioServicio;
    private double consumo, promedio, tmpPromedio, lecturaAnterior = 0, lecturaActual=0, vIvaDecimal = 0.12, MontoMora = 0;
    private boolean editando = false, continuar = false, correcta = false, tieneProforma = false, contadorNuevo = false;
    private clsBeLecturaImp lecturaImp;
    private clsBeProformaImp proformaImp;
    private UsrServicioModel usuarioSer;
    private String IdContadorActual = "";
    private clsBeContadores ContadorActual = null;
    private CorrelativoModel corelModel;
    private clsBeInstitucion_detalle parametros = null;
    private clsBeServicios_instalado uServicioInstalado = null;
    private clsBeCorrelativo_proforma corel = null;
    private LocalDate  FechaPagoActual, FechaUltimoPago;
    private clsBeMeses_pago objUP;
    private ArrayList<clsBeTmpProformaUs> pendientes = new ArrayList<>();
    private double tmpActualLect = 0;
    private  int IdProformaActual = 0, CantidadMesesPendientes;
    private UsinLecturaModel mdlSinLectura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura_form);
        super.SetBase();

        lblTecnico = findViewById(R.id.lblTecnico);
        lblProforma = findViewById(R.id.lblProforma);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtContador = findViewById(R.id.txtContador);
        txtRuta = findViewById(R.id.txtRuta);
        txtItinerario = findViewById(R.id.txtItinerario);
        txtFecha = findViewById(R.id.txtFecha);
        txtLectura = findViewById(R.id.txtLectura);
        txtConsumo = findViewById(R.id.txtConsumo);
        txtLecKW = findViewById(R.id.txtLecKW);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnFecha = findViewById(R.id.btnFecha);
        btnImprimir = findViewById(R.id.btnImprimir);
        datePicker = findViewById(R.id.datePicker);

        lecturaModel = new LecturaModel(this, Con, db);
        serviciosModel = new ServicioInsModel(this, Con, db);
        contadorModel = new ContadoresModel(this, Con, db);
        usuarioSer = new UsrServicioModel(this, Con, db);
        mpago = new MesesPagoModel(this, Con ,db);
        renglonModel = new RenglonesModel(this, Con, db);
        insDetModel = new InstitucionDetalleModel(this, Con, db);
        corelModel = new CorrelativoModel(this, Con, db);
        mdlSinLectura = new UsinLecturaModel(this, Con, db);

        setDatos();
        setHandlers();
    }

    private void setDatos() {
        try {
            IdUsuarioServicio = auxLectura.IdUsuarioServicio;
            ContadorActual = contadorModel.getContadorByUsuario(IdUsuarioServicio);
            IdContadorActual = ContadorActual.IdContador;
            lblTecnico.setText("Técnico: "+ gl.tecnico.Nombre);

            objUP = mpago.getUltimoPago(IdUsuarioServicio);
            FechaPagoActual = LocalDate.now();

            if (objUP != null) {
                FechaUltimoPago = LocalDate.of(objUP.anno, objUP.nomes, 1);
            }

            CantidadMesesPendientes = du.diffMeses(FechaUltimoPago, FechaPagoActual);

            txtRuta.setText("No. #" + gl.ruta.IdRuta);
            txtItinerario.setText("No. #" + auxLectura.IdItinerario);
            txtContador.setText(auxLectura.IdContador);
            txtFecha.setEnabled(false);
            btnFecha.setEnabled(false);
            txtUsuario.setText(auxLectura.IdUsuarioServicio + " - " + auxLectura.Usuario);

            if (auxLectura.Servicio_bajo_demanda || auxLectura.Servicio_bajo_demandafp) {
                txtLecKW.setEnabled(true);
            }

            if (auxLectura.Lectura_realizada == 0) {
                setFechaActual();
            } else {
                lecturaModel.getLinea("WHERE IdLectura = "+auxLectura.Lectura_realizada);

                if (lecturaModel.objLectura != null) {
                    editando = true;
                    objLectura = lecturaModel.objLectura;
                    tmpActualLect = objLectura.Lectura;

                    if (objLectura.Fecha.contains("T")) {
                        txtFecha.setText(du.strFecha(objLectura.Fecha));
                    }

                    txtLectura.setText(objLectura.Lectura+"");
                    txtConsumo.setText(objLectura.Consumo+"");
                    txtLecKW.setText(objLectura.Lectura_kw+"");
                }
            }

            if (editando) {
                if (objLectura.StatCom == 1) {
                    txtLectura.setEnabled(false);
                    btnGuardar.setVisibility(View.GONE);
                }

                btnImprimir.setVisibility(View.VISIBLE);
            }

            IdProformaActual = catalogo.existeProforma(auxLectura.IdUsuarioServicio);
            if (IdProformaActual != 0) {
                tieneProforma = true;
                lblProforma.setText("Proforma creada.");
                lblProforma.setVisibility(View.VISIBLE);
            } else {
                lblProforma.setVisibility(View.GONE);
            }
            txtLectura.requestFocus();
            txtLectura.selectAll();
        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
        }
    }

    private void setHandlers() {
        btnRegresar.setOnClickListener(v -> regresar());

        btnFecha.setOnClickListener(v -> abrirCalendario());

        btnGuardar.setOnClickListener(v -> {
            if (validaDatos()) {
                correcta = true;
                dialogo("Guardar lectura", "¿Está seguro de continuar?");
            }
        });

        btnImprimir.setOnClickListener(v -> {
            pk = !editando ? lecturaModel.IdActualLectura : auxLectura.Lectura_realizada;
            imprimir("Imprimir", "¿Desea imprimir?");
        });

        txtLectura.setOnKeyListener((v, keyCode, event) -> {
            try {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            if (validaDatos()) {
                                correcta = true;
                                dialogo("Guardar lectura", "¿Está seguro de continuar?");
                            }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    private boolean validaDatos() {
        try {
            if (txtFecha.getText().toString().isEmpty()) {
                helper.toast("Ingrese fecha");
                return false;
            }

            if (txtLectura.getText().toString().isEmpty()) {
                helper.toast("Ingrese lectura");
                return false;
            }

            if (txtLectura.getText().toString().equals("0.0") || txtFecha.getText().toString().equals("0")) {
                helper.toast("La lectura debe ser mayor a 0");
                return false;
            }

            if (auxLectura.Servicio_bajo_demanda || auxLectura.Servicio_bajo_demandafp) {
                if (txtLecKW.getText().toString().isEmpty()) {
                    helper.toast("Debe ingresar la lectura de los KW");
                    txtLecKW.requestFocus();
                    txtLecKW.selectAll();
                    return false;
                } else {
                    if (txtLecKW.getText().toString().equals("0.0") || txtLecKW.getText().toString().equals("0")) {
                        helper.toast("La lectura en KW debe ser mayor a 0");
                        return false;
                    }
                }
            }

            if (calculosLectura()) {
                txtConsumo.setText(consumo+"");
                if (consumo < 0) {
                    helper.msgbox("La lectura anterior: " + lecturaAnterior + " es mayor que la lectura ingresada: " + lecturaActual);
                    txtLectura.requestFocus();
                    txtLectura.selectAll();
                    return  false;
                }

                if (tmpPromedio > 0 && promedio < Double.valueOf(txtConsumo.getText().toString())) {
                    dConfirmarLectura("Lectura Incorrecta","El consumo ("+consumo+") es mayor a lectura promedio: " +promedio+ ". ¿Está seguro de que está correcta?");
                    correcta = false;
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
            return false;
        }

        return true;
    }

    private boolean calculosLectura() {
        clsBeContadores contadorActual;
        String contadorMesAnterior;
        double lecturaAnt;

        try {

            lecturaActual = Double.valueOf(txtLectura.getText().toString());

            LocalDate fechaAnterior = FechaPagoActual.minusMonths(1);
            lecturaAnt = lecturaModel.getLecturaAnteriorContador(auxLectura.IdUsuarioServicio,
                                                                    IdContadorActual,
                                                                    fechaAnterior.getYear(),
                                                                    fechaAnterior.getMonthValue());

            if (lecturaAnt == 0) {
                contadorActual = contadorModel.getContadorByUsuario(auxLectura.IdUsuarioServicio);
                contadorMesAnterior = lecturaModel.Get_IdContador_Fecha(auxLectura.IdUsuarioServicio,
                        fechaAnterior.getMonthValue(),
                        fechaAnterior.getYear());

                if (!contadorActual.IdContador.equals(contadorMesAnterior)) {
                    double tmpL = contadorModel.getLecturaContadoActual(contadorActual.IdContador);
                    lecturaAnterior = tmpL;
                }
            }

            consumo = (lecturaActual - lecturaAnterior);
            tmpPromedio = lecturaModel.getPromedio(auxLectura.IdUsuarioServicio);

            double porcentaje_lectura = (gl.institucion.Porcentaje_lectura) / 100;
            promedio = helper.round2dec(tmpPromedio + (tmpPromedio * porcentaje_lectura));

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" calculosLectura - "+ e);
            return false;
        }
        return true;
    }

    private void guardar() {
        clsBeServicios_instalado item;
        try {
            objLectura = new clsBeLectura();

            objLectura.IdUsuarioServicio = auxLectura.IdUsuarioServicio;
            objLectura.IdContador = auxLectura.IdContador;
            objLectura.Fecha = du.convertirFecha(txtFecha.getText().toString());
            objLectura.Lectura = Double.valueOf(txtLectura.getText().toString());
            objLectura.Consumo = Double.valueOf(txtConsumo.getText().toString());
            objLectura.Fecha_creacion = du.getFechaCompleta();
            objLectura.Lectura_kw = Double.valueOf(txtLecKW.getText().toString());
            objLectura.IdTecnico = gl.tecnico.IdTecnico;
            objLectura.StatCom = 0;
            objLectura.Con_hh = 1;

            if (!editando) {
                if (lecturaModel.guardar(objLectura, false)) {
                    helper.toast("Lectura guardada con éxito");
                } else {
                    helper.toast("Hubo problemas al guardar la lectura.");
                    return;
                }
            } else {
                objLectura.IdLectura = auxLectura.Lectura_realizada;
                if (lecturaModel.actualizar(objLectura)) {
                    helper.toast("Lectura actualizada con éxito");

                    if (tmpActualLect != objLectura.Lectura) {
                        tmpActualLect = objLectura.Lectura;

                        IdProformaActual = catalogo.existeProforma(auxLectura.IdUsuarioServicio);
                        if (IdProformaActual != 0) {
                            sql="UPDATE PROFORMA SET ANULADO = 1 WHERE IdProforma = "+IdProformaActual;
                            db.execSQL(sql);

                            sql="DELETE FROM MESES_PROFORMA WHERE IdProforma = "+IdProformaActual;
                            db.execSQL(sql);
                        }
                    }
                } else {
                    helper.toast("Hubo problemas al actualizar la lectura.");
                    return;
                }
            }

            Double consumo = Double.valueOf(txtConsumo.getText().toString());
            if (consumo > 0) {
                getDatosGeneralesProforma();
            }

            item = new clsBeServicios_instalado();
            item.IdInstalacion = auxLectura.IdInstalacion;
            item.Lectura_realizada = !editando ? lecturaModel.IdActualLectura : auxLectura.Lectura_realizada;
            item.Lectura_correcta = correcta ? 1:0;

            serviciosModel.actualizarServicio(item);

            int IdUsuarioSinLectura = mdlSinLectura.getIdUsuarioSinLectura(auxLectura.IdUsuarioServicio);

            if (IdUsuarioSinLectura > 0) {
                sql = "DELETE FROM USUARIO_SIN_LECTURA WHERE IdUsuarioSinLectura = " + IdUsuarioSinLectura;
                db.execSQL(sql);
            }

            pk = !editando ? lecturaModel.IdActualLectura: auxLectura.Lectura_realizada;
            imprimir("Imprimir", "¿Desea imprimir?");

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" guardar -  "+ e);
        }
    }

    private void getDatosGeneralesProforma() {
        double consumo = 0;
        try  {
            uServicioInstalado = serviciosModel.getUsServicio(IdUsuarioServicio);

            sql = "DELETE FROM TMP_PROFORMA_USUARIO WHERE IdUsuarioServicio = " + IdUsuarioServicio;
            db.execSQL(sql);
            Calcula_Meses_Pago_Trans();
        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" getDatosGeneralesProforma - " + e);
        }
    }

    private int Calcula_Meses_Pago_Trans() {
        int filas = 0, vCantidadMesesPendientes = 0;
        LocalDate vFechaPendiente = null;
        double MontoTotalMesesPendientes = 0;

        try {
            /*FechaUltimoPago = LocalDate.of(objUP.anno, objUP.nomes, 1);
            CantidadMesesPendientes = du.diffMeses(FechaUltimoPago, FechaPagoActual);*/

            if (CantidadMesesPendientes <= 6) {

                for (int i = 1; i <= CantidadMesesPendientes; i++) {
                    vFechaPendiente = FechaUltimoPago.plusMonths(i);

                    if (lecturaModel.getLecturaByFecha(vFechaPendiente.getYear(), vFechaPendiente.getMonthValue(), IdUsuarioServicio, IdContadorActual) != 0) {
                        MontoTotalMesesPendientes = Calcula_Monto_PagoUsuario(vFechaPendiente.getYear(), vFechaPendiente.getMonthValue());

                        vCantidadMesesPendientes += 1;
                    }
                }
                guardarProforma();
            }
        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+"  Calcula_Meses_Pago_Trans - "+ e);
        }
        return filas;
    }

    private double Calcula_Monto_PagoUsuario(int anio, int mes) {
        double total = 0, MoraActual = 0, vImporteUCD = 0,
               vImporteKW = 0, vImportePot = 0, IVA = 0,
               Monto = 0, ImporteAutoProductor = 0, ImporteTNS = 0,
               ImporteTS = 0, IVACF = 0, Lectura_Actual = 0, Lectura_Anterior = 0, Consumo_A_Cobrar = 0, LecturaKW = 0, MontoMesAnterior = 0;
        LocalDate FechaAPagar = null, vFechaUP = null, vFechaUL = null, vFechaPL = null, FechaAct = null, FechaAnt = null;
        boolean vTieneMora = false;
        clsBeLectura lactual = null, lanterior = null;
        clsBeInstitucion_detalle paramDet = null;
        int IdRenglon = 0;
        String NomRenglon = "";

        try {

            FechaAPagar = LocalDate.of(anio, mes, 1);
            vFechaUL = du.getFechaStr(lecturaModel.getFechaUltimaLectura(IdUsuarioServicio));
            vFechaPL = du.getFechaStr(lecturaModel.getFechaPrimeraLectura(IdUsuarioServicio));
            vFechaUP = LocalDate.of(objUP.anno, objUP.nomes, 1);

            //Get Lecturas
            lactual = lecturaModel.getLecturas(IdUsuarioServicio, anio, mes, IdContadorActual);
            FechaAct = LocalDate.parse(du.strFechaIng(lactual.Fecha));
            Lectura_Actual = lactual.Lectura;

            LocalDate tmpFechaAnt = FechaAPagar.minusMonths(1);
            lanterior = lecturaModel.getLecturas(IdUsuarioServicio, tmpFechaAnt.getYear(), tmpFechaAnt.getMonthValue(), IdContadorActual);
            //FechaAnt = LocalDate.parse(du.strFechaIng(lanterior.Fecha));
            //Lectura_Anterior = lanterior.Lectura;

            if (lanterior != null) {
                FechaAnt = LocalDate.parse(du.strFechaIng(lanterior.Fecha));
                Lectura_Anterior = lanterior.Lectura;
            } else {
                Lectura_Anterior = 0;
            }

            if (Lectura_Actual == 0) {
                Consumo_A_Cobrar = 0;
            } else if (Lectura_Anterior == 0 && lanterior != null) {
                String ContadorMesAnterior = lecturaModel.getContadorFecha(IdUsuarioServicio, FechaAnt.getYear(), FechaAnt.getMonthValue());

                if (!IdContadorActual.equals(ContadorMesAnterior)) {
                    Lectura_Anterior = contadorModel.getLecturaByContador(IdContadorActual);
                }

                if (Lectura_Anterior == 0) {
                    Consumo_A_Cobrar = Lectura_Actual;
                } else {
                    Consumo_A_Cobrar = Lectura_Actual - Lectura_Anterior;
                }
            } else {
                Consumo_A_Cobrar = Lectura_Actual - Lectura_Anterior;
            }

            if (Lectura_Actual == 0) return 0;

            LocalDate Ahora = LocalDate.now();
            if (vFechaUL != null && vFechaUP == null) {
                vTieneMora = Tiene_Mora(Ahora, vFechaUP, FechaAct);

            } else if (vFechaUP != null && vFechaUL != null) {
                vTieneMora = Tiene_Mora(Ahora, FechaAPagar, FechaAct);

            } else {
                vTieneMora = Tiene_Mora(Ahora, FechaAPagar, FechaAct);

            }

            if (vTieneMora) {
                renglonModel.getRenglon("(2,3,5,6)");
            } else {
                renglonModel.getRenglon("(2,3,5)");
            }

            LecturaKW = lecturaModel.getLecturas(IdUsuarioServicio, anio, mes, IdContadorActual).Lectura_kw;
            paramDet = insDetModel.getLinea(FechaAPagar.toString());

            if (paramDet != null) {
                MoraActual = paramDet.Mora;

                for (clsBeRenglones obj: renglonModel.lista) {
                    IdRenglon = obj.Idrenglon;
                    NomRenglon = obj.renglon;

                    switch (IdRenglon) {
                        case 2:
                            if (uServicioInstalado.servicio_bajo_demandafp) {
                                vImporteUCD = Consumo_A_Cobrar * paramDet.Tarifa_demandafp;
                                vImporteKW = LecturaKW * paramDet.Precio_kwfp;
                                vImportePot = uServicioInstalado.potencia_contratada * paramDet.Precio_pcfp;
                                IVA = (vImporteUCD + vImporteKW + vImportePot) * vIvaDecimal;

                                Monto = vImporteUCD + vImporteKW + vImportePot + IVA;

                            } else if (uServicioInstalado.servicio_bajo_demanda) {
                                vImporteUCD = Consumo_A_Cobrar * paramDet.Tarifa_demanda;
                                vImporteKW = LecturaKW * paramDet.Precio_kw;
                                vImportePot = uServicioInstalado.potencia_contratada * paramDet.Precio_pc;
                                IVA = (vImporteUCD + vImporteKW + vImportePot) * vIvaDecimal;

                                Monto = vImporteUCD + vImporteKW + vImportePot + IVA;
                            } else {
                                if (Consumo_A_Cobrar > 300) {
                                    ImporteTNS = Consumo_A_Cobrar * paramDet.Preciotns;
                                    IVA = (ImporteTNS) * vIvaDecimal;

                                } else if (Consumo_A_Cobrar <= 300) {
                                    ImporteTS = Consumo_A_Cobrar * paramDet.Preciots;
                                    IVA = (ImporteTS) * vIvaDecimal;

                                }

                                Monto = ImporteTS + ImporteTNS + IVA;
                            }
                            break;
                        case 3:
                            Monto = paramDet.Luz_publica;
                            break;
                        case 5:
                            if (uServicioInstalado.servicio_bajo_demanda) {
                                Monto = paramDet.Cargo_fijo_btdp;
                            } else if (uServicioInstalado.servicio_bajo_demandafp) {
                                Monto = paramDet.Cargo_fijo_btdfp;
                            } else  {
                                Monto = paramDet.Cargo_fijo;
                            }

                            IVACF = Monto * vIvaDecimal;
                            Monto = Monto + IVACF;
                            break;
                        case 6:
                            LocalDate auxFechaActual = LocalDate.of(anio, mes, 1);
                            LocalDate auxFechaAnterior = auxFechaActual.minusMonths(1);

                            if (uServicioInstalado.servicio_bajo_demandafp) {
                                vImporteUCD = Consumo_A_Cobrar * paramDet.Tarifa_demandafp;
                                vImporteKW = LecturaKW * paramDet.Precio_kwfp;
                                vImportePot = uServicioInstalado.potencia_contratada * paramDet.Precio_pcfp;
                                IVA = (vImporteUCD + vImporteKW + vImportePot + paramDet.Cargo_fijo_btdfp) * vIvaDecimal;

                                Monto = paramDet.Luz_publica + vImporteUCD + vImporteKW + vImportePot + paramDet.Cargo_fijo_btdfp + IVA;

                                MontoMesAnterior = Calcula_Monto_Pago_Usuario_Anterior(auxFechaAnterior.getYear(), auxFechaAnterior.getMonthValue());

                                Monto = (Monto + MontoMesAnterior + MontoMora) * MoraActual;
                                //MontoMesAnterior = (MontoMesAnterior * paramDet.Mora) + MontoMesAnterior;
                               // MontoMora = (Monto + MontoMesAnterior) * MoraActual;
                                //Monto = 0;
                            } else if (uServicioInstalado.servicio_bajo_demanda) {
                                vImporteUCD = Consumo_A_Cobrar * paramDet.Tarifa_demanda;
                                vImporteKW = LecturaKW * paramDet.Precio_kw;
                                vImportePot = uServicioInstalado.potencia_contratada * paramDet.Precio_pc;
                                IVA = (vImporteUCD + vImporteKW + vImportePot + paramDet.Cargo_fijo_btdp) * vIvaDecimal;

                                Monto = paramDet.Luz_publica + vImporteUCD + vImporteKW + vImportePot + paramDet.Cargo_fijo_btdp + IVA;
                                MontoMesAnterior = Calcula_Monto_Pago_Usuario_Anterior(auxFechaAnterior.getYear(), auxFechaAnterior.getMonthValue());

                                //MontoMesAnterior = (MontoMesAnterior * paramDet.Mora) + MontoMesAnterior;
                                Monto = (Monto + MontoMesAnterior + MontoMora) * MoraActual;
                                //Monto = 0;
                            } else {
                                if (Consumo_A_Cobrar > 300) {
                                    ImporteTNS = Consumo_A_Cobrar * paramDet.Preciotns;
                                    IVA = (ImporteTNS + paramDet.Cargo_fijo) * vIvaDecimal;

                                } else if (Consumo_A_Cobrar <= 300) {
                                    ImporteTS = Consumo_A_Cobrar * paramDet.Preciots;
                                    IVA = (ImporteTS + paramDet.Cargo_fijo) * vIvaDecimal;

                                }

                                Monto = (ImporteTS + ImporteTNS + IVA + paramDet.Cargo_fijo + paramDet.Luz_publica);
                                MontoMesAnterior  = Calcula_Monto_Pago_Usuario_Anterior(auxFechaAnterior.getYear(), auxFechaAnterior.getMonthValue());
                                Monto = (Monto + MontoMesAnterior + MontoMora) * MoraActual;
                                //MontoMesAnterior = (MontoMesAnterior * paramDet.Mora) + MontoMesAnterior;
                                //MontoMora = (Monto + MontoMesAnterior) * MoraActual;
                                //Monto = 0;
                            }
                            break;
                    }

                    clsBeTmpProformaUs item = new clsBeTmpProformaUs();
                    item.IdUsuarioServicio = IdUsuarioServicio;
                    item.NoMes =  mes;

                    String auxMes = mes < 9 ? "0"+mes: ""+mes;
                    item.Mes = auxMes +" "+ du.getNombreMes(mes);

                    item.IdRenglon = IdRenglon;
                    item.Descripcion = NomRenglon;
                    item.Cantidad = helper.round2dec(Monto);
                    item.Anio = anio;

                    catalogo.guardarProformaUsuario(item);
                }
            }

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" Calcula_Monto_PagoUsuario - "+ e);
        }

        return total;
    }

    private double Calcula_Monto_Pago_Usuario_Anterior(int anio, int mes) {
        double total = 0,
                MoraActual = 0, vImporteUCD = 0,
                vImporteKW = 0, vImportePot = 0, IVA = 0,
                Monto = 0, ImporteAutoProductor = 0,
                ImporteTNS = 0, ImporteTS = 0,
                IVACF = 0, Lectura_Actual = 0,
                Lectura_Anterior = 0, Consumo_A_Cobrar = 0,
                LecturaKW = 0, MontoMesAnterior = 0;
        LocalDate FechaAPagar = null,
                vFechaUP = null,
                vFechaUL = null, vFechaPL = null,
                FechaAct = null, FechaAnt = null;
        boolean vTieneMora = false;
        clsBeLectura lactual = null, lanterior = null;
        clsBeInstitucion_detalle paramDet = null;
        int IdRenglon = 0;
        String NomRenglon = "";

        try {

            FechaAPagar = LocalDate.of(anio, mes, 1);
            vFechaUL = du.getFechaStr(lecturaModel.getFechaUltimaLectura(IdUsuarioServicio));
            vFechaPL = du.getFechaStr(lecturaModel.getFechaPrimeraLectura(IdUsuarioServicio));
            vFechaUP = LocalDate.of(objUP.anno, objUP.nomes, 1);

            if (du.diffMeses(vFechaUP, FechaAPagar) <= 0)  return 0;

            //Get Lecturas
            lactual = lecturaModel.getLecturas(IdUsuarioServicio, anio, mes, IdContadorActual);
            FechaAct = LocalDate.parse(du.strFechaIng(lactual.Fecha));
            Lectura_Actual = lactual.Lectura;

            LocalDate tmpFechaAnt = FechaAPagar.minusMonths(1);
            lanterior = lecturaModel.getLecturas(IdUsuarioServicio, tmpFechaAnt.getYear(), tmpFechaAnt.getMonthValue(), IdContadorActual);
            FechaAnt = LocalDate.parse(du.strFechaIng(lanterior.Fecha));
            Lectura_Anterior = lanterior.Lectura;

            if (Lectura_Actual == 0) {
                Consumo_A_Cobrar = 0;
            } else if (Lectura_Anterior == 0) {
                String ContadorMesAnterior = lecturaModel.getContadorFecha(IdUsuarioServicio, FechaAnt.getYear(), FechaAnt.getMonthValue());

                if (!IdContadorActual.equals(ContadorMesAnterior)) {
                    Lectura_Anterior = contadorModel.getLecturaByContador(IdContadorActual);
                }

                if (Lectura_Anterior == 0) {
                    Consumo_A_Cobrar = Lectura_Actual;
                } else {
                    Consumo_A_Cobrar = Lectura_Actual - Lectura_Anterior;
                }
            } else {
                Consumo_A_Cobrar = Lectura_Actual - Lectura_Anterior;
            }

            LocalDate Ahora = LocalDate.now();
            if (vFechaUL != null && vFechaUP == null) {
                vTieneMora = Tiene_Mora(Ahora, vFechaUP, FechaAct);

            } else if (vFechaUP != null && vFechaUL != null) {
                vTieneMora = Tiene_Mora(Ahora, FechaAPagar, FechaAct);

            } else {
                vTieneMora = Tiene_Mora(Ahora, FechaAPagar, FechaAct);

            }

            if (vTieneMora) {
                renglonModel.getRenglon("(2,3,5,6)");
            } else {
                renglonModel.getRenglon("(2,3,5)");
            }

            LecturaKW = lecturaModel.getLecturas(IdUsuarioServicio, anio, mes, IdContadorActual).Lectura_kw;
            paramDet = insDetModel.getLinea(FechaAPagar.toString());

            if (paramDet != null) {
                MoraActual = paramDet.Mora;

                for (clsBeRenglones obj : renglonModel.lista) {
                    switch (obj.Idrenglon) {
                        case 2:
                            if (uServicioInstalado.servicio_bajo_demandafp) {
                                vImporteUCD = Consumo_A_Cobrar * paramDet.Tarifa_demandafp;
                                vImporteKW = LecturaKW * paramDet.Precio_kwfp;
                                vImportePot = uServicioInstalado.potencia_contratada * paramDet.Precio_pcfp;
                                IVA = (vImporteUCD + vImporteKW + vImportePot) * vIvaDecimal;

                                Monto = vImporteUCD + vImporteKW + vImportePot + IVA;

                            } else if (uServicioInstalado.servicio_bajo_demanda) {
                                vImporteUCD = Consumo_A_Cobrar * paramDet.Tarifa_demanda;
                                vImporteKW = LecturaKW * paramDet.Precio_kw;
                                vImportePot = uServicioInstalado.potencia_contratada * paramDet.Precio_pc;
                                IVA = (vImporteUCD + vImporteKW + vImportePot) * vIvaDecimal;

                                Monto = vImporteUCD + vImporteKW + vImportePot + IVA;
                            } else {
                                if (Consumo_A_Cobrar > 300) {
                                    ImporteTNS = Consumo_A_Cobrar * paramDet.Preciotns;
                                    IVA = (ImporteTNS + paramDet.Cargo_fijo) * vIvaDecimal;

                                } else if (Consumo_A_Cobrar <= 300) {
                                    ImporteTS = Consumo_A_Cobrar * paramDet.Preciots;
                                    IVA = (ImporteTS + paramDet.Cargo_fijo) * vIvaDecimal;

                                }

                                Monto = ImporteTS + ImporteTNS + IVA;
                            }
                            break;
                        case 3:
                            Monto = paramDet.Luz_publica;
                            break;
                        case 5:
                            if (uServicioInstalado.servicio_bajo_demanda) {
                                Monto = paramDet.Cargo_fijo_btdp;
                            } else if (uServicioInstalado.servicio_bajo_demandafp) {
                                Monto = paramDet.Cargo_fijo_btdfp;
                            } else {
                                Monto = paramDet.Cargo_fijo;
                            }

                            IVACF = Monto * vIvaDecimal;
                            Monto = Monto + IVACF;
                            break;
                        case 6:
                            LocalDate auxFechaActual = LocalDate.of(anio, mes, 1);
                            LocalDate auxFechaAnterior = auxFechaActual.minusMonths(1);

                            if (uServicioInstalado.servicio_bajo_demandafp) {
                                vImporteUCD = Consumo_A_Cobrar * paramDet.Tarifa_demandafp;
                                vImporteKW = LecturaKW * paramDet.Precio_kwfp;
                                vImportePot = uServicioInstalado.potencia_contratada * paramDet.Precio_pcfp;
                                IVA = (vImporteUCD + vImporteKW + vImportePot + paramDet.Cargo_fijo_btdfp) * vIvaDecimal;

                                Monto = paramDet.Luz_publica + vImporteUCD + vImporteKW + vImportePot + paramDet.Cargo_fijo_btdfp + IVA;

                                MontoMesAnterior += Calcula_Monto_Pago_Usuario_Anterior(anio, mes);

                                MontoMesAnterior = (MontoMesAnterior * paramDet.Mora) + MontoMesAnterior;
                                MontoMora = (Monto + MontoMesAnterior) * MoraActual;
                                Monto = 0;
                            } else if (uServicioInstalado.servicio_bajo_demanda) {
                                vImporteUCD = Consumo_A_Cobrar * paramDet.Tarifa_demanda;
                                vImporteKW = LecturaKW * paramDet.Precio_kw;
                                vImportePot = uServicioInstalado.potencia_contratada * paramDet.Precio_pc;
                                IVA = (vImporteUCD + vImporteKW + vImportePot + paramDet.Cargo_fijo_btdp) * vIvaDecimal;

                                Monto = paramDet.Luz_publica + vImporteUCD + vImporteKW + vImportePot + paramDet.Cargo_fijo_btdp + IVA;

                                MontoMesAnterior += Calcula_Monto_Pago_Usuario_Anterior(auxFechaAnterior.getYear(), auxFechaAnterior.getMonthValue());
                                MontoMesAnterior = (MontoMesAnterior * paramDet.Mora) + MontoMesAnterior;
                                MontoMora = (Monto + MontoMesAnterior) * MoraActual;
                                Monto = 0;
                            } else {
                                if (Consumo_A_Cobrar > 300) {
                                    ImporteTNS = Consumo_A_Cobrar * paramDet.Preciotns;
                                    IVA = (ImporteTNS + paramDet.Cargo_fijo) * vIvaDecimal;

                                } else if (Consumo_A_Cobrar <= 300) {
                                    ImporteTS = Consumo_A_Cobrar * paramDet.Preciots;
                                    IVA = (ImporteTS + paramDet.Cargo_fijo) * vIvaDecimal;

                                }

                                Monto = (ImporteTS + ImporteTNS + IVA + paramDet.Cargo_fijo + paramDet.Luz_publica);
                                MontoMesAnterior += Calcula_Monto_Pago_Usuario_Anterior(auxFechaAnterior.getYear(), auxFechaAnterior.getMonthValue());
                                MontoMesAnterior = (MontoMesAnterior * paramDet.Mora) + MontoMesAnterior;
                                MontoMora = (Monto + MontoMesAnterior) * MoraActual;
                                Monto = 0;
                            }
                            break;
                    }

                    total += helper.round2dec(Monto) + MontoMesAnterior;
                }
            }
        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" Calcula_Monto_Pago_Usuario_Anterior - "+ e);
        }

        return total;
    }

    private boolean Tiene_Mora(LocalDate fecha_pago, LocalDate fecha_a_pagar, LocalDate fecha_lectura) {
        clsBeLectura lectura = null;
        int dif_fecha = 0;
        long dif_dias = 0;
        try {
            dif_fecha = du.diffMeses(fecha_a_pagar, fecha_pago);
            dif_dias = du.diffDias(fecha_lectura, fecha_pago);

            lectura = lecturaModel.getLecturas(IdUsuarioServicio, fecha_a_pagar.getYear(), fecha_a_pagar.getMonthValue(), IdContadorActual);

            if (lectura != null && dif_fecha >= 1 && dif_dias > 31 && !catalogo.MoraPagada(fecha_a_pagar.getYear(), fecha_a_pagar.getMonthValue(), IdUsuarioServicio)) {
                return  true;
            }

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" Tiene_Mora - "+ e);
            return false;
        }
        return false;
    }

    private void guardarProforma() {
        ArrayList<clsBeTmpProformaUs> auxLista = new ArrayList<>();
        ArrayList<clsBeTmpProformaUs> mProforma = new ArrayList<>();
        LocalDate fechaPago;
        String desc = "";

        try {
            String fechaLocal = du.getFechaCompleta();;
            fechaPago = LocalDate.now();
            auxLista = catalogo.getMesesPendientes(auxLectura.IdUsuarioServicio);
            corel = corelModel.getCorrelativo();

            if (corel == null) {
                helper.toast("No tiene correlativo asignado a la ruta: " +gl.ruta);
            }

            int correlativo = corel.actual + 1;

            proforma = new clsBeProforma();
            proforma.IdUsuarioServicio = IdUsuarioServicio;
            proforma.fecha_recibo = fechaLocal;
            proforma.fecha_modifica = fechaLocal;
            proforma.num_proforma = correlativo;
            proforma.serie_proforma = corel.serie;
            proforma.anulado = false;
            proforma.fecha_creacion = fechaLocal;
            proforma.proveedor = auxLectura.Usuario;

            if (CantidadMesesPendientes > 2) {
                LocalDate fechaCorte = fechaPago.plusDays(1);
                desc = "SU PROFORMA GENERA ORDEN DE CORTE A PARTIR DEL " +du.setFechaToString(fechaCorte) + ". " +
                        "Si ya realizó su pago, favor hacer caso omiso.";
            } else {
                desc = "";
            }

            proforma.observacion = desc;
            proforma.mes_pago = fechaPago.getMonthValue();
            proforma.año_pago = fechaPago.getYear();
            proforma.pagol = true;
            proforma.Con_hh = 1;
            proforma.IdTecnico = gl.tecnico.IdTecnico;
            proforma.fecha_ultimo_pago = String.valueOf(du.setFecha(objUP.anno, objUP.nomes, 1));

            ArrayList<clsBeTmpProformaUs> lista =  catalogo.getDetalleTmpProforma(IdUsuarioServicio);
            double factor = 1 + (gl.institucion.Porcentaje_iva / 100);

            proforma.detalle.clear();
            for (clsBeTmpProformaUs obj: lista) {
                clsBeProforma_detalle itemdet = new clsBeProforma_detalle();
                itemdet.idrenglon = obj.IdRenglon;
                itemdet.descripcion = obj.Descripcion;
                itemdet.cantidad = helper.round2dec(obj.Cantidad);
                itemdet.exonera = false;
                itemdet.exento = obj.exento_iva;

                if (!obj.exento_iva) {
                    itemdet.monto_gravable = helper.round2dec(obj.Cantidad / factor);
                    itemdet.monto_impuesto = helper.round2dec(obj.Cantidad - itemdet.monto_gravable);
                } else {
                    itemdet.monto_gravable = helper.round2dec(obj.Cantidad);
                    itemdet.monto_impuesto = 0;
                }

                proforma.detalle.add(itemdet);
            }

            mProforma = catalogo.getTmpProformaUsuario(IdUsuarioServicio);

            if (mProforma.size() > 0) {
                proforma.MesesProforma.clear();

                for (clsBeTmpProformaUs obj:mProforma) {
                    clsBeMeses_proforma item = new clsBeMeses_proforma();

                    item.IdUsuarioServicio = obj.IdUsuarioServicio;
                    item.nomes = obj.NoMes;
                    item.mes = obj.Mes;
                    item.idrenglon = obj.IdRenglon;
                    item.descripcion = obj.Descripcion;
                    item.cantidad = obj.Cantidad;
                    item.anno = obj.Anio;

                    proforma.MesesProforma.add(item);
                }
            }

            if (catalogo.guardarProforma(proforma)) {
                tieneProforma = true;
                sql = "UPDATE CORRELATIVO_PROFORMA SET ACTUAL = "+ correlativo;
                db.execSQL(sql);

                IdProformaActual = catalogo.existeProforma(auxLectura.IdUsuarioServicio);
                lblProforma.setText("Proforma creada.");
                lblProforma.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" guardarProforma - "+ e);
        }
    }

    private void generaJsonProf() {
        double total = 0, iva;

        try {
            proformaImp = new clsBeProformaImp();

            proformaImp.Contribuyente = auxLectura.IdUsuarioServicio + " - " + auxLectura.Usuario;
            proformaImp.Contador = auxLectura.IdContador;
            proformaImp.Direccion = auxLectura.Direccion;

            proformaImp.proforma = catalogo.buscarProforma(auxLectura.IdUsuarioServicio);

            pendientes = catalogo.getMesesPendientes(auxLectura.IdUsuarioServicio);

            for (clsBeTmpProformaUs obj:pendientes) {
                clsBeLectura tmpLectura = lecturaModel.getLecturas(IdUsuarioServicio, obj.Anio, obj.NoMes, IdContadorActual);
                if (tmpLectura !=  null) {
                    obj.Consumo = tmpLectura.Consumo;
                    obj.Demanda = tmpLectura.Lectura_kw;
                }
            }

            proformaImp.pendientes = pendientes;
            total = proformaImp.proforma.detalle.stream().mapToDouble(clsBeProforma_detalle::getCantidad).sum();
            iva = proformaImp.proforma.detalle.stream().mapToDouble(clsBeProforma_detalle::getMonto_impuesto).sum();

            if (iva > 0) {
                clsBeProforma_detalle detIva = new clsBeProforma_detalle();
                detIva.descripcion = "IVA";
                detIva.cantidad = helper.round2dec(iva);

                proformaImp.proforma.detalle.add(detIva);
            }

            proformaImp.Monto_letras = letras.convertirALetras(total).toUpperCase();
            proformaImp.Fecha_notificacion = du.setFechaImp();
            proformaImp.Total = total;
            proformaImp.consumos = catalogo.ultimosConsumos(IdUsuarioServicio);

            Gson gson = new Gson();
            String  profJson = gson.toJson(proformaImp);

            Intent intent = this.getPackageManager().getLaunchIntentForPackage("com.olc.printcilico");
            intent.putExtra("fname", "");
            intent.putExtra("proforma", profJson);
            intent.putExtra("copies", 0);
            this.startActivity(intent);

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" generaJsonProf - "+ e);
        }

    }

    private void setFechaActual() {
        Calendar c = Calendar.getInstance();
        anio = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH)+1;
        dia = c.get(Calendar.DAY_OF_MONTH);

        txtFecha.setText(new StringBuilder()
                .append(dia).append("/").append(mes).append("/")
                .append(anio).append(""));

        datePicker.init(anio, mes, dia, null);
    }

    private void abrirCalendario() {
        Calendar c = Calendar.getInstance();

        anio = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, (view, anio, mes, dia) -> {
            txtFecha.setText(new StringBuilder()
                    .append(dia).append("/").append(mes).append("/")
                    .append(anio).append(""));
            txtLectura.requestFocus();
            txtLectura.selectAll();

        }, anio, mes, dia);

        datePickerDialog.show();
    }

    private void dialogo(String titulo, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setIcon(R.drawable.logo);
        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setPositiveButton("Si", (dialog1, id) -> guardar());

        dialog.setNegativeButton("No", (dialog12, id) -> {
        });

        dialog.show();
    }

    private void dConfirmarLectura(String titulo, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setIcon(R.drawable.logo);
        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setPositiveButton("Si", (dialog1, id) -> {
            guardar();
        });

        dialog.setNegativeButton("No", (dialog12, id) -> {
        });

        dialog.show();
    }

    private void imprimir(String titulo, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setIcon(R.drawable.imprimir);
        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setPositiveButton("Si", (dialog1, id) -> {
            if (!tieneProforma) {
                doPrint();
            } else {
                generaJsonProf();
            }

            regresar();
        });

        dialog.setNegativeButton("No", (dialog12, id) -> {
            if (!editando) {
                editando = true;
                auxLectura.Lectura_realizada = lecturaModel.IdActualLectura;
                dialog12.cancel();
            }
        });

        dialog.show();
    }

    private void doPrint() {
        try {
            clsBeLectura lecturaActual, lecturaAnterior = null;
            clsBeUsuarios_servicio usuario = null;
            double tmpLecturaAnterior = 0;

            lecturaImp = new clsBeLecturaImp();
            lecturaImp.Nit = "NIT: " + gl.institucion.NIT_Emisor;
            lecturaImp.Direccion = "Dirección: " + gl.institucion.Direccion_emisor;
            lecturaImp.Fecha = "Fecha: " + du.strFechaHora(du.getFechaCompleta());
            lecturaImp.Ruta = "Ruta: No." + gl.ruta.IdRuta;
            lecturaImp.Itinerario = "Itinerario: No." + gl.itinerario;
            lecturaImp.Tecnico = "Técnico: " + gl.tecnico.Nombre;

            lecturaModel.getLinea("WHERE IdLectura = " + pk);
            if (lecturaModel.objLectura != null) {
                lecturaActual = lecturaModel.objLectura;
                lecturaAnterior = lecturaModel.getLecturaAnterior(lecturaActual.IdUsuarioServicio);

                usuarioSer.getLinea("WHERE IdUsuarioServicio = " + lecturaActual.IdUsuarioServicio);
                usuario = usuarioSer.objUsuarioServicio;

                tmpLecturaAnterior = lecturaAnterior == null ? 0:lecturaAnterior.Lectura;

                lecturaImp.Usuario = "Usuario: " + usuario.IdUsuarioServicio+"-"+usuario.Nombres;
                lecturaImp.Contador = "Contador: " + lecturaActual.IdContador;
                lecturaImp.LecturaAnterior = "Lectura anterior: " + tmpLecturaAnterior+" KW";
                lecturaImp.LecturaActual = "Lectura actual: " + lecturaActual.Lectura+" KW";
                lecturaImp.Consumo = "Consumo: " + lecturaActual.Consumo+" KW";

                String msj = "";

                if (CantidadMesesPendientes > 6) {
                    msj += "Debe más de 6 meses de energía, \n";
                }

                if (lecturaActual.Consumo == 0) {
                    msj += "No tuvo consumo de energía, \n";
                }

                if (!msj.isEmpty()) {
                    lecturaImp.Texto = msj +
                            " Favor abocarse a oficina \n" +
                            " de inmediato";
                }
            } else {
                helper.toast("Problemas al obtener información.");
                return;
            }

            Gson gson = new Gson();
            String  lecturaJson = gson.toJson(lecturaImp);

            Intent intent = this.getPackageManager().getLaunchIntentForPackage("com.olc.printcilico");
            intent.putExtra("fname", "");
            intent.putExtra("lectura", lecturaJson);
            intent.putExtra("copies", 0);
            this.startActivity(intent);

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
        }
    }

    private void regresar() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
        regresar();
    }
}