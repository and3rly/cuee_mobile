package com.example.cuee_mobile.controladores.lectura;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.controladores.PBase;
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
        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
        }
    }

    private void setHandlers() {
        btnRegresar.setOnClickListener(view -> regresar());

        btnFecha.setOnClickListener(v -> abrirCalendario());
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
        }, anio, mes, dia);

        datePickerDialog.show();
    }

    private void regresar() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
        regresar();
    }
}