package com.example.cuee_mobile.modelos.meses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeMeses_mora_proforma;

public class MoraProformaModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;

    public MoraProformaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;

        ins = Con.Ins; upd = Con.Upd;
    }

    public boolean guardar(clsBeMeses_mora_proforma obj) {
        try {
            ins.init("USUARIOS_POR_RUTA");



            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("UsuariosPorRuta", "guardar: ", e);
            return false;
        }
        return  true;
    }
}
