package com.example.cuee_mobile.controladores.lectura;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.clases.clsBeLectura;
import com.example.cuee_mobile.clases.clsBeServicios_instalado;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.modelos.ServicioInsModel;
import com.example.cuee_mobile.modelos.lectura.LecturaModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.cuee_mobile.controladores.lectura.Lectura.auxLectura;

import java.util.Calendar;

public class LecturaForm extends PBase {

    private TextView lblTecnico, txtUsuario, txtContador, txtRuta, txtItinerario;
    private DatePicker datePicker;
    private ImageView btnRegresar, btnFecha;
    private EditText txtFecha, txtLectura, txtConsumo, txtLecKW;
    private DatePickerDialog datePickerDialog;
    private FloatingActionButton btnGuardar;
    private clsBeLectura objLectura;
    private LecturaModel lecturaModel;
    private ServicioInsModel serviciosModel;
    private int dia, mes, anio;
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
        datePicker = findViewById(R.id.datePicker);

        lecturaModel = new LecturaModel(this, Con, db);
        serviciosModel = new ServicioInsModel(this, Con, db);
        setDatos();
        setHandlers();
    }

    private void setDatos() {
        try {
            lblTecnico.setText("Técnico: "+ gl.tecnico.Nombre);

            txtRuta.setText("No. #" + gl.ruta.IdRuta);
            txtItinerario.setText("No. #" + auxLectura.IdItinerario);
            txtContador.setText(auxLectura.IdContador);
            txtUsuario.setText(auxLectura.IdUsuarioServicio + " - " + auxLectura.Usuario);

            setFechaActual();
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
                dialogo("Guardar lectura", "¿Está seguro de continuar?");
            }
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

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
            return false;
        }

        return  true;
    }

    private void guardar() {
        clsBeServicios_instalado item;
        try {
            objLectura = new clsBeLectura();

            objLectura.IdUsuarioServicio = auxLectura.IdUsuarioServicio;
            objLectura.IdContador = auxLectura.IdContador;
            objLectura.Fecha = txtFecha.getText().toString();
            objLectura.Consumo = Double.valueOf(txtConsumo.getText().toString());
            objLectura.Fecha_creacion = du.getFechaCompleta();
            objLectura.Lectura_kw = Double.valueOf(txtLecKW.getText().toString());
            objLectura.IdTecnico = gl.tecnico.IdTecnico;
            objLectura.Con_hh = true;

            if (lecturaModel.guardar(objLectura, false)) {

                item = new clsBeServicios_instalado();
                item.IdInstalacion = auxLectura.IdInstalacion;
                item.Lectura_realizada = 1;
                item.Lectura_correcta = 1;

                serviciosModel.actualizarServicio(item);
            }

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

    private void regresar() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
        regresar();
    }
}