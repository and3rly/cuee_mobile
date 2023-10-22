package com.example.cuee_mobile.controladores.consultas;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.adapter.ContadoresAdapter;
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

        Handler timer = new Handler();
        Runnable runner = this::getContadores;
        timer.postDelayed(runner, 500);
    }

    private void setHandlers() {

        btnLimpiar.setOnClickListener(view -> txtFiltro.setText(""));

        btnRegresar.setOnClickListener(v -> regresar());
    }

    private void getContadores() {
        try {
            contadores.getReporteContadores();

            clista.clear();
            if (contadores.lista.size() > 0) {
                clista = contadores.lista;

                for (clsBeContadores obj: clista) {
                    if (obj.Fecha_Creacion.contains("T")) {
                        obj.Fecha_Creacion = du.strFecha(obj.Fecha_Creacion);
                    }

                    if (obj.Fecha_Cambio.contains("T")) {
                        obj.Fecha_Cambio = du.strFecha(obj.Fecha_Cambio);
                    }
                }

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