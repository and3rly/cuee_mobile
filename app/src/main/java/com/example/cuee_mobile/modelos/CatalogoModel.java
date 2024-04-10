package com.example.cuee_mobile.modelos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeMeses_proforma;
import com.example.cuee_mobile.clases.clsBeProforma;
import com.example.cuee_mobile.clases.clsBeProforma_detalle;
import com.example.cuee_mobile.clases.clsBeRazon_no_lectura;
import com.example.cuee_mobile.clases.clsBeTmpProformaUs;
import com.example.cuee_mobile.clases.clsBeTransformadores;
import com.example.cuee_mobile.clases.clsBeUltimo_consumo;
import com.example.cuee_mobile.clases.clsBeUsuario_sin_lectura;

import java.util.ArrayList;

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
            Log.e("CatalogoModel", "getItinerario: ", e);
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

            sql = "DELETE FROM RAZON_NO_LECTURA";
            db.execSQL(sql);

            sql = "DELETE FROM USUARIO_SIN_LECTURA";
            db.execSQL(sql);

            sql = "DELETE FROM CORRELATIVO_PROFORMA";
            db.execSQL(sql);

        } catch (Exception e) {
            Log.e("CatalogoModel", "eliminarDatosTalbas: ", e);
        }
    }

    public ArrayList<clsBeTmpProformaUs> getMesesPendientes(int usuario) {
        ArrayList<clsBeTmpProformaUs> lista = new ArrayList<>();
        clsBeTmpProformaUs item;
        String sq = "";
        Cursor DT;

        try {
            sq = "SELECT NOMES, MES, SUM(CANTIDAD) AS MONTO, anno as AÑO, SUM(CASE WHEN idrenglon = 6 THEN CANTIDAD ELSE 0 END) AS MORA" +
                 " FROM TMP_PROFORMA_USUARIO"+
                 " WHERE IDUSUARIOSERVICIO = "+ usuario+
                 " GROUP BY ANNO, MES,NOMES";
            DT = Con.OpenDT(sq);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeTmpProformaUs();

                    item.NoMes = DT.getInt(0);
                    item.Mes = DT.getString(1);
                    item.Cantidad = DT.getDouble(2);
                    item.Anio = DT.getInt(3);
                    item.Mora = DT.getInt(4);

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("CatalogoModel", "getMesesPendientes: ", e);
        }

        return lista;
    }

    public ArrayList<clsBeTmpProformaUs> getDetalleTmpProforma(int usuario) {
        ArrayList<clsBeTmpProformaUs> tmpLista = new ArrayList<>();
        clsBeTmpProformaUs item;
        String sq = "";
        Cursor DT;

        try {
            sq = "select a.idrenglon, a.descripcion, sum(a.cantidad) as total, b.exento_iva "+
                " from tmp_proforma_usuario a"+
                " inner join renglones b on b.idrenglon = a.idrenglon"+
                " where a.idusuarioservicio ="+ usuario +
                " group by a.descripcion"+
                " order by a.idrenglon";

            DT = Con.OpenDT(sq);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                tmpLista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeTmpProformaUs();

                    item.IdRenglon = DT.getInt(0);
                    item.Descripcion = DT.getString(1);
                    item.Cantidad = DT.getDouble(2);
                    item.exento_iva = DT.getInt(3) == 1 ? true: false;

                    tmpLista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("CatalogoModel", "getDetalleTmpProforma: ", e);
        }

        return tmpLista;
    }

    public boolean guardarProformaUsuario(clsBeTmpProformaUs obj) {
        try {
            ins.init("TMP_PROFORMA_USUARIO");

            ins.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            ins.add("nomes", obj.NoMes);
            ins.add("mes", obj.Mes);
            ins.add("idrenglon", obj.IdRenglon);
            ins.add("descripcion", obj.Descripcion);
            ins.add("cantidad", obj.Cantidad);
            ins.add("anno", obj.Anio);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("CatalogoModel", "guardarProformaUsuario: ", e);
            return false;
        }
        return  true;
    }

    public boolean MoraPagada(int anio, int mes, int usuario) {
        Cursor DT;
        try {
            sql = "SELECT A.* FROM MESES_MORA_PAGADA A " +
                  " INNER JOIN MESES_PAGO B ON B.IdRecibo = A.IdRecibo " +
                  " WHERE B.IdUsuarioServicio = '"+usuario+"'" +
                  " AND A.Anno = "+anio+" AND A.NoMes = "+mes;
            DT = Con.OpenDT(sql);

            if (DT.getCount() > 0) {
                DT.moveToFirst();
                return true;
            }
            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("CatalogoModel", "MoraPagada: ", e);
        }
        return false;
    }

    public boolean guardarProforma(clsBeProforma obj) {
        Cursor DT;
        try {
            ins.init("PROFORMA");

            String sql = "SELECT MAX(idproforma) FROM PROFORMA";
            DT = Con.OpenDT(sql);
            DT.moveToFirst();
            obj.idproforma = DT.getInt(0) + 1;

            ins.add("idproforma", obj.idproforma);
            ins.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            ins.add("fecha_recibo", obj.fecha_recibo);
            ins.add("num_proforma", obj.num_proforma);
            ins.add("serie_proforma", obj.serie_proforma);
            ins.add("idusuario", obj.idusuario);
            ins.add("fecha_creacion", obj.fecha_creacion);
            ins.add("idusuario_modifica", obj.idusuario_modifica);
            ins.add("fecha_modifica", obj.fecha_modifica);
            ins.add("mes_pago", obj.mes_pago);
            ins.add("año_pago", obj.año_pago);
            ins.add("impresion", obj.Impresion);
            ins.add("proveedor", obj.proveedor);
            ins.add("observacion", obj.observacion);
            ins.add("IdPeriodoParametros", obj.IdPeriodoParametros);
            ins.add("pagol", obj.pagol);
            ins.add("fecha_ultimo_pago", obj.fecha_ultimo_pago);
            ins.add("StatCom", 0);
            ins.add("Con_hh", obj.Con_hh);
            ins.add("IdTecnico", obj.IdTecnico);
            db.execSQL(ins.sql());

            for (clsBeProforma_detalle tmp : obj.detalle) {
                ins.init("PROFORMA_DETALLE");

                ins.add("idproforma", obj.idproforma);
                ins.add("idrenglon", tmp.idrenglon);
                ins.add("descripcion", tmp.descripcion);
                ins.add("cantidad", tmp.cantidad);
                ins.add("exonera", tmp.exonera);
                ins.add("monto_impuesto", tmp.monto_impuesto);
                ins.add("exento", tmp.exento);
                ins.add("monto_gravable", tmp.monto_gravable);
                ins.add("StatCom", "N");

                db.execSQL(ins.sql());
            }

            for (clsBeMeses_proforma o: obj.MesesProforma) {
                ins.init("MESES_PROFORMA");

                ins.add("idproforma", obj.idproforma);
                ins.add("IdUsuarioServicio", o.IdUsuarioServicio);
                ins.add("NoMes", o.nomes);
                ins.add("Mes", o.mes);
                ins.add("idrenglon", o.idrenglon);
                ins.add("descripcion", o.descripcion);
                ins.add("cantidad", o.cantidad);
                ins.add("anno", o.anno);
                ins.add("StatCom", 0);

                db.execSQL(ins.sql());
            }

        } catch (Exception e) {
            Log.e("CatalogoModel", "guardarProforma: ", e);
            return false;
        }

        return true;
    }

    public clsBeProforma buscarProforma(int usuario) {
        clsBeProforma item = new clsBeProforma();
        clsBeProforma_detalle itemdet = new clsBeProforma_detalle();
        Cursor DT, DTD;
        String sq = "", sqd = "";

        try {
            sq = "SELECT * FROM PROFORMA WHERE IdUsuarioServicio = " +usuario + " AND ANULADO = 0";
            DT = Con.OpenDT(sq);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                item = new clsBeProforma();

                item.idproforma = DT.getInt(0);
                item.IdUsuarioServicio = DT.getInt(1);
                item.fecha_recibo = DT.getString(2);
                item.num_proforma = DT.getDouble(3);
                item.serie_proforma = DT.getString(4);
                item.anulado = DT.getInt(5) == 1 ? true:false;
                item.idusuario = DT.getInt(6);
                item.fecha_creacion = DT.getString(7);
                item.idusuario_modifica = DT.getInt(8);
                item.fecha_modifica = DT.getString(9);
                item.mes_pago = DT.getInt(10);
                item.año_pago = DT.getInt(11);
                item.Impresion = DT.getInt(12) == 1 ? true:false;
                item.proveedor = DT.getString(13);
                item.observacion = DT.getString(14);
                item.IdPeriodoParametros = DT.getInt(15);
                item.pagol = DT.getInt(16) == 1 ? true:false;
                item.fecha_ultimo_pago = DT.getString(17);
                item.StatCom = DT.getString(18);
                item.Con_hh = DT.getInt(19);
                item.IdTecnico = DT.getInt(20);

                sqd = "SELECT * FROM PROFORMA_DETALLE WHERE idproforma = "+ item.idproforma;
                DTD = Con.OpenDT(sqd);

                if (DTD.getCount() > 0) {
                    DTD.moveToFirst();
                    item.detalle = new ArrayList<clsBeProforma_detalle>();
                    while (!DTD.isAfterLast()) {
                        itemdet = new clsBeProforma_detalle();

                        itemdet.idproforma = DTD.getInt(0);
                        itemdet.idrenglon = DTD.getInt(1);
                        itemdet.descripcion = DTD.getString(2);
                        itemdet.cantidad = DTD.getDouble(3);
                        itemdet.exonera = DTD.getInt(4) == 1 ? true:false;
                        itemdet.monto_impuesto = DTD.getDouble(5);
                        itemdet.exento = DTD.getInt(6) == 1 ? true:false;
                        itemdet.monto_gravable = DTD.getDouble(7);
                        itemdet.StatCom = DTD.getString(8);

                        item.detalle.add(itemdet);
                        DTD.moveToNext();
                    }
                }

                if (DTD != null) DTD.close();

            }
            if (DT != null) DT.close();

        } catch (Exception e) {
            Log.e("CatalogoModel", "buscarProforma: ", e);
        }

        return item;
    }

    public ArrayList<clsBeTmpProformaUs> getTmpProformaUsuario(int usuario) {
        ArrayList<clsBeTmpProformaUs> lista = new ArrayList<>();
        clsBeTmpProformaUs item = new clsBeTmpProformaUs();
        Cursor DT;
        String sq = "";

        try {
            sq = "SELECT * FROM TMP_PROFORMA_USUARIO WHERE IdUsuarioServicio = " +usuario;
            DT = Con.OpenDT(sq);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeTmpProformaUs();
                    item.IdUsuarioServicio = DT.getInt(0);
                    item.NoMes = DT.getInt(1);
                    item.Mes = DT.getString(2);
                    item.IdRenglon = DT.getInt(3);
                    item.Descripcion = DT.getString(4);
                    item.Cantidad = DT.getDouble(5);
                    item.Anio = DT.getInt(6);

                    lista.add(item);
                    DT.moveToNext();
                }
            }
            if (DT != null) DT.close();

        } catch (Exception e) {
            Log.e("CatalogoModel", "buscarProforma: ", e);
        }

        return lista;
    }

    public int existeProforma(int usuario) {
        Cursor DT;
        String sq = "";
        int IdProforma = 0;

        try {
            sq = "SELECT idproforma FROM PROFORMA WHERE IdUsuarioServicio = " +usuario + " AND ANULADO = 0";
            DT = Con.OpenDT(sq);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                IdProforma = DT.getInt(0);
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("CatalogoModel", "existeProforma: ", e);
        }

        return IdProforma;
    }

    public ArrayList<clsBeUltimo_consumo> ultimosConsumos(int usuario) {
        Cursor DT;
        String sq = "";
        clsBeUltimo_consumo item;
        ArrayList<clsBeUltimo_consumo> ultimoConsumos = new ArrayList<>();

        try {
            sq = "SELECT consumo," +
            "CASE strftime('%m', fecha)"+
                " WHEN '01' THEN 'ENE'"+
                " WHEN '02' THEN 'FEB'"+
                " WHEN '03' THEN 'MAR'"+
                " WHEN '04' THEN 'ABR'"+
                " WHEN '05' THEN 'MAY'"+
                " WHEN '06' THEN 'JUN'"+
                " WHEN '07' THEN 'JUL'"+
                " WHEN '08' THEN 'AGTO'"+
                " WHEN '09' THEN 'SEP'"+
                " WHEN '10' THEN 'OCT'"+
                " WHEN '11' THEN 'NOV'"+
                " WHEN '12' THEN 'DIC'"+
            " END AS nombre_mes, fecha"+
            " FROM LECTURA"+
            " WHERE fecha < date('now') AND strftime('%m', fecha) <> strftime('%m', 'now') AND " +
            " IdUsuarioServicio ="+usuario +
            " GROUP BY strftime('%Y-%m', fecha)"+
            " ORDER BY fecha DESC"+
            " LIMIT 3";

            DT = Con.OpenDT(sq);

            if (DT.getCount() > 0) {

                ultimoConsumos.clear();

                DT.moveToFirst();
                while (!DT.isAfterLast()) {
                    item = new clsBeUltimo_consumo();

                    item.Consumo = DT.getInt(0);
                    item.Mes = DT.getString(1);

                    ultimoConsumos.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("CatalogoModel", "ultimoConsumos: ", e);
        }

        return ultimoConsumos;
    }

    public ArrayList<clsBeRazon_no_lectura> getRazones() {
        ArrayList<clsBeRazon_no_lectura> lista = new ArrayList<>();
        clsBeRazon_no_lectura item = null;
        String sq = "";
        Cursor DT;
        try {

            sq = "SELECT * FROM RAZON_NO_LECTURA";
            DT = Con.OpenDT(sq);

            if (DT.getCount() > 0) {

                lista.clear();

                DT.moveToFirst();
                while (!DT.isAfterLast()) {
                    item = new clsBeRazon_no_lectura();

                    item.IdRazonNoLectura = DT.getInt(0);
                    item.Nombre = DT.getString(1);
                    item.Activo = DT.getInt(2) == 1 ? true:false;
                    item.IdUsuario_crea = DT.getInt(3);
                    item.fecha_crea = DT.getString(4);
                    item.IdUsuario_modifica = DT.getInt(5);
                    item.fecha_modifica = DT.getString(6);

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("CatalogoModel", "getRazones: ", e);
        }

        return lista;
    }
}
