package com.example.cuee_mobile.modelos.mnt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeRenglones;

public class RenglonesModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "RENGLONES";

    public RenglonesModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;

        ins = Con.Ins; upd = Con.Upd;
    }

    public boolean guardar(clsBeRenglones obj) {
        try {
            ins.init(tabla);
            ins.add("Idrenglon", obj.Idrenglon);
            ins.add("renglon", obj.renglon);
            ins.add("especifico1", obj.especifico1);
            ins.add("especifico2", obj.especifico2);
            ins.add("concepto", obj.concepto);
            ins.add("exento_IVA", obj.exento_IVA);

            db.execSQL(ins.sql());
        } catch (Exception e) {
            Log.e("Tecnicos", "guardar: ", e);
        }

        return true;
    }
}
