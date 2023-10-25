package com.example.cuee_mobile.controladores.lectura;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.base.AppMethods;
import com.example.cuee_mobile.clases.clsBeContadores;
import com.example.cuee_mobile.clases.clsBeLectura;
import com.example.cuee_mobile.clases.clsBeServicios_instalado;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.imprimir.clsDocLectura;
import com.example.cuee_mobile.modelos.ServicioInsModel;
import com.example.cuee_mobile.modelos.lectura.LecturaModel;
import com.example.cuee_mobile.modelos.mnt.ContadoresModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.cuee_mobile.controladores.lectura.Lectura.auxLectura;

import java.util.Calendar;

public class LecturaForm extends PBase {

    private TextView lblTecnico, txtUsuario, txtContador, txtRuta, txtItinerario;
    private DatePicker datePicker;
    private ImageView btnRegresar, btnFecha;
    private EditText txtFecha, txtLectura, txtConsumo, txtLecKW;
    private DatePickerDialog datePickerDialog;
    private FloatingActionButton btnGuardar, btnImprimir;
    private clsBeLectura objLectura;
    private LecturaModel lecturaModel;
    private ServicioInsModel serviciosModel;
    private ContadoresModel contadorModel;
    private AppMethods app;
    private clsDocLectura prnLec;
    private int dia, mes, anio;
    private double consumo, promedio, tmpPromedio, lecturaAnterior = 0, lecturaActual=0;;
    private boolean editando = false, continuar = false, correcta = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura_form);
        super.SetBase();

        lblTecnico = findViewById(R.id.lblTecnico);
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

        prnLec = new clsDocLectura(this, 32, Con, db, "lectura.txt");
        app = new AppMethods(this);
        setDatos();
        setHandlers();
    }

    private void setDatos() {
        try {
            lblTecnico.setText("Técnico: "+ gl.tecnico.Nombre);

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
            int auxId = !editando ? lecturaModel.IdActualLectura: auxLectura.Lectura_realizada;
            dImprimir("Imprimir", "¿Desea imprimir lectura?", auxId);
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
            }
        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
            return false;
        }

        return true;
    }

    private boolean calculosLectura() {
        clsBeLectura objLecturaAnt;
        clsBeContadores contadorActual;
        try {

            lecturaActual = Double.valueOf(txtLectura.getText().toString());
            objLecturaAnt = lecturaModel.getLecturaAnterior(auxLectura.IdUsuarioServicio);

            if (objLecturaAnt != null) {
                if (objLecturaAnt.Lectura == 0) {
                    contadorActual = contadorModel.getContador(auxLectura.IdContador);

                    if (!objLecturaAnt.IdContador.equals(contadorActual.IdContador)) {
                        lecturaAnterior = contadorActual.Lectura;
                    }
                } else {
                    lecturaAnterior = objLecturaAnt.Lectura;
                }
            } else {
                return false;
            }

            consumo = lecturaActual - lecturaAnterior;
            tmpPromedio = lecturaModel.getPromedio(auxLectura.IdUsuarioServicio);

            double porcentaje_lectura = (gl.institucion.Porcentaje_lectura) / 100;
            promedio = helper.round2dec(tmpPromedio + (tmpPromedio * porcentaje_lectura));

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
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
                    helper.toast("Lectura guardad con éxito");
                } else {
                    helper.toast("Hubo problemas al guardar la lectura.");
                    return;
                }
            } else {
                objLectura.IdLectura = auxLectura.Lectura_realizada;
                if (lecturaModel.actualizar(objLectura)) {
                    helper.toast("Lectura actualizada con éxito");
                } else {
                    helper.toast("Hubo problemas al actualizar la lectura.");
                    return;
                }
            }

            item = new clsBeServicios_instalado();
            item.IdInstalacion = auxLectura.IdInstalacion;
            item.Lectura_realizada = !editando ? lecturaModel.IdActualLectura : auxLectura.Lectura_realizada;
            item.Lectura_correcta = correcta ? 1:0;

            serviciosModel.actualizarServicio(item);
            int auxId = !editando ? lecturaModel.IdActualLectura: auxLectura.Lectura_realizada;
            dImprimir("Imprimir", "¿Desea imprimir lectura?", auxId);
            //regresar();

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
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
        dialog.setPositiveButton("Si", (dialog1, id) -> {
            guardar();
        });

        dialog.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog12, id) -> {
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

        dialog.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog12, id) -> {
        });

        dialog.show();
    }

    private void dImprimir(String titulo, String msg, int IdLectura) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setIcon(R.drawable.imprimir);
        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setPositiveButton("Si", (dialog1, id) -> {
            if (prnLec.buildPrint(IdLectura, 0)) {
                app.doPrint();
            }
            regresar();
        });

        dialog.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog12, id) -> {
            dialog12.cancel();
        });

        dialog.show();
    }

    private void regresar() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
        regresar();
    }
}