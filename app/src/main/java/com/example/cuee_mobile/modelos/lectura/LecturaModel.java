package com.example.cuee_mobile.modelos.lectura;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.auxLecturaServicio;
import com.example.cuee_mobile.clases.clsBeLectura;

import java.util.ArrayList;

public class LecturaModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "LECTURA";
    private String sql;
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeLectura> lista = new ArrayList<>();
    public ArrayList<auxLecturaServicio> serLectura = new ArrayList<>();
    public clsBeLectura objLectura = null;
    public int IdActualLectura;

    public LecturaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        buscar(sel +" "+ sq, true);
    }

    private void buscar(String sel, boolean uno) {
        clsBeLectura item;
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
                    objLectura = setLinea(DT);
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("LECTURA", "buscar: ", e );
        }
    }

    public ArrayList<auxLecturaServicio> getServiciosLectura() {
        Cursor DT;
        auxLecturaServicio item;
        try {
            serLectura.clear();
            sql = "SELECT A.IdInstalacion, A.IdUsuarioServicio, A.IdContador, A.Lectura_realizada, A.Lectura_correcta, B.Nombres, C.IdItinerario" +
                    " FROM SERVICIOS_INSTALADO A" +
                    " INNER JOIN USUARIOS_SERVICIO B ON A.IdUsuarioServicio = B.IdUsuarioServicio" +
                    " INNER JOIN USUARIOS_POR_RUTA C ON A.IdUsuarioServicio = C.IdUsuarioServicio";

            DT = Con.OpenDT(sql);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                while (!DT.isAfterLast()) {
                    item = new auxLecturaServicio();

                    item.IdInstalacion = DT.getInt(0);
                    item.IdUsuarioServicio = DT.getInt(1);
                    item.IdContador = DT.getString(2);
                    item.Lectura_realizada =  DT.getInt(3);
                    item.Lectura_correcta = DT.getInt(4) == 1 ? true:false;
                    item.Usuario = DT.getString(5);
                    item.IdItinerario = DT.getInt(6);

                    serLectura.add(item);
                    DT.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.e("LECTURA", "buscar: ", e );
        }

        return serLectura;
    }

    private clsBeLectura setLinea(Cursor DT) {
        clsBeLectura item = null;
        try {
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

        } catch (Exception e) {
            Log.e("LECTURA", "setLinea: ", e );
        }

        return item;
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
                IdActualLectura = obj.IdLectura;
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
            ins.add("con_hh", obj.Con_hh);
            ins.add("StatCom", obj.StatCom);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("LECTURA", "guardar: ", e);
            return false;
        }
        return  true;
    }

    public boolean actualizar(clsBeLectura obj) {
        try {
            upd.init(tabla);
            upd.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            upd.add("IdContador", obj.IdContador);
            upd.add("Fecha", obj.Fecha);
            upd.add("Lectura", obj.Lectura);
            upd.add("Consumo", obj.Consumo);
            upd.add("IdUsuario", obj.IdUsuario);
            upd.add("Fecha_creacion", obj.Fecha_creacion);
            upd.add("Lectura_kw", obj.Lectura_kw);
            upd.add("IdTecnico", obj.IdTecnico);
            upd.add("con_hh", obj.Con_hh);
            upd.Where("IdLectura = "+obj.IdLectura);
            db.execSQL(upd.sql());
        } catch (Exception e) {
            Log.e("LECTURA", "actualizar: ", e);
            return false;
        }
        return  true;
    }
}

