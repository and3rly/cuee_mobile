package com.example.cuee_mobile.modelos.mnt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeTecnicos;

import java.util.ArrayList;

public class TecnicoModel {

    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "TECNICOS";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeTecnicos> lista = new ArrayList<>();

    public TecnicoModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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

    public void buscar(String sel) {
        clsBeTecnicos item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeTecnicos();

                    item.IdTecnico = DT.getInt(0);
                    item.Nombre = DT.getString(1);
                    item.Dpi = DT.getString(2);
                    item.Direccion = DT.getString(3);
                    item.Activo = Boolean.parseBoolean(DT.getString(4));
                    item.Codigo = DT.getString(5);
                    item.Clave = DT.getString(6);

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("RUTA_LECTURA", "buscar: ", e );
        }
    }

    public boolean guardar(clsBeTecnicos obj) {
        try {

            ins.init(tabla);

            ins.add("IdTecnico", obj.IdTecnico);
            ins.add("Nombre", obj.Nombre);
            ins.add("Dpi", obj.Dpi);
            ins.add("Direccion", obj.Direccion);
            ins.add("Activo", obj.Activo);
            ins.add("Codigo", obj.Codigo);
            ins.add("Clave", obj.Clave);

            db.execSQL(ins.sql());
        } catch (Exception e) {
            Log.e("Tecnicos", "guardar: ", e);
        }

        return true;
    }

}
