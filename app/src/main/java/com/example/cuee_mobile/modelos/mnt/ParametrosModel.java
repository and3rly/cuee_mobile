package com.example.cuee_mobile.modelos.mnt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeParametros;

import java.util.ArrayList;

public class ParametrosModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "PARAMETROS";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeParametros> lista = new ArrayList<>();

    public ParametrosModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        clsBeParametros item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeParametros();

                    item.idparametro = DT.getInt(0);
                    item.parametro = DT.getString(1);
                    item.valor = DT.getDouble(2);

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("PARAMETROS", "buscar: ", e );
        }
    }

    public boolean guardar(clsBeParametros obj) {
        try {
            ins.init(tabla);

            ins.add("idparametro", obj.idparametro);
            ins.add("parametro", obj.parametro);
            ins.add("valor", obj.valor);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("PARAMETROS", "guardar: ", e);
            return false;
        }
        return  true;
    }
}
