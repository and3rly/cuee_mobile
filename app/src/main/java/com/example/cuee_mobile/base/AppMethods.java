package com.example.cuee_mobile.base;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;

public class AppMethods {

    private Context cont;
    private VarGlobal gl;
    public AppMethods(Context context, VarGlobal varGl) {
        gl = varGl;
        cont = context;
    }

    public void doPrint() {
        printCilico();
    }
    private void printCilico() {
        try {
            Intent intent = cont.getPackageManager().getLaunchIntentForPackage("com.olc.printcilico");
            intent.putExtra("fname", cont.getFilesDir()+"/lectura.txt");
            intent.putExtra("copies",0);
            cont.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(cont, "El controlador de PrintCilico no está instalado", Toast.LENGTH_LONG).show();
        }
    }
}

