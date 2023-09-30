package com.example.cuee_mobile.modelos.ruta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeRuta_lectura;

public class RutaLecturaModel {

    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;

    public RutaLecturaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;

        ins = Con.Ins; upd = Con.Upd;
    }

    public boolean guardar(clsBeRuta_lectura obj) {
        try {
            ins.init("RUTA_LECTURA");

            ins.add("IdRuta", obj.IdRuta);
            ins.add("Nombre", obj.Nombre);
            ins.add("Activo", obj.Activo);
            ins.add("IdTecnicoDef", obj.IdTecnicoDef);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("RutaLectura", "guardar: ", e);
            return false;
        }
        return  true;
    }

}
