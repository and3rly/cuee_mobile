package com.example.cuee_mobile.modelos.meses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeMeses_mora_pagada;

public class MoraPagadaModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    public final String tabla;

    public MoraPagadaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;

        tabla = "MESES_MORA_PAGADA";
    }

    public boolean guardar(clsBeMeses_mora_pagada obj) {
        try {
            ins.init(tabla);

            ins.add("IdRecibo", obj.IdRecibo);
            ins.add("NoMes", obj.NoMes);
            ins.add("Anno", obj.Anno);
            ins.add("MoraPagada", obj.MoraPagada);
            ins.add("Anulado", obj.Anulado);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("MESES_MORA_PAGADA", "guardar: ", e);
            return false;
        }
        return  true;
    }
}
