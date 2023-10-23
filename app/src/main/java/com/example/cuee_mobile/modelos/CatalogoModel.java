package com.example.cuee_mobile.modelos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;

public class CatalogoModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    private String sql;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;

    public CatalogoModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;
    }

    public int getItinerario() {
        int itinerario = 0;
        Cursor DT;
        try {
            sql = "SELECT IdItinerario FROM USUARIOS_POR_RUTA LIMIT 1";
            DT = Con.OpenDT(sql);

            if (DT.getCount() > 0) {
                DT.moveToFirst();
                itinerario = DT.getInt(0);
            }
            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("USUARIOS_POR_RUTA", "getItinerario: ", e);
        }
        return itinerario;
    }

    public void eliminarDatosTalbas() {
        try {
            sql = "DELETE FROM LECTURA";
            db.execSQL(sql);

            sql = "DELETE FROM SERVICIOS_INSTALADO";
            db.execSQL(sql);

            sql = "DELETE FROM USUARIOS_SERVICIO";
            db.execSQL(sql);

            sql = "DELETE FROM PROFORMA";
            db.execSQL(sql);

            sql = "DELETE FROM PROFORMA_DETALLE";
            db.execSQL(sql);

            sql = "DELETE FROM TMP_PROFORMA_USUARIO";
            db.execSQL(sql);

            sql = "DELETE FROM TRANSFORMADORES";
            db.execSQL(sql);

            sql = "DELETE FROM RENGLONES";
            db.execSQL(sql);

            sql = "DELETE FROM CONTADORES";
            db.execSQL(sql);

            sql = "DELETE FROM INSTITUCION";
            db.execSQL(sql);

            sql = "DELETE FROM INSTITUCION_DETALLE";
            db.execSQL(sql);

            sql = "DELETE FROM MESES_MORA_PAGADA";
            db.execSQL(sql);

            sql = "DELETE FROM MESES_MORA_PROFORMA";
            db.execSQL(sql);

            sql = "DELETE FROM MESES_PROFORMA";
            db.execSQL(sql);

            sql = "DELETE FROM PARAMETROS";
            db.execSQL(sql);

            sql = "DELETE FROM RUTA_LECTURA";
            db.execSQL(sql);

            sql = "DELETE FROM RUTA_TECNICO";
            db.execSQL(sql);

            sql = "DELETE FROM RUTA_SINCRONIZACION";
            db.execSQL(sql);

            sql = "DELETE FROM TECNICOS";
            db.execSQL(sql);

            sql = "DELETE FROM USUARIOS_POR_RUTA";
            db.execSQL(sql);

            sql = "DELETE FROM PAGOS_DETALLE_REP";
            db.execSQL(sql);

        } catch (Exception e) {
            Log.e("Institucion", "eliminarDatosTalbas: ", e);
        }
    }
}
