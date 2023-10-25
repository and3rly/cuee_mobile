package com.example.cuee_mobile.imprimir;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class clsDocumento {
    public clsDocBuilder rep;
    private Context cont;
    protected ArrayList<String> lines= new ArrayList<String>();
    public clsDocumento(Context context, int printwidth, String archivo) {
        cont = context;
        rep=new clsDocBuilder(cont, printwidth, archivo);
    }

    public boolean buildPrint(int id,int reimpres) {
        rep.clear();

        if (!buildHeader(id)) return false;
        if (!buildDetail()) return false;
        if (!buildFooter()) return false;

        if (!rep.save()) return false;

        return true;
    }

    private boolean buildHeader(int id) {

        lines.clear();

        try {
            loadHeadData();
            loadDocData(id);
        } catch (Exception e) {

            Toast.makeText(cont,"buildheader: "+e.getMessage(), Toast.LENGTH_SHORT).show();return false;
        }
        return true;
    }

    protected boolean buildDetail() {
        return true;
    }

    protected boolean buildFooter() {

        return true;
    }

    protected boolean loadDocData(int id) {
        return true;
    }

    protected boolean loadHeadData() {
        return true;
    }
}
