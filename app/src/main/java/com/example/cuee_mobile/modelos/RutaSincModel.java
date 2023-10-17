package com.example.cuee_mobile.modelos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeRutaSincronizacion;

public class RutaSincModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "RUTA_SINCRONIZACION";
    private final String sel =  "SELECT * FROM " + tabla;

    public RutaSincModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;
    }

    public boolean guardar(clsBeRutaSincronizacion obj) {
        try {

            ins.init(tabla);

            ins.add("Ruta", obj.Ruta);
            ins.add("Fecha_carga_datos", obj.Fecha_carga_datos);
            ins.add("Fecha_envio_datos", obj.Fecha_envio_datos);
            ins.add("Fecha_servidor", obj.Fecha_servidor);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("RutaSincModel", "guardar: ", e);
        }

        return true;
    }
}
