package com.example.cuee_mobile.modelos.ruta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeUsuarios_por_ruta;

public class UsuariosRutaModel {

    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;

    public UsuariosRutaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;

        ins = Con.Ins; upd = Con.Upd;
    }

    public boolean guardar(clsBeUsuarios_por_ruta obj) {
        try {
            ins.init("USUARIOS_POR_RUTA");

            ins.add("IdRuta", obj.IdRuta);
            ins.add("IdItinerario", obj.IdItinerario);
            ins.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            ins.add("NombreItinerario", obj.NombreItinerario);
            ins.add("Orden", obj.Orden);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("UsuariosPorRuta", "guardar: ", e);
            return false;
        }
        return  true;
    }

}
