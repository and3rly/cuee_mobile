package com.example.cuee_mobile.controladores.consultas;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.adapter.ContadoresAdapter;
import com.example.cuee_mobile.adapter.LecturaAdapter;
import com.example.cuee_mobile.clases.auxLecturaServicio;
import com.example.cuee_mobile.clases.clsBeContadores;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.modelos.mnt.ContadoresModel;

import java.util.ArrayList;

public class ConsultaContadores extends PBase {

    private ImageView btnLimpiar, btnRegresar;
    private EditText txtFiltro;
    private TextView lblRegistros;
    private ListView lcontadores;
    private ContadoresModel contadores;
    private ArrayList<clsBeContadores> clista =  new ArrayList<>();
    private ContadoresAdapter ContadoresAdapter;
    private ProgressDialog progress;
    private ArrayList<clsBeContadores> auxLista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_contadores);
        super.SetBase();

        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnRegresar = findViewById(R.id.btnRegresar);
        txtFiltro = findViewById(R.id.txtFiltro);
        lcontadores = findViewById(R.id.lcontadores);
        lblRegistros = findViewById(R.id.lblRegistros);

        contadores = new ContadoresModel(this, Con, db);
        setHandlers();

        getContadores();
    }

    private void setHandlers() {

        btnLimpiar.setOnClickListener(view -> txtFiltro.setText(""));

        btnRegresar.setOnClickListener(v -> regresar());

        txtFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtFiltro.getText().toString().isEmpty()) {
                    gl.termino = txtFiltro.getText().toString();
                    Filtro();
                } else {
                    gl.termino = "";
                    getContadores();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void Filtro() {
        String termino  = gl.termino;

        try {

            auxLista.clear();
            for (clsBeContadores obj : clista) {

                if (obj.IdContador.toLowerCase().contains(termino.toLowerCase()) ||
                        obj.Nmarca.toLowerCase().contains(termino.toLowerCase()) ||
                        obj.Ncolor.toLowerCase().contains(termino.toLowerCase())) {
                    auxLista.add(obj);
                }
            }

            ContadoresAdapter = new ContadoresAdapter(this, auxLista);
            lcontadores.setAdapter(ContadoresAdapter);
            lblRegistros.setText("REGISTROS: " + auxLista.size());

        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingMethod().getName() +" - "+ e.getMessage());
        }
    }

    private void getContadores() {
        try {
            clista.clear();
            contadores.getReporteContadores();

            if (contadores.lista.size() > 0) {
                clista = contadores.lista;

                ContadoresAdapter  = new ContadoresAdapter(this, clista);
                lcontadores.setAdapter(ContadoresAdapter);

                lblRegistros.setText("REGISTROS: "+clista.size());
            }

        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
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