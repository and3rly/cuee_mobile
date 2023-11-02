package com.example.cuee_mobile.controladores.consultas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.adapter.CLecturaAdapter;
import com.example.cuee_mobile.adapter.ContadoresAdapter;
import com.example.cuee_mobile.clases.clsBeLectura;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.modelos.lectura.LecturaModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ConsultaLectura extends PBase {
    private ImageView btnRegresar, btnLimpiar, btnFechaDel, btnFechaAl;
    private DatePicker datePicker;
    private FloatingActionButton btnBuscar;
    private TextView lblRegistros;
    private EditText txtFiltro, txtFechaDel, txtFechaAl;
    private ListView lLecturas;
    private DatePickerDialog datePickerDialog;
    private ArrayList<clsBeLectura> clista =  new ArrayList<>();
    private CLecturaAdapter apdater;
    private LecturaModel lectura;
    private int dia, mes, anio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_lectura);
        super.SetBase();

        btnRegresar = findViewById(R.id.btnRegresar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        lblRegistros = findViewById(R.id.lblRegistros);
        txtFiltro = findViewById(R.id.txtFiltro);
        txtFechaDel = findViewById(R.id.txtFechaDel);
        txtFechaAl = findViewById(R.id.txtFechaAl);
        lLecturas = findViewById(R.id.lLecturas);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnFechaAl = findViewById(R.id.btnFechaAl);
        btnFechaDel = findViewById(R.id.btnFechaDel);

        lectura = new LecturaModel(this, Con, db);
        setHandlers();
        getLecturas();
    }

    private void setHandlers() {

        btnLimpiar.setOnClickListener(v -> {
            txtFiltro.setText("");
            txtFechaDel.setText("");
            txtFechaAl.setText("");
            getLecturas();
        });

        btnRegresar.setOnClickListener(v -> regresar());

        btnBuscar.setOnClickListener(v -> getLecturas());

        txtFiltro.setOnKeyListener((v, keyCode, event) -> {
            try {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            getLecturas();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        });

        btnFechaDel.setOnClickListener(v -> abrirCalendarioDel());
        btnFechaAl.setOnClickListener(v -> abrirCalendarioAl());
    }

    private void getLecturas() {
        Date fechaDel;
        Date fechaAl;
        String fecha1 = "", fecha2 = "";
        try {
            String termino = txtFiltro.getText().toString();

            if (!txtFechaDel.getText().toString().isEmpty() && !txtFechaAl.getText().toString().isEmpty()) {
                /*SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                fechaDel = formato.parse(txtFechaDel.getText().toString());
                fechaAl = formato.parse(txtFechaAl.getText().toString());

                if (fechaAl.before(fechaDel)) {
                    txtFechaAl.selectAll();
                    txtFechaAl.requestFocus();
                    helper.toast( "Fecha final debe ser mayor a la inicial");
                    return;
                }*/

                fecha1 = du.strFechaSinHora(txtFechaDel.getText().toString());
                fecha2 = du.strFechaSinHora(txtFechaAl.getText().toString());
            }

            clista.clear();
            lectura.getConsultaLectura(termino, fecha1, fecha2);

            if (lectura.lista.size() > 0) {
                clista = lectura.lista;

                apdater  = new CLecturaAdapter(this, clista);
                lLecturas.setAdapter(apdater);
                lblRegistros.setText("REGISTROS: "+clista.size());
            }

        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private void abrirCalendarioDel() {
        Calendar c = Calendar.getInstance();

        anio = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, (view, anio, mes, dia) -> {
            txtFechaDel.setText(new StringBuilder()
                    .append(dia).append("-").append(mes).append("-")
                    .append(anio).append(""));

        }, anio, mes, dia);

        datePickerDialog.show();
    }

    private void abrirCalendarioAl() {
        Calendar c = Calendar.getInstance();

        anio = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, (view, anio, mes, dia) -> {
            txtFechaAl.setText(new StringBuilder()
                    .append(dia).append("-").append(mes).append("-")
                    .append(anio).append(""));

        }, anio, mes, dia);

        datePickerDialog.show();
    }
    private void regresar() {
        super.finish();
    }
}