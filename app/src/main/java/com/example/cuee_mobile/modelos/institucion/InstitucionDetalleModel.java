package com.example.cuee_mobile.modelos.institucion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeInstitucion_detalle;

import java.util.ArrayList;

public class InstitucionDetalleModel {

    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    public ArrayList<clsBeInstitucion_detalle> lista = new ArrayList<>();
    private final String tabla = "INSTITUCION_DETALLE";
    private final String sel =  "SELECT * FROM " + tabla;

    public InstitucionDetalleModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        clsBeInstitucion_detalle item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    lista.add(setDatos(DT));

                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("INSTITUCION_DETALLE", "buscar: ", e);
        }
    }

    public clsBeInstitucion_detalle setDatos(Cursor DT) {
        clsBeInstitucion_detalle item = null;
        try {
            item = new clsBeInstitucion_detalle();

            item.IdInstitucion = DT.getInt(0);
            item.IdPeriodoParametros = DT.getInt(1);
            item.FechaInicio = DT.getString(2);
            item.FechaFin = DT.getString(3);
            item.Preciots = DT.getDouble(4);
            item.Preciotns = DT.getDouble(5);
            item.Luz_publica = DT.getDouble(6);
            item.Cargo_fijo = DT.getDouble(7);
            item.Mora = DT.getDouble(8) / 100;
            item.Tarifa_demanda = DT.getDouble(9);
            item.Precio_kw = DT.getDouble(10);
            item.Precio_pc = DT.getDouble(11);
            item.Cargo_fijo_btdp = DT.getDouble(12);
            item.Multas_varias = DT.getDouble(13);
            item.Cobro_instalaciones = DT.getDouble(14);
            item.Cobro_reconexiones = DT.getDouble(15);
            item.Cobro_multa = DT.getDouble(16);
            item.Activo = Boolean.parseBoolean(DT.getString(17));
            item.IdUsuarioCreo = DT.getInt(18);
            item.Fecha_Creacion = DT.getString(19);
            item.Fecha_Modificacion = DT.getString(20);
            item.IdUsuarioModifico = DT.getInt(21);
            item.Cargo_fijo_btdfp = DT.getDouble(22);
            item.Tarifa_demandafp = DT.getDouble(23);
            item.Precio_kwfp = DT.getDouble(24);
            item.Precio_pcfp = DT.getDouble(25);
            item.Precio_luz_autoproductor = DT.getDouble(26);
            item.Cargo_fijo_autoproductor = DT.getDouble(27);

        } catch (Exception e) {
            Log.e("INSTITUCION_DETALLE", "setDatos: ", e);
        }
        return item;
    }

    public clsBeInstitucion_detalle getLinea(String fecha) {
        Cursor DT;
        clsBeInstitucion_detalle item = null;
        String sq = "";
        try {
            sq = "SELECT * FROM INSTITUCION_DETALLE WHERE '"+ fecha +"' BETWEEN STRFTIME('%Y-%m-%d', FechaInicio) AND STRFTIME('%Y-%m-%d', FechaFin)";
            DT = Con.OpenDT(sq);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                item = setDatos(DT);
            }
        } catch (Exception e) {
            Log.e("INSTITUCION_DETALLE", "getLinea: ", e);
        }
        return item;
    }

    public boolean guardar(clsBeInstitucion_detalle obj) {
        try {
            
            ins.init("INSTITUCION_DETALLE");

            ins.add("IdInstitucion", obj.IdInstitucion);
            ins.add("IdPeriodoParametros", obj.IdPeriodoParametros);
            ins.add("FechaInicio", obj.FechaInicio);
            ins.add("FechaFin", obj.FechaFin);
            ins.add("Preciots", obj.Preciots);
            ins.add("Preciotns", obj.Preciotns);
            ins.add("Luz_publica", obj.Luz_publica);
            ins.add("Cargo_fijo", obj.Cargo_fijo);
            ins.add("Mora", obj.Mora);
            ins.add("Tarifa_demanda", obj.Tarifa_demanda);
            ins.add("Precio_kw", obj.Precio_kw);
            ins.add("Precio_pc", obj.Precio_pc);
            ins.add("Cargo_fijo_btdp", obj.Cargo_fijo_btdp);
            ins.add("Multas_varias", obj.Multas_varias);
            ins.add("Cobro_instalaciones", obj.Cobro_instalaciones);
            ins.add("Cobro_reconexiones", obj.Cobro_reconexiones);
            ins.add("Cobro_multa", obj.Cobro_multa);
            ins.add("Activo", obj.Activo);
            ins.add("IdUsuarioCreo", obj.IdUsuarioCreo);
            ins.add("Fecha_Creacion", obj.Fecha_Creacion);
            ins.add("Fecha_Modificacion", obj.Fecha_Modificacion);
            ins.add("IdUsuarioModifico", obj.IdUsuarioModifico);
            ins.add("Cargo_fijo_btdfp", obj.Cargo_fijo_btdfp);
            ins.add("Tarifa_demandafp", obj.Tarifa_demandafp);
            ins.add("Precio_kwfp", obj.Precio_kwfp);
            ins.add("Precio_pcfp", obj.Precio_pcfp);
            ins.add("Precio_luz_autoproductor", obj.Precio_luz_autoproductor);
            ins.add("Cargo_fijo_autoproductor", obj.Cargo_fijo_autoproductor);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("INSTITUCION_DETALLE", "guardar: ", e);
            return false;
        }

        return  true;
    }
}
