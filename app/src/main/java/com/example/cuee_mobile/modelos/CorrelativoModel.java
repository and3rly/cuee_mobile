package com.example.cuee_mobile.modelos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeColor;
import com.example.cuee_mobile.clases.clsBeCorrelativo_proforma;

import java.util.ArrayList;

public class CorrelativoModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "CORRELATIVO_PROFORMA";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeCorrelativo_proforma> lista = new ArrayList<>();

    public CorrelativoModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        clsBeCorrelativo_proforma item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeCorrelativo_proforma();

                    item.idCorrelativoProforma = DT.getInt(0);
                    item.serie = DT.getString(1);
                    item.inicial = DT.getInt(2);
                    item.cfinal = DT.getInt(3);
                    item.actual = DT.getInt(4);
                    item.idtecnico = DT.getInt(5);
                    item.activo = DT.getInt(6) == 1 ? true:false;
                    item.fec_agr = DT.getString(7);
                    item.fec_mod = DT.getString(8);
                    item.user_mod = DT.getString(9);
                    item.user_agr = DT.getString(10);

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("COLOR", "buscar: ", e );
        }
    }

    public clsBeCorrelativo_proforma getCorrelativo() {
        clsBeCorrelativo_proforma item = null;
        String sql = "";
        Cursor DT;
        try {
            sql = "SELECT * FROM CORRELATIVO_PROFORMA WHERE ACTIVO = 1";
            DT = Con.OpenDT(sql);

            if (DT.getCount() > 0) {
                DT.moveToFirst();
                    item = new clsBeCorrelativo_proforma();

                    item.idCorrelativoProforma = DT.getInt(0);
                    item.serie = DT.getString(1);
                    item.inicial = DT.getInt(2);
                    item.cfinal = DT.getInt(3);
                    item.actual = DT.getInt(4);
                    item.idtecnico = DT.getInt(5);
                    item.activo = DT.getInt(6) == 1 ? true:false;
                    item.fec_agr = DT.getString(7);
                    item.fec_mod = DT.getString(8);
                    item.user_mod = DT.getString(9);
                    item.user_agr = DT.getString(10);
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("COLOR", "buscar: ", e );
        }

        return item;
    }

    public boolean guardar(clsBeCorrelativo_proforma obj) {
        try {
            ins.init(tabla);

            ins.add("IdCorrelativoProforma", obj.idCorrelativoProforma);
            ins.add("Serie", obj.serie);
            ins.add("Inicial", obj.inicial);
            ins.add("Final", obj.cfinal);
            ins.add("Actual", obj.actual);
            ins.add("IdTecnico", obj.idtecnico);
            ins.add("Activo", obj.activo);
            ins.add("Fec_agr", obj.fec_agr);
            ins.add("Fec_mod", obj.fec_mod);
            ins.add("User_mod", obj.user_mod);
            ins.add("User_agr", obj.user_agr);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("Correlativo", "guardar: ", e);
            return false;
        }
        return  true;
    }

    public void actualizaEstado(int id) {
        String sq = "";
        try {
            sq = "UPDATE CORRELATIVO_PROFORMA SET StatCom = 1 WHERE IdCorrelativoProforma = " + id;
            db.execSQL(sq);

        } catch (Exception e) {
            Log.e("CorrelativoProforma", "actualizaEstado: ", e);
        }
    }
}
