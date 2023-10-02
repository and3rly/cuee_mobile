package com.example.cuee_mobile.modelos.ruta;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeRuta_lectura;

import java.util.ArrayList;

public class RutaLecturaModel {

    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "RUTA_LECTURA";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeRuta_lectura> lista = new ArrayList<>();
    public clsBeRuta_lectura objRutaLec = null;

    public RutaLecturaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        buscar(sel , true);
    }

    private void buscar(String sel, boolean uno) {
        clsBeRuta_lectura item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                if (!uno) {
                    lista.clear();
                    while (!DT.isAfterLast()) {

                        lista.add(setLinea(DT));
                        DT.moveToNext();
                    }
                } else {
                    objRutaLec = setLinea(DT);
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("RUTA_LECTURA", "buscar: ", e );
        }
    }

    private clsBeRuta_lectura setLinea(Cursor DT) {
        clsBeRuta_lectura item = null;
        try {
            item = new clsBeRuta_lectura();
            item.IdRuta = DT.getInt(0);
            item.Nombre = DT.getString(1);
            item.Activo = Boolean.parseBoolean(DT.getString(2));
            item.IdTecnicoDef = DT.getInt(3);
        } catch (Exception e) {
            Log.e("Institucion", "buscar: ", e );
        }

        return item;
    }

    public boolean guardar(clsBeRuta_lectura obj) {
        try {
            ins.init(tabla);
            ins.add("IdRuta", obj.IdRuta);
            ins.add("Nombre", obj.Nombre);
            ins.add("Activo", obj.Activo);
            ins.add("IdTecnicoDef", obj.IdTecnicoDef);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("RUTA_LECTURA", "guardar: ", e);
            return false;
        }
        return  true;
    }

}
