package com.example.cuee_mobile.modelos.institucion;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeInstitucion;

public class InstitucionModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;

    public InstitucionModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;

        ins = Con.Ins; upd = Con.Upd;
    }

    public boolean guardar(clsBeInstitucion obj) {

        try {
            ins.init("INSTITUCION");

            ins.add("IdInstitucion", obj.IdInstitucion);
            ins.add("Nombre", obj.Nombre);
            ins.add("Logo", obj.Logo);
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
            ins.add("Ultima_solicitud", obj.Ultima_solicitud);
            ins.add("Ultimo_usuario", obj.Ultimo_usuario);
            ins.add("Ultimo_contrato", obj.Ultimo_contrato);
            ins.add("Porcentaje_lectura", obj.Porcentaje_lectura);
            ins.add("Cargo_fijo_btdfp", obj.Cargo_fijo_btdfp);
            ins.add("Tarifa_demandafp", obj.Tarifa_demandafp);
            ins.add("Precio_kwfp", obj.Precio_kwfp);
            ins.add("Precio_pcfp", obj.Precio_pcfp);
            ins.add("Serie_pf", obj.Serie_pf);
            ins.add("Correl_pf", obj.Correl_pf);
            ins.add("Correl_pf_actual", obj.Correl_pf_actual);
            ins.add("Fel_codigo_acceso", obj.Fel_codigo_acceso);
            ins.add("Fel_usuario", obj.Fel_usuario);
            ins.add("Fel_clave", obj.Fel_clave);
            ins.add("NIT_Emisor", obj.NIT_Emisor);
            ins.add("Direccion_emisor", obj.Direccion_emisor);
            ins.add("Correo_emisor", obj.Correo_emisor);
            ins.add("Nombre_Comercial", obj.Nombre_Comercial);
            ins.add("Nombre_Emisor", obj.Nombre_Emisor);
            ins.add("Codigo_postal", obj.Codigo_postal);
            ins.add("Municipio", obj.Municipio);
            ins.add("Departamento", obj.Departamento);
            ins.add("Pais", obj.Pais);
            ins.add("Afiliacion_IVA", obj.Afiliacion_IVA);
            ins.add("Fel_token", obj.Fel_token);
            ins.add("Fel_requestor", obj.Fel_requestor);
            ins.add("Porcentaje_iva", obj.Porcentaje_iva);
            ins.add("Fel_codigo_escenario_isr", obj.Fel_codigo_escenario_isr);
            ins.add("Fel_tipo_frase_isr", obj.Fel_tipo_frase_isr);
            ins.add("Fel_texto_isr", obj.Fel_texto_isr);
            ins.add("Fel_codigo_escenario_iva", obj.Fel_codigo_escenario_iva);
            ins.add("Fel_tipo_frase_iva", obj.Fel_tipo_frase_iva);
            ins.add("Fel_texto_iva", obj.Fel_texto_iva);
            ins.add("Numero_resolucion", obj.Numero_resolucion);
            ins.add("Fecha_resolucion", obj.Fecha_resolucion);
            ins.add("Frase_exento", obj.Frase_exento);
            ins.add("Monto_permitido_cf", obj.Monto_permitido_cf);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("Institucion", "guardar: ", e );
            return  false;
        }

        return true;
    }
}
