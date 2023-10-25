package com.example.cuee_mobile.modelos.usuario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeUsuarios_servicio;

import java.util.ArrayList;

public class UsrServicioModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla ="USUARIOS_SERVICIO";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeUsuarios_servicio> lista = new ArrayList<>();
    public clsBeUsuarios_servicio objUsuarioServicio = null;

    public UsrServicioModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;
    }

    public void getLista(String sq) {
        buscar(sel +" "+ sq, false);
    }

    public void getLista() {
        buscar(sel, false);
    }

    public void getLinea() {
        buscar(sel, true);
    }
    public void getLinea(String sq) {
        buscar(sel + " " +sq, true);
    }

    private void buscar(String sel, boolean uno) {
        clsBeUsuarios_servicio item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                if (!uno) {
                    lista.clear();
                    while (!DT.isAfterLast()) {
                        lista.add(setDatos(DT));
                        DT.moveToNext();
                    }
                } else {
                    objUsuarioServicio = setDatos(DT);
                }
            }
            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("USUARIOS_SERVICIO", "buscar: ", e );
        }
    }

    private clsBeUsuarios_servicio setDatos(Cursor DT) {
        clsBeUsuarios_servicio item = null;
        try {
            item = new clsBeUsuarios_servicio();

            item.IdUsuarioServicio = DT.getInt(0);
            item.DPI = DT.getString(1);
            item.NIT = DT.getString(2);
            item.Nombres = DT.getString(3);
            item.Telefono = DT.getString(4);
            item.IdUsuario = DT.getInt(5);
            item.Fecha_creacion = DT.getString(6);
            item.Idusuario_modifica = DT.getInt(7);
            item.Fecha_modificacion = DT.getString(8);
            item.Activo = Boolean.parseBoolean(String.valueOf(DT.getString(9)));
            item.Correo_electronico = DT.getString(10);
            item.Exento_IVA = Boolean.parseBoolean(String.valueOf(DT.getInt(11)));
        } catch (Exception e) {
            Log.e("USUARIOS_SERVICIO", "setDatos: ", e );
        }

        return item;
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
