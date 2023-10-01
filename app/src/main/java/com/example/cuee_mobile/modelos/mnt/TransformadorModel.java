package com.example.cuee_mobile.modelos.mnt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeTransformadores;

import java.util.ArrayList;

public class TransformadorModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "TRANSFORMADORES";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeTransformadores> lista = new ArrayList<>();

    public TransformadorModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        clsBeTransformadores item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeTransformadores();

                    item.IdTransformador = DT.getInt(0);
                    item.Nombre = DT.getString(1);
                    item.Activo = Boolean.parseBoolean(String.valueOf(DT.getInt(2)));

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("TRANSFORMADORES", "buscar: ", e );
        }
    }

    public boolean guardar(clsBeTransformadores obj) {
        try {
            ins.init(tabla);
            ins.add("IdTransformador", obj.IdTransformador);
            ins.add("Nombre", obj.Nombre);
            ins.add("Activo", obj.Activo);

            db.execSQL(ins.sql());
        } catch (Exception e) {
            Log.e("TRANSFORMADORES", "guardar: ", e);
        }

        return true;
    }
}
