package com.example.cuee_mobile.controladores.lectura;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private ImageView btnRegresar, btnLimpiar;
    private ListView lista_servicios;
    private LecturaAdapter adapter;
    private ArrayList<auxLecturaServicio> lista = new ArrayList<>();
    private ArrayList<auxLecturaServicio> auxLista = new ArrayList<>();
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
        btnLimpiar = findViewById(R.id.btnLimpiar);
        lblRegistros = findViewById(R.id.lblRegistros);
        lblTecnico = findViewById(R.id.lblTecnico);

        lectura = new LecturaModel(this, Con, db);
        lblTecnico.setText("Técnico: " + gl.tecnico.Nombre);
        gl.termino = "";
        setServiciosLectura();
        setHandlers();
    }

    private void setHandlers() {
        lista_servicios.setOnItemClickListener((parent, view, position, id) -> {
            auxLectura = new auxLecturaServicio();
            auxLectura = (auxLecturaServicio) lista_servicios.getItemAtPosition(position);
            procesaLectura();
        });

        btnRegresar.setOnClickListener(view -> regresar());

        btnLimpiar.setOnClickListener(v -> {
            txtFiltro.setText("");
            gl.termino = "";
        });
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
                    setServiciosLectura();
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
            for (auxLecturaServicio obj : lista) {

                if (obj.IdContador.toLowerCase().equals(termino.toLowerCase())) {
                    auxLista.add(obj);
                }
            }

            adapter = new LecturaAdapter(this, auxLista);
            lista_servicios.setAdapter(adapter);


            lblRegistros.setText("REGISTROS: " + auxLista.size());
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingMethod().getName() +" - "+ e.getMessage());
        }
    }

    private void procesaLectura() {
        startActivity(new Intent(this, LecturaForm.class));
        browse = 1;
    }
    private void setServiciosLectura() {
        try {
            lista.clear();
            lista = lectura.getServiciosLectura();

            if (lista.size() > 0) {
                adapter  = new LecturaAdapter(this, lista);
                lista_servicios.setAdapter(adapter);

                lblRegistros.setText("REGISTROS: " + lista.size());
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

    @Override
    protected void onResume() {
        super.onResume();

        if (browse == 1) {
            setServiciosLectura();
            browse = 0;
        }

        if (!gl.termino.isEmpty()) {
            txtFiltro.setText(gl.termino);
        }
    }
}