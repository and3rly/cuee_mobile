package com.example.cuee_mobile.controladores.lectura;

import android.os.Bundle;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.controladores.PBase;

public class Lectura extends PBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura);
        super.SetBase();
    }
}