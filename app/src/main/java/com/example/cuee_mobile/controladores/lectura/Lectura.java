package com.example.cuee_mobile.controladores.lectura;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.adapter.LecturaAdapter;
import com.example.cuee_mobile.clases.auxLecturaServicio;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.modelos.lectura.LecturaModel;

import java.util.ArrayList;

public class Lectura extends PBase {

    private EditText txtFiltro;
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

        lectura = new LecturaModel(this, Con, db);
        setServiciosLectura();
    }

    private void setServiciosLectura() {
        try {
            lista = lectura.getServiciosLectura();

            if (lista.size() > 0) {
                adapter  = new LecturaAdapter(this, lista);
                lista_servicios.setAdapter(adapter);
            }
        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
        }
    }
}