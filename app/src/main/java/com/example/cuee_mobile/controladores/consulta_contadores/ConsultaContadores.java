package com.example.cuee_mobile.controladores.consulta_contadores;

import android.os.Bundle;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.controladores.PBase;

public class ConsultaContadores extends PBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_contadores);
        super.SetBase();
    }
}