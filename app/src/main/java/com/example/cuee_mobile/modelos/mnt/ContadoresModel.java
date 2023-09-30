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
    private String tabla;

    public ContadoresModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;

        tabla = "CONTADORES";
    }

    public boolean guardar(clsBeContadores obj) {
        try {
            ins.init(tabla);

            ins.add("IdContador", obj.IdContador);
            ins.add("IdMarca", obj.IdMarca);
            ins.add("Activo", obj.Activo);
            ins.add("No_marchamo", obj.No_marchamo);
            ins.add("Color", obj.Color);
            ins.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            ins.add("Fecha_Cambio", obj.Fecha_Cambio);
            ins.add("Fecha_Creacion", obj.Fecha_Creacion);
            ins.add("Lectura", obj.Lectura);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("UsuariosPorRuta", "guardar: ", e);
            return false;
        }
        return  true;
    }
}

