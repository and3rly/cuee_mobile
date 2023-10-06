package com.example.cuee_mobile.controladores.consulta_contadores;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.adapter.ContadoresAdapter;
import com.example.cuee_mobile.clases.clsBeContadores;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.modelos.mnt.ContadoresModel;

import java.util.ArrayList;

public class ConsultaContadores extends PBase {

    private ImageView btnLimpiar;
    private EditText txtFiltro;
    private ListView lcontadores;
    private ContadoresModel contadores;
    private ArrayList<clsBeContadores> clista =  new ArrayList<>();
    private ContadoresAdapter ContadoresAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_contadores);
        super.SetBase();

        btnLimpiar = findViewById(R.id.btnLimpiar);
        txtFiltro = findViewById(R.id.txtFiltro);
        lcontadores = findViewById(R.id.lcontadores);

        contadores = new ContadoresModel(this, Con, db);
        setHandlers();
        getContadores();
    }

    private void setHandlers() {
        btnLimpiar.setOnClickListener(view -> txtFiltro.setText(""));
    }

    private void getContadores() {
        try {
            contadores.getLista();

            clista.clear();
            if (contadores.lista.size() > 0) {
                clista = contadores.lista;

                ContadoresAdapter  = new ContadoresAdapter(this, clista);
                lcontadores.setAdapter(ContadoresAdapter);
            }

        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }
}