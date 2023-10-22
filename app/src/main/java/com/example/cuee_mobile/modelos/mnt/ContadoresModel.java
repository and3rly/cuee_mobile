package com.example.cuee_mobile.modelos.mnt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeContadores;

import java.util.ArrayList;

public class ContadoresModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "CONTADORES";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeContadores> lista = new ArrayList<>();
    private String sql;
    public ContadoresModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        Cursor DT;
        try {
            lista.clear();
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                while (!DT.isAfterLast()) {
                    lista.add(setDatos(DT));
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("CONTADORES", "buscar: ", e );
        }
    }

    public void getReporteContadores() {
        Cursor DT;
        try {
            sql = "SELECT A.*, B.Nombre AS NColor, C.Nombre AS NMarca " +
                    "FROM CONTADORES A " +
                    "INNER JOIN COLOR B ON B.Idcolor = A.Color " +
                    "INNER JOIN MARCAS C ON C.IdMarca = A.IdMarca";

            lista.clear();
            DT = Con.OpenDT(sql);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                while (!DT.isAfterLast()) {
                    lista.add(setDatos(DT));
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("CONTADORES", "buscar: ", e );
        }
    }

    private clsBeContadores setDatos(Cursor DT) {
        clsBeContadores item = null;
        try {
            item = new clsBeContadores();

            item.IdContador = DT.getString(0);
            item.IdMarca = DT.getInt(1);
            item.Activo = Boolean.parseBoolean(DT.getString(2));
            item.No_marchamo = DT.getString(3);
            item.Color = DT.getString(4);
            item.IdUsuarioServicio = DT.getInt(5);
            item.Fecha_Cambio = DT.getString(6);
            item.Fecha_Creacion = DT.getString(7);
            item.Lectura = DT.getDouble(8);
            item.Ncolor = DT.getString(9);
            item.Nmarca = DT.getString(10);
        } catch (Exception e) {
            Log.e("CONTADORES", "guardar: ", e);
        }

        return item;
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
            Log.e("CONTADORES", "guardar: ", e);
            return false;
        }
        return  true;
    }

    public clsBeContadores getContador(String IdContador) {
        clsBeContadores contador = null;
        Cursor DT;
        try {
            sql = "SELECT * FROM CONTADORES WHERE IdContador = '"+IdContador+"' AND ACTIVO = 1";
            DT = Con.OpenDT(sql);

            if (DT.getCount() > 0) {
                DT.moveToFirst();
                contador = setDatos(DT);
            }
        } catch (Exception e) {
            Log.e("LECTURA", "getLecturaAnterior: ", e);
        }
        return contador;
    }
}

