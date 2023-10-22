package com.example.cuee_mobile.controladores.consultas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import java.util.ArrayList;

public class ConsultaLectura extends PBase {
    private ImageView btnRegresar, btnLimpiar;
    private FloatingActionButton btnBuscar;
    private TextView lblRegistros;
    private EditText txtFiltro;
    private ListView lLecturas;
    private ArrayList<clsBeLectura> clista =  new ArrayList<>();
    private CLecturaAdapter apdater;
    private LecturaModel lectura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_lectura);
        super.SetBase();

        btnRegresar = findViewById(R.id.btnRegresar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        lblRegistros = findViewById(R.id.lblRegistros);
        txtFiltro = findViewById(R.id.txtFiltro);
        lLecturas = findViewById(R.id.lLecturas);
        btnBuscar = findViewById(R.id.btnBuscar);

        lectura = new LecturaModel(this, Con, db);
        setHandlers();
        getLecturas();
    }

    private void setHandlers() {

        btnLimpiar.setOnClickListener(view -> txtFiltro.setText(""));

        btnRegresar.setOnClickListener(v -> regresar());

        btnBuscar.setOnClickListener(v -> getLecturas());
    }

    private void getLecturas() {
        try {
            String termino = txtFiltro.getText().toString();
            clista.clear();

            lectura.getConsultaLectura(termino);

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

    private void regresar() {
        super.finish();
    }
}