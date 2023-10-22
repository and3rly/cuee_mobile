package com.example.cuee_mobile.modelos.institucion;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeInstitucion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InstitucionModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private String sql;
    private final String tabla = "INSTITUCION";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeInstitucion> lista = new ArrayList<>();
    public  clsBeInstitucion objInstitucion = null;

    public InstitucionModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;

        ins = Con.Ins; upd = Con.Upd;
    }

    public void getLista(String sq) {
        buscar(sel +" "+ sq, false);
    }

    public void getLista() {
        buscar(sel, false);
    }

    public void getLinea() {
        buscar(sel , true);
    }

    public void buscar(String sel, boolean uno) {
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                if (!uno) {
                    lista.clear();
                    while (!DT.isAfterLast()) {
                        lista.add(setLinea(DT));
                        DT.moveToNext();
                    }
                } else {
                    objInstitucion = setLinea(DT);
                }
            }

            if (DT != null) DT.close();

        } catch (Exception e) {
            Log.e("Institucion", "buscar: ", e );
        }
    }

    private clsBeInstitucion setLinea(Cursor DT) {
        clsBeInstitucion item = null;
        try {
            item = new clsBeInstitucion();

            item.IdInstitucion = DT.getInt(0);
            item.Nombre = DT.getString(1);
            item.Logo = DT.getString(2);
            item.Preciots = DT.getDouble(3);
            item.Preciotns = DT.getDouble(4);
            item.Luz_publica = DT.getDouble(5);
            item.Cargo_fijo = DT.getDouble(6);
            item.Mora = DT.getDouble(7);
            item.Tarifa_demanda = DT.getDouble(8);
            item.Precio_kw = DT.getDouble(9);
            item.Precio_pc = DT.getDouble(10);
            item.Cargo_fijo_btdp = DT.getDouble(11);
            item.Multas_varias = DT.getDouble(12);
            item.Cobro_instalaciones = DT.getDouble(13);
            item.Cobro_reconexiones = DT.getDouble(14);
            item.Cobro_multa = DT.getDouble(15);
            item.Ultima_solicitud = DT.getDouble(16);
            item.Ultimo_usuario = DT.getDouble(17);
            item.Ultimo_contrato = DT.getDouble(18);
            item.Porcentaje_lectura = DT.getInt(19);
            item.Cargo_fijo_btdfp = DT.getDouble(20);
            item.Tarifa_demandafp = DT.getDouble(21);
            item.Precio_kwfp = DT.getDouble(22);
            item.Precio_pcfp = DT.getDouble(23);
            item.Serie_pf = DT.getString(24);
            item.Correl_pf = DT.getInt(25);
            item.Correl_pf_actual = DT.getInt(26);
            item.Fel_codigo_acceso = DT.getString(27);
            item.Fel_usuario = DT.getString(28);
            item.Fel_clave = DT.getString(29);
            item.NIT_Emisor = DT.getString(30);
            item.Direccion_emisor = DT.getString(31);
            item.Correo_emisor = DT.getString(32);
            item.Nombre_Comercial = DT.getString(33);
            item.Nombre_Emisor = DT.getString(34);
            item.Codigo_postal = DT.getString(35);
            item.Municipio = DT.getString(36);
            item.Departamento = DT.getString(37);
            item.Pais = DT.getString(38);
            item.Afiliacion_IVA = DT.getString(39);
            item.Fel_token = DT.getString(40);
            item.Fel_requestor = DT.getString(41);
            item.Porcentaje_iva = DT.getDouble(42);
            item.Fel_codigo_escenario_isr = DT.getInt(43);
            item.Fel_tipo_frase_isr = DT.getInt(44);
            item.Fel_texto_isr = DT.getString(45);
            item.Fel_codigo_escenario_iva = DT.getInt(46);
            item.Fel_tipo_frase_iva = DT.getInt(47);
            item.Fel_texto_iva = DT.getString(48);
            item.Numero_resolucion = DT.getString(49);
            item.Fecha_resolucion = DT.getString(50);
            item.Frase_exento = DT.getString(51);
            item.Monto_permitido_cf = DT.getDouble(52);

        } catch (Exception e) {
            Log.e("Institucion", "buscar: ", e );
        }

        return item;
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
