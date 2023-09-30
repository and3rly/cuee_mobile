package com.example.cuee_mobile.modelos.reporte;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBePagos_detalle_rep;

public class PagosDetModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla;

    public PagosDetModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;

        tabla = "PAGOS_DETALLE_REP";
    }

    public boolean guardar(clsBePagos_detalle_rep obj) {
        try {
            ins.init(tabla);

            ins.add("id", obj.id);
            ins.add("idusuarioservicio", obj.idusuarioservicio);
            ins.add("nombreusuarioservicio", obj.nombreusuarioservicio);
            ins.add("nocontador", obj.nocontador);
            ins.add("nomarchamo", obj.nomarchamo);
            ins.add("tiposervicio", obj.tiposervicio);
            ins.add("tipotarifa", obj.tipotarifa);
            ins.add("nomes", obj.nomes);
            ins.add("año", obj.año);
            ins.add("voltios", obj.voltios);
            ins.add("lectura_anterior", obj.lectura_anterior);
            ins.add("lectura_actual", obj.lectura_actual);
            ins.add("consumo", obj.consumo);
            ins.add("fecha_inicio", obj.fecha_inicio);
            ins.add("fecha_fin", obj.fecha_fin);
            ins.add("dias_facturados", obj.dias_facturados);
            ins.add("mes_operacion", obj.mes_operacion);
            ins.add("fecha_emision", obj.fecha_emision);
            ins.add("cargo_fijo", obj.cargo_fijo);
            ins.add("consumo_energia_ts", obj.consumo_energia_ts);
            ins.add("importe_ts", obj.importe_ts);
            ins.add("consumo_energia_tns", obj.consumo_energia_tns);
            ins.add("importe_tns", obj.importe_tns);
            ins.add("consumo_energia_total", obj.consumo_energia_total);
            ins.add("importe_consumo_total", obj.importe_consumo_total);
            ins.add("alumbrado_publico", obj.alumbrado_publico);
            ins.add("fecha_sistema", obj.fecha_sistema);
            ins.add("potencia_contratada", obj.potencia_contratada);
            ins.add("potencia_maxima", obj.potencia_maxima);
            ins.add("importe_potencia_maxima", obj.importe_potencia_maxima);
            ins.add("servicio_bajo_demanda", obj.servicio_bajo_demanda);
            ins.add("IdPago", obj.IdPago);
            ins.add("servicio_bajo_demandafp", obj.servicio_bajo_demandafp);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("PAGOS_DETALLE_REP", "guardar: ", e);
            return false;
        }
        return  true;
    }
}
