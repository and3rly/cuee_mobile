package com.example.cuee_mobile.modelos.ruta;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeUsuarios_por_ruta;

import java.util.ArrayList;

public class UsuariosRutaModel {

    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "USUARIOS_POR_RUTA";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeUsuarios_por_ruta> lista = new ArrayList<>();

    public UsuariosRutaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;

        ins = Con.Ins; upd = Con.Upd;
    }

    public void getLista(String sq) {
        buscar(sel +" "+ sq);
    }

    public void getLista() {
        buscar(sel);
    }

    private void buscar(String sel) {
        clsBeUsuarios_por_ruta item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeUsuarios_por_ruta();

                    item.IdRuta = DT.getInt(0);
                    item.IdItinerario = DT.getInt(1);
                    item.IdUsuarioServicio = DT.getInt(2);
                    item.NombreItinerario = DT.getString(3);
                    item.Orden = DT.getInt(4);

                    lista.add(item);
                    DT.moveToNext();
                }
            }
            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("USUARIOS_POR_RUTA", "buscar: ", e );
        }
    }

    public boolean guardar(clsBeUsuarios_por_ruta obj) {
        try {
            ins.init(tabla);

            ins.add("IdRuta", obj.IdRuta);
            ins.add("IdItinerario", obj.IdItinerario);
            ins.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            ins.add("NombreItinerario", obj.NombreItinerario);
            ins.add("Orden", obj.Orden);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("USUARIOS_POR_RUTA", "guardar: ", e);
            return false;
        }
        return  true;
    }

}
