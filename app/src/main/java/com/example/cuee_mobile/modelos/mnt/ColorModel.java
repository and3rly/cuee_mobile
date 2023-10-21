package com.example.cuee_mobile.modelos.mnt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeColor;

import java.util.ArrayList;

public class ColorModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "COLOR";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeColor> lista = new ArrayList<>();

    public ColorModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        clsBeColor item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeColor();

                    item.Idcolor = DT.getInt(0);
                    item.Nombre = DT.getString(1);

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("COLOR", "buscar: ", e );
        }
    }

    public boolean guardar(clsBeColor obj) {
        try {
            ins.init(tabla);

            ins.add("Idcolor", obj.Idcolor);
            ins.add("Nombre", obj.Nombre);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("COLOR", "guardar: ", e);
            return false;
        }
        return  true;
    }
}
