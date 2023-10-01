package com.example.cuee_mobile.modelos.mnt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeRenglones;

import java.util.ArrayList;

public class RenglonesModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "RENGLONES";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeRenglones> lista = new ArrayList<>();

    public RenglonesModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        clsBeRenglones item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeRenglones();

                    item.Idrenglon = DT.getInt(0);
                    item.renglon = DT.getString(1);
                    item.especifico1 = DT.getString(2);
                    item.especifico2 = DT.getString(3);
                    item.concepto = DT.getString(4);
                    item.exento_IVA = Boolean.parseBoolean(String.valueOf(DT.getInt(5)));

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("RENGLONES", "buscar: ", e );
        }
    }

    public boolean guardar(clsBeRenglones obj) {
        try {
            ins.init(tabla);
            ins.add("Idrenglon", obj.Idrenglon);
            ins.add("renglon", obj.renglon);
            ins.add("especifico1", obj.especifico1);
            ins.add("especifico2", obj.especifico2);
            ins.add("concepto", obj.concepto);
            ins.add("exento_IVA", obj.exento_IVA);

            db.execSQL(ins.sql());
        } catch (Exception e) {
            Log.e("RENGLONES", "guardar: ", e);
        }

        return true;
    }
}
