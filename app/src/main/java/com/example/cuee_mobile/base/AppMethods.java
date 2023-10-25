package com.example.cuee_mobile.base;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AppMethods {

    private Context cont;
    public AppMethods(Context context) {
        cont = context;
    }

    public void doPrint() {
        printCilico();
    }
    private void printCilico() {
        try {
            Intent intent = cont.getPackageManager().getLaunchIntentForPackage("com.olc.printcilico");
            intent.putExtra("fname", "");
            intent.putExtra("copies",0);
            cont.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(cont, "El controlador de PrintCilico no está instalado", Toast.LENGTH_LONG).show();
        }
    }
}

