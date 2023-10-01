package com.example.cuee_mobile.modelos.lectura;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeLectura;

import java.util.ArrayList;

public class LecturaModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "LECTURA";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeLectura> lista = new ArrayList<>();

    public LecturaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        clsBeLectura item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeLectura();

                    item.IdLectura = DT.getInt(0);
                    item.IdUsuarioServicio = DT.getInt(1);
                    item.IdContador = DT.getString(2);
                    item.Fecha = DT.getString(3);
                    item.Lectura = DT.getDouble(4);
                    item.Consumo = DT.getDouble(5);
                    item.IdUsuario = DT.getInt(6);
                    item.Fecha_creacion = DT.getString(7);
                    item.Lectura_kw = DT.getDouble(8);
                    item.IdTecnico = DT.getInt(9);
                    item.StatCom = DT.getString(10);

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("LECTURA", "buscar: ", e );
        }
    }

    public boolean guardar(clsBeLectura obj, boolean rec) {
        Cursor DT;
        try {
            ins.init(tabla);

            if (!rec) {
                String sql = "SELECT MAX(IdLectura) FROM " + tabla;
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

