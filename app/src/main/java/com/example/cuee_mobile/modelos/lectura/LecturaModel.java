package com.example.cuee_mobile.modelos.lectura;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.auxLecturaServicio;
import com.example.cuee_mobile.clases.clsBeLectura;

import java.util.ArrayList;

public class LecturaModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "LECTURA";
    private String sql;
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeLectura> lista = new ArrayList<>();
    public ArrayList<auxLecturaServicio> serLectura = new ArrayList<>();
    public clsBeLectura objLectura = null;
    public int IdActualLectura;
    public int filas = 0;

    public LecturaModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        buscar(sel, true);
    }

    public void getLinea(String sq) {
        buscar(sel +" "+ sq, true);
    }

    private void buscar(String sel, boolean uno) {
        clsBeLectura item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            filas = DT.getCount();
            if (DT.getCount() > 0) {
                DT.moveToFirst();

                if (!uno) {
                    lista.clear();
                    while (!DT.isAfterLast()) {
                        lista.add(setLinea(DT));
                        DT.moveToNext();
                    }
                } else {
                    objLectura = setLinea(DT);
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("LECTURA", "buscar: ", e );
        }
    }

    public void getConsultaLectura(String termino, String fechaInicial, String fechaFinal) {
        clsBeLectura item;
        Cursor DT;
        try {
            sql = "SELECT A.*, STRFTIME('%d/%m/%Y', A.Fecha) AS Fecha, STRFTIME('%d/%m/%Y', A.Fecha_creacion) AS Fecha_creacion " +
                    "FROM LECTURA A WHERE 1=1 ";
                    //"INNER JOIN USUARIOS_SERVICIO B ON B.IdUsuarioServicio = A.IdUsuarioServicio";

            if (!termino.isEmpty()) {
                sql += " AND A.IdLectura LIKE '%"+termino+"%' " +
                        "OR A.IdContador LIKE '%"+termino+"%' " +
                        "OR A.IdUsuarioServicio LIKE '%"+termino+"%' ";
                        //"OR B.Nombres LIKE '%"+termino+"%' ";
            }

            if (!fechaInicial.isEmpty() && !fechaFinal.isEmpty()) {
                sql += " AND A.Fecha BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"'";
            }

            DT = Con.OpenDT(sql);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {

                    item = new clsBeLectura();

                    item.IdLectura = DT.getInt(0);
                    item.IdUsuarioServicio = DT.getInt(1);
                    item.IdContador = DT.getString(2);
                    item.Fecha = DT.getString(12);
                    item.Lectura = DT.getDouble(4);
                    item.Consumo = DT.getDouble(5);
                    item.IdUsuario = DT.getInt(6);
                    item.Fecha_creacion = DT.getString(13);
                    item.Lectura_kw = DT.getDouble(8);
                    item.IdTecnico = DT.getInt(9);
                    item.Con_hh = DT.getInt(10);
                    item.StatCom = DT.getInt(11);

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("LECTURA", "getConsultaLectura: ", e );
        }
    }

    public ArrayList<auxLecturaServicio> getServiciosLectura(boolean pendientes) {
        Cursor DT;
        auxLecturaServicio item;
        try {
            serLectura.clear();
            sql = "SELECT A.IdInstalacion, " +
                    "A.IdUsuarioServicio, " +
                    "A.IdContador, " +
                    "A.Lectura_realizada, " +
                    "A.Lectura_correcta, " +
                    "A.Servicio_bajo_demandafp, " +
                    "A.Servicio_bajo_demanda, " +
                    "B.Nombres, " +
                    "C.IdItinerario, " +
                    "C.Orden" +
                    " FROM SERVICIOS_INSTALADO A" +
                    " INNER JOIN USUARIOS_SERVICIO B ON A.IdUsuarioServicio = B.IdUsuarioServicio" +
                    " INNER JOIN USUARIOS_POR_RUTA C ON A.IdUsuarioServicio = C.IdUsuarioServicio " +
                    " WHERE A.Estado_servicio NOT IN (1,3) ";

            if (pendientes) {
                sql += " AND A.Lectura_realizada = 0 AND A.Lectura_correcta = 0";
            }

            sql += " Order By C.Orden ";

            DT = Con.OpenDT(sql);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                while (!DT.isAfterLast()) {
                    item = new auxLecturaServicio();

                    item.IdInstalacion = DT.getInt(0);
                    item.IdUsuarioServicio = DT.getInt(1);
                    item.IdContador = DT.getString(2);
                    item.Lectura_realizada =  DT.getInt(3);
                    item.Lectura_correcta = DT.getInt(4) == 1 ? true:false;
                    item.Servicio_bajo_demandafp = DT.getInt(5) == 1 ? true:false;
                    item.Servicio_bajo_demanda = DT.getInt(6) == 1 ? true:false;
                    item.Usuario = DT.getString(7);
                    item.IdItinerario = DT.getInt(8);

                    serLectura.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("LECTURA", "buscar: ", e );
        }

        return serLectura;
    }

    private clsBeLectura setLinea(Cursor DT) {
        clsBeLectura item = null;
        try {
            item = new clsBeLectura();

            item.IdLectura = DT.getInt(0);
            item.IdUsuarioServicio = DT.getInt(1);
            item.IdContador = DT.getString(2);
            item.Fecha = DT.getString(3);
            item.Lectura = DT.getDouble(4);
            item.Consumo = DT.getDouble(5);
            item.IdUsuario = DT.getInt(6);
            item.Fecha_creacion = DT.getString(7);
            item.Lectura_kw = DT.getDouble(8);
            item.IdTecnico = DT.getInt(9);
            item.Con_hh = DT.getInt(10);
            item.StatCom = DT.getInt(11);

        } catch (Exception e) {
            Log.e("LECTURA", "setLinea: ", e );
        }

        return item;
    }

    public boolean guardar(clsBeLectura obj, boolean rec) {
        Cursor DT;
        try {
            ins.init(tabla);

            if (!rec) {
                String sql = "SELECT MAX(IdLectura) FROM " + tabla;
                DT = Con.OpenDT(sql);
                DT.moveToFirst();

                obj.IdLectura = DT.getInt(0) + 1;
                IdActualLectura = obj.IdLectura;
            }

            ins.add("IdLectura", obj.IdLectura);
            ins.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            ins.add("IdContador", obj.IdContador);
            ins.add("Fecha", obj.Fecha);
            ins.add("Lectura", obj.Lectura);
            ins.add("Consumo", obj.Consumo);
            ins.add("IdUsuario", obj.IdUsuario);
            ins.add("Fecha_creacion", obj.Fecha_creacion);
            ins.add("Lectura_kw", obj.Lectura_kw);
            ins.add("IdTecnico", obj.IdTecnico);
            ins.add("con_hh", obj.Con_hh);
            ins.add("StatCom", obj.StatCom);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("LECTURA", "guardar: ", e);
            return false;
        }
        return  true;
    }

    public boolean actualizar(clsBeLectura obj) {
        try {
            upd.init(tabla);
            upd.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            upd.add("IdContador", obj.IdContador);
            upd.add("Fecha", obj.Fecha);
            upd.add("Lectura", obj.Lectura);
            upd.add("Consumo", obj.Consumo);
            upd.add("IdUsuario", obj.IdUsuario);
            upd.add("Fecha_creacion", obj.Fecha_creacion);
            upd.add("Lectura_kw", obj.Lectura_kw);
            upd.add("IdTecnico", obj.IdTecnico);
            upd.add("con_hh", obj.Con_hh);
            upd.Where("IdLectura = "+obj.IdLectura);
            db.execSQL(upd.sql());
        } catch (Exception e) {
            Log.e("LECTURA", "actualizar: ", e);
            return false;
        }
        return  true;
    }

    public clsBeLectura getLecturaAnterior(int usuario) {
        clsBeLectura lectura = null;
        Cursor DT;
        try {
            sql = "SELECT *,'' FROM LECTURA WHERE IdUsuarioServicio = "+usuario+" AND StatCom = 1 ORDER BY DATE(Fecha) DESC LIMIT 1";
            DT = Con.OpenDT(sql);

            if (DT.getCount() > 0) {
                DT.moveToFirst();
                lectura = setLinea(DT);
            }
        } catch (Exception e) {
            Log.e("LECTURA", "getLecturaAnterior: ", e);
        }
        return lectura;
    }

    public double getPromedio(int usuario) {
        double promedio = 0;
        Cursor DT;
        try {
            sql = "SELECT IFNULL(avg(consumo),0) as promedio FROM LECTURA WHERE idUsuarioServicio =" +usuario;
            DT = Con.OpenDT(sql);

            if (DT.getCount() > 0) {
                DT.moveToFirst();
                promedio = DT.getDouble(0);
            }
        } catch (Exception e) {
            Log.e("LECTURA", "getLecturaAnterior: ", e);
        }
        return promedio;
    }

    public void actualizaEstado(int lectura) {
        try {
            sql = "UPDATE LECTURA SET StatCom = 1 WHERE IdLectura = " + lectura;
            db.execSQL(sql);

        } catch (Exception e) {
            Log.e("LECTURA", "getLecturaAnterior: ", e);
        }
    }

    public boolean getLecturaById(int Id) {
        Cursor DT;
        try {
            sql = "SELECT A.*, B.Nombres as NUsuario FROM LECTURA A " +
                  "INNER JOIN USUARIOS_SERVICIO B ON B.IdUsuarioServicio = A.IdUsuarioServicio " +
                  "WHERE A.IdLectura = "+Id;
            DT = Con.OpenDT(sql);

            if (DT.getCount() > 0) {
                DT.moveToFirst();
                objLectura = setLinea(DT);
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("LECTURA", "getLecturaById: ", e);
        }

        return true;
    }
}

