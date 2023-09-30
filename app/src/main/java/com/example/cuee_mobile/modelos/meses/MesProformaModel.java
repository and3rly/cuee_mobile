package com.example.cuee_mobile.modelos.meses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeMeses_proforma;

public class MesProformaModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla;

    public MesProformaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;

        tabla ="MESES_PROFORMA";
    }

    public boolean guardar(clsBeMeses_proforma obj) {
        try {
            ins.init(tabla);

            ins.add("idproformadet", obj.idproformadet);
            ins.add("idproforma", obj.idproforma);
            ins.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            ins.add("nomes", obj.nomes);
            ins.add("mes", obj.mes);
            ins.add("idrenglon", obj.idrenglon);
            ins.add("descripcion", obj.descripcion);
            ins.add("cantidad", obj.cantidad);
            ins.add("anno", obj.anno);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("Meses Proforma", "guardar: ", e);
            return false;
        }
        return  true;
    }
}
