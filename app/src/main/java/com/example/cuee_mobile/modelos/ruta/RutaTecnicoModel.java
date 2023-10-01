package com.example.cuee_mobile.modelos.ruta;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeRuta_tecnico;

import java.util.ArrayList;

public class RutaTecnicoModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "RUTA_TECNICO";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeRuta_tecnico> lista = new ArrayList<>();

    public RutaTecnicoModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        clsBeRuta_tecnico item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeRuta_tecnico();

                    item.IdRutaTecnico = DT.getInt(0);
                    item.IdTecnico = DT.getInt(1);
                    item.IdRuta = DT.getInt(2);
                    item.Activo = Boolean.parseBoolean(DT.getString(3));
                    item.Fecha_Agr = DT.getString(4);
                    item.User_Agr = DT.getInt(5);
                    item.Fecha_Mod = DT.getString(6);
                    item.User_Mod = DT.getInt(7);

                    lista.add(item);
                    DT.moveToNext();
                }
            }
            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("RUTA_TECNICO", "buscar: ", e );
        }
    }

    public boolean guardar(clsBeRuta_tecnico obj) {
        try {

            ins.init(tabla);

            ins.add("IdRutaTecnico", obj.IdRutaTecnico);
            ins.add("IdTecnico", obj.IdTecnico);
            ins.add("IdRuta", obj.IdRuta);
            ins.add("Activo", obj.Activo);
            ins.add("Fecha_Agr", obj.Fecha_Agr);
            ins.add("User_Agr", obj.User_Agr);
            ins.add("Fecha_Mod", obj.Fecha_Mod);
            ins.add("User_Mod", obj.User_Mod);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("RutaTecnicos", "guardar: ", e);
        }

        return true;
    }
}
