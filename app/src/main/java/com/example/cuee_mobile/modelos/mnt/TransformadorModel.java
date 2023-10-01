package com.example.cuee_mobile.modelos.mnt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeTransformadores;

public class TransformadorModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "TRANSFORMADORES";

    public TransformadorModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;

        ins = Con.Ins; upd = Con.Upd;
    }

    public boolean guardar(clsBeTransformadores obj) {
        try {
            ins.init(tabla);
            ins.add("IdTransformador", obj.IdTransformador);
            ins.add("Nombre", obj.Nombre);
            ins.add("Activo", obj.Activo);

            db.execSQL(ins.sql());
        } catch (Exception e) {
            Log.e("Tecnicos", "guardar: ", e);
        }

        return true;
    }
}
