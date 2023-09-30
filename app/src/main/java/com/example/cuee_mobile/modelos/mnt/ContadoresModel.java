package com.example.cuee_mobile.modelos.mnt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeContadores;

public class ContadoresModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;

    public ContadoresModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;

        ins = Con.Ins; upd = Con.Upd;
    }

    public boolean guardar(clsBeContadores obj) {
        try {
            ins.init("USUARIOS_POR_RUTA");



            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("UsuariosPorRuta", "guardar: ", e);
            return false;
        }
        return  true;
    }
}

