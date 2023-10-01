package com.example.cuee_mobile.modelos.lectura;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeLectura;

public class LecturaModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla;
    private String sql="";

    public LecturaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;

        tabla ="LECTURA";
    }

    public boolean guardar(clsBeLectura obj, boolean rec) {
        Cursor DT;
        try {
            ins.init(tabla);

            if (!rec) {
                sql = "SELECT MAX(IdLectura) FROM " + tabla;
                DT = Con.OpenDT(sql);
                DT.moveToFirst();

                obj.IdLectura = DT.getInt(0) + 1;
            }

            ins.add("IdLectura", obj.IdLectura);
            ins.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            ins.add("IdContador", obj.IdContador);
            ins.add("Fecha", obj.Fecha);
            ins.add("Lectura", obj.Lectura);
            ins.add("Consumo", obj.Consumo);
            ins.add("IdUsuario", obj.IdUsuario);
            ins.add("Fecha_creacion", obj.Fecha_creacion);
            ins.add("Lectura_kw", obj.Lectura_kw);
            ins.add("IdTecnico", obj.IdTecnico);
            ins.add("StatCom", obj.StatCom);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("LECTURA", "guardar: ", e);
            return false;
        }
        return  true;
    }
}

