package com.example.cuee_mobile.modelos.mnt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeParametros;
public class ParametrosModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private String tabla;

    public ParametrosModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;

        tabla = "PARAMETROS";
    }

    public boolean guardar(clsBeParametros obj) {
        try {
            ins.init(tabla);

            ins.add("idparametro", obj.idparametro);
            ins.add("parametro", obj.parametro);
            ins.add("valor", obj.valor);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("Parametros", "guardar: ", e);
            return false;
        }
        return  true;
    }
}
