package com.example.cuee_mobile.modelos.ruta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeRuta_tecnico;

public class RutaTecnicoModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla;

    public RutaTecnicoModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;

        tabla = "RUTA_TECNICO";
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
