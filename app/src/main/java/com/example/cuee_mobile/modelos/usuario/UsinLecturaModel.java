package com.example.cuee_mobile.modelos.usuario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeUsuario_sin_lectura;

import java.util.ArrayList;

public class UsinLecturaModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla ="USUARIO_SIN_LECTURA";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeUsuario_sin_lectura> lista = new ArrayList<>();
    public clsBeUsuario_sin_lectura obj = null;

    public UsinLecturaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        clsBeUsuario_sin_lectura item;
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
                    obj = setDatos(DT);
                }
            }
            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("USUARIOS_SERVICIO", "buscar: ", e );
        }
    }

    private clsBeUsuario_sin_lectura setDatos(Cursor DT) {
        clsBeUsuario_sin_lectura item = null;
        try {
            item = new clsBeUsuario_sin_lectura();

            item.IdUsuarioSinLectura = DT.getInt(0);
            item.IdRuta = DT.getInt(1);
            item.IdItinerario = DT.getInt(2);
            item.IdTecnico = DT.getInt(3);
            item.IdUsuarioServicio = DT.getInt(4);
            item.IdRazonNoLectura = DT.getInt(5);
            item.Usuario_Crea = DT.getInt(6);
            item.Fecha_Crea = DT.getString(7);
            item.Usuario_Modifica = DT.getInt(8);
            item.Fecha_Modifica = DT.getString(9);
            item.StatCom = DT.getInt(10);

        } catch (Exception e) {
            Log.e("USUARIOS_SERVICIO", "setDatos: ", e );
        }

        return item;
    }

    public boolean guardarNoLectura(clsBeUsuario_sin_lectura o) {
        boolean exito = false;

        try {
            ins.init(tabla);

            ins.add("IdRuta", o.IdRuta);
            ins.add("IdItinerario", o.IdItinerario);
            ins.add("IdTecnico", o.IdTecnico);
            ins.add("IdUsuarioServicio", o.IdUsuarioServicio);
            ins.add("IdRazonNoLectura", o.IdRazonNoLectura);
            ins.add("Usuario_crea", o.Usuario_Crea);
            ins.add("Fecha_crea", o.Fecha_Crea);
            ins.add("Usuario_Modifica", o.Usuario_Modifica);
            ins.add("Fecha_Modifica", o.Fecha_Modifica);
            ins.add("StatCom", o.StatCom);

            db.execSQL(ins.sql());
            exito = true;

        } catch (Exception e) {
            Log.e("CatalogoModel", "guardarNoLectura: ", e);
        }

        return exito;
    }

    public void actualizaEstado(int id) {
        String sq = "";
        try {
            sq = "UPDATE USUARIO_SIN_LECTURA SET StatCom = 1 WHERE IdUsuarioSinLectura = " + id;
            db.execSQL(sq);

        } catch (Exception e) {
            Log.e("UsuarioSinLectura", "actualizaEstado: ", e);
        }
    }
}
