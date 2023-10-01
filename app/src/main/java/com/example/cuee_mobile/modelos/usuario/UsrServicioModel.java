package com.example.cuee_mobile.modelos.usuario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeUsuarios_servicio;

public class UsrServicioModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla;

    public UsrServicioModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;

        tabla ="USUARIOS_SERVICIO";
    }

    public boolean guardar(clsBeUsuarios_servicio obj) {
        try {
            ins.init(tabla);
            ins.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            ins.add("DPI", obj.DPI);
            ins.add("NIT", obj.NIT);
            ins.add("Nombres", obj.Nombres);
            ins.add("Telefono", obj.Telefono);
            ins.add("IdUsuario", obj.IdUsuario);
            ins.add("Fecha_creacion", obj.Fecha_creacion);
            ins.add("Idusuario_modifica", obj.Idusuario_modifica);
            ins.add("Fecha_modificacion", obj.Fecha_modificacion);
            ins.add("Activo", obj.Activo);
            ins.add("Correo_electronico", obj.Correo_electronico);
            ins.add("Exento_IVA", obj.Exento_IVA);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("USUARIOS_SERVICIO", "guardar: ", e);
            return false;
        }
        return  true;
    }
}
