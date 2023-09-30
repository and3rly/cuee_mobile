package com.example.cuee_mobile.modelos.institucion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeInstitucion_detalle;

public class InstitucionDetalleModel {

    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;

    public InstitucionDetalleModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;

        ins = Con.Ins; upd = Con.Upd;
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
            Log.e("Institucion Detalle", "guardar: ", e);
            return false;
        }

        return  true;
    }
}
