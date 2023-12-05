package com.example.cuee_mobile.modelos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeCorrelativo_proforma;
import com.example.cuee_mobile.clases.clsBeMeses_pago;
import com.example.cuee_mobile.clases.clsBeServicios_instalado;

import java.util.ArrayList;

public class MesesPagoModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "MESES_PAGO";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeMeses_pago> lista = new ArrayList<>();
    public clsBeMeses_pago obj = null;

    public MesesPagoModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;
    }

    public void getLista(String sq) {
        buscar(sel +" "+ sq, false);
    }

    public void getLinea(String sq) {
        buscar(sel + " " +sq, true);
    }

    public void getLista() {
        buscar(sel, false);
    }

    private void buscar(String sel, boolean uno) {
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
            Log.e("COLOR", "buscar: ", e );
        }
    }

    private clsBeMeses_pago setDatos(Cursor DT) {
        clsBeMeses_pago item = null;
        try {
            item = new clsBeMeses_pago();
            item.IdMesPago = DT.getInt(0);
            item.IdRecibo = DT.getInt(1);
            item.IdUsuarioServicio = DT.getInt(2);
            item.nomes = DT.getInt(3);
            item.anno = DT.getInt(4);

        } catch (Exception e) {
            Log.e("MESES PAGO", "setDatos: ", e);
        }

        return item;
    }

    public boolean guardar(clsBeMeses_pago obj) {
        try {
            ins.init(tabla);

            ins.add("IdRecibo", obj.IdRecibo);
            ins.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            ins.add("NoMes", obj.nomes);
            ins.add("Anno", obj.anno);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("Correlativo", "guardar: ", e);
            return false;
        }
        return  true;
    }

    public clsBeMeses_pago getUltimoPago(int usuario) {
        clsBeMeses_pago item = null;
        Cursor DT;
        String sq="";
        try {
            sq ="SELECT * FROM MESES_PAGO WHERE IdUsuarioServicio = " + usuario;
            DT = Con.OpenDT(sq);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                item = setDatos(DT);
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("RENGLONES", "getUsServicio: ", e );
        }

        return item;
    }
}
