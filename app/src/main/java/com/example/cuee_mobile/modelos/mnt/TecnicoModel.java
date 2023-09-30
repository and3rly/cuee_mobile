package com.example.cuee_mobile.modelos.mnt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeTecnicos;

public class TecnicoModel {

    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;

    public TecnicoModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;

        ins = Con.Ins; upd = Con.Upd;
    }

    public boolean guardar(clsBeTecnicos obj) {
        try {

            ins.init("TECNICOS");

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
