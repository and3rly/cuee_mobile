package com.example.cuee_mobile.modelos.mnt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeRazon_no_lectura;

import java.util.ArrayList;

public class RazonNoCierreModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "RAZON_NO_LECTURA";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeRazon_no_lectura> lista = new ArrayList<>();

    public RazonNoCierreModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;
    }

    public void getLista(String sq) {
        buscar(sel +" "+ sq);
    }

    public void getLista() {
        buscar(sel);
    }

    private void buscar(String sel) {
        clsBeRazon_no_lectura item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeRazon_no_lectura();

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("RazonNoCierre", "buscar: ", e );
        }
    }

    public boolean guardar(clsBeRazon_no_lectura obj) {
        try {
            ins.init(tabla);

            ins.add("IdRazonNoLectura", obj.IdRazonNoLectura);
            ins.add("Nombre", obj.Nombre);
            ins.add("Activo", obj.Activo);
            ins.add("IdUsuario_crea", obj.IdUsuario_crea);
            ins.add("fecha_crea", obj.fecha_crea);
            ins.add("IdUsuario_modifica", obj.IdUsuario_modifica);
            ins.add("fecha_modifica", obj.fecha_modifica);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("RazonNoCierre", "guardar: ", e);
            return false;
        }
        return  true;
    }
}
