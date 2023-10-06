package com.example.cuee_mobile.controladores.lectura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.adapter.LecturaAdapter;
import com.example.cuee_mobile.clases.auxLecturaServicio;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.modelos.lectura.LecturaModel;

import java.util.ArrayList;

public class Lectura extends PBase {

    private EditText txtFiltro;
    private TextView lblRegistros, lblTecnico;
    private ImageView btnRegresar;
    private ListView lista_servicios;
    private LecturaAdapter adapter;
    private ArrayList<auxLecturaServicio> lista = new ArrayList<>();
    public static auxLecturaServicio auxLectura = new auxLecturaServicio();
    private LecturaModel lectura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura);
        super.SetBase();

        txtFiltro = findViewById(R.id.txtFiltro);
        lista_servicios = findViewById(R.id.lista_servicios);
        btnRegresar = findViewById(R.id.btnRegresar);
        lblRegistros = findViewById(R.id.lblRegistros);
        lblTecnico = findViewById(R.id.lblTecnico);

        lectura = new LecturaModel(this, Con, db);
        lblTecnico.setText("Técnico: " + gl.tecnico.Nombre);
        setServiciosLectura();
        setHandlers();
    }

    private void setHandlers() {
        lista_servicios.setOnItemClickListener((parent, view, position, id) -> {
            try {
                adapter.setSelectedIndex(position);

                auxLectura = new auxLecturaServicio();
                auxLectura = (auxLecturaServicio) lista_servicios.getItemAtPosition(position);
                startActivity(new Intent(this, LecturaForm.class));

            } catch (Exception e) {
                helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
            }
        });

        btnRegresar.setOnClickListener(view -> regresar());
    }

    private void setServiciosLectura() {
        try {
            lista = lectura.getServiciosLectura();

            if (lista.size() > 0) {
                adapter  = new LecturaAdapter(this, lista);
                lista_servicios.setAdapter(adapter);
                int reg = lista.size() - 1;

                lblRegistros.setText("REGISTROS: " + reg);
            }
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