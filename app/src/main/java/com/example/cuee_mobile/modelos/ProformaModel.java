package com.example.cuee_mobile.modelos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeMeses_proforma;
import com.example.cuee_mobile.clases.clsBeProforma;
import com.example.cuee_mobile.clases.clsBeProforma_detalle;

import java.util.ArrayList;

public class ProformaModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "PROFORMA";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeProforma> lista = new ArrayList<>();

    public ProformaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;
    }

    public void getLista(String sq) {
        buscar(sel +" "+ sq);
    }

    public void buscar(String sel) {
        clsBeProforma item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
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

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("ProformaModel", "buscar: ", e );
        }
    }

    public ArrayList<clsBeProforma_detalle> getDetalle(int proforma) {
        ArrayList<clsBeProforma_detalle> listaDet = new ArrayList<>();
        clsBeProforma_detalle itemdet;
        String sq = "";
        Cursor DT;
        try {
            sq = "SELECT * FROM PROFORMA_DETALLE WHERE idproforma =" + proforma;
            DT = Con.OpenDT(sq);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                listaDet.clear();
                while (!DT.isAfterLast()) {
                    itemdet = new clsBeProforma_detalle();

                    itemdet.idproforma = DT.getInt(0);
                    itemdet.idrenglon = DT.getInt(1);
                    itemdet.descripcion = DT.getString(2);
                    itemdet.cantidad = DT.getDouble(3);
                    itemdet.exonera = DT.getInt(4) == 1 ? true:false;
                    itemdet.monto_impuesto = DT.getDouble(5);
                    itemdet.exento = DT.getInt(6) == 1 ? true:false;
                    itemdet.monto_gravable = DT.getDouble(7);
                    itemdet.StatCom = DT.getString(8);

                    listaDet.add(itemdet);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("ProformaModel", "buscar: ", e );
        }

        return listaDet;
    }

    public ArrayList<clsBeMeses_proforma> getMesesProforma(int proforma) {
        ArrayList<clsBeMeses_proforma> listaDet = new ArrayList<>();
        clsBeMeses_proforma itemdet;
        String sq = "";
        Cursor DT;
        try {
            sq = "SELECT * FROM MESES_PROFORMA WHERE idproforma =" + proforma;
            DT = Con.OpenDT(sq);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                listaDet.clear();
                while (!DT.isAfterLast()) {
                    itemdet = new clsBeMeses_proforma();

                    itemdet.idproforma = DT.getInt(1);
                    itemdet.IdUsuarioServicio = DT.getInt(2);
                    itemdet.nomes = DT.getInt(3);
                    itemdet.mes = DT.getString(4);
                    itemdet.idrenglon = DT.getInt(5);
                    itemdet.descripcion = DT.getString(6);
                    itemdet.cantidad = DT.getDouble(7);
                    itemdet.anno = DT.getInt(8);

                    listaDet.add(itemdet);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("ProformaModel", "buscar: ", e );
        }

        return listaDet;
    }

    public void actualizaEstado(int poroforma) {
        String sq = "";
        try {
            sq = "UPDATE PROFORMA SET StatCom = 1 WHERE idproforma = " + poroforma;
            db.execSQL(sq);

            sq = "UPDATE MESES_PROFORMA SET StatCom = 1 WHERE idproforma = " + poroforma;
            db.execSQL(sq);

        } catch (Exception e) {
            Log.e("LECTURA", "getLecturaAnterior: ", e);
        }
    }
}
