package com.example.cuee_mobile.modelos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeServicios_instalado;

import java.util.ArrayList;

public class ServicioInsModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "SERVICIOS_INSTALADO";
    private final String sel =  "SELECT * FROM " + tabla;
    public ArrayList<clsBeServicios_instalado> lista = new ArrayList<>();

    public ServicioInsModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
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
        clsBeServicios_instalado item;
        Cursor DT;
        try {
            DT = Con.OpenDT(sel);

            if (DT.getCount() > 0) {
                DT.moveToFirst();

                lista.clear();
                while (!DT.isAfterLast()) {
                    item = new clsBeServicios_instalado();

                    item.IdInstalacion = DT.getInt(0);
                    item.IdUsuarioServicio = DT.getInt(1);
                    item.IdContador = DT.getString(2);
                    item.IdVoltaje = DT.getInt(3);
                    item.IdPotencia = DT.getInt(4);
                    item.IdTransformador = DT.getInt(5);
                    item.IdTipoServicio = DT.getInt(6);
                    item.fecha = DT.getString(7);
                    item.hora = DT.getString(8);
                    item.IdTecnico = DT.getInt(9);
                    item.contador_anterior = DT.getString(10);
                    item.usuario_anterior = DT.getString(11);
                    item.contador_siguiente = DT.getString(12);
                    item.usuario_siguiente = DT.getString(13);
                    item.modificacion_red = DT.getInt(14) == 1 ? true : false;
                    item.subestacion = DT.getInt(15);
                    item.id_circuito = DT.getInt(16);
                    item.IdGPS = Double.valueOf(DT.getString(17));
                    item.IdFotografia = DT.getString(18);
                    item.direccion = DT.getString(19);
                    item.IdMunicipio = DT.getInt(20);
                    item.IdDepartamento = DT.getInt(21);
                    item.Zona = DT.getString(22);
                    item.Colonia = DT.getString(23);
                    item.Avenida = DT.getString(24);
                    item.Calle = DT.getString(25);
                    item.Numero = DT.getString(26);
                    item.centro = DT.getInt(27);
                    item.id_poste_inicio = DT.getString(28);
                    item.tension_nominal_acom = DT.getString(29);
                    item.fases_de_conexion = DT.getInt(30);
                    item.acometida_subt_area = DT.getInt(31);
                    item.long_cable_acom = DT.getInt(32);
                    item.tipo_de_conductor = DT.getString(33);
                    item.donacion_acom = DT.getString(34);
                    item.fecha_puesto_servicio = DT.getString(35);
                    item.fecha_retiro_acom = DT.getString(36);
                    item.num_serie_medido = DT.getString(37);
                    item.tipo_medidor = DT.getString(38);
                    item.voltaje_medidor = DT.getInt(39);
                    item.voltaje_suministro = DT.getInt(40);
                    item.corriente_nominal = DT.getInt(41);
                    item.corriente_maxima = DT.getInt(42);
                    item.kh = DT.getDouble(43);
                    item.Rr = DT.getString(44);
                    item.fecha_puesto_servicio_m = DT.getString(45);
                    item.fecha_retiro_medidor = DT.getString(46);
                    item.coor_x = DT.getDouble(47);
                    item.coor_y = DT.getDouble(48);
                    item.zona_utm_medidor = DT.getString(49);
                    item.fecha_ultimo_pago = DT.getString(50);
                    item.numero_contrato = DT.getDouble(51);
                    item.fecha_contrato = DT.getString(52);
                    item.hora_contrato = DT.getString(53);
                    item.servicio_bajo_demanda = DT.getInt(54) == 1 ? true:false;
                    item.kw_contratada = DT.getDouble(55);
                    item.potencia_contratada = DT.getDouble(56);
                    item.ruta = DT.getString(57);
                    item.itinerario = DT.getInt(58);
                    item.IdUsuario = DT.getInt(59);
                    item.fecha_creacion = DT.getString(60);
                    item.idusuario_modifica = DT.getInt(61);
                    item.fecha_modificacion = DT.getString(62);
                    item.Activo = Boolean.parseBoolean(String.valueOf(DT.getInt(63)));
                    item.estado_servicio = DT.getInt(64);
                    item.IdTipoUsuario = DT.getInt(65);
                    item.num_tarjeta = DT.getString(66);
                    item.tipo_registro = DT.getString(67);
                    item.servicio_bajo_demandafp = DT.getInt(68) == 1 ? true:false;
                    item.es_autoproductor = DT.getInt(69) == 1 ? true:false;

                    lista.add(item);
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();
        } catch (Exception e) {
            Log.e("SERVICIOS_INSTALADO", "buscar: ", e );
        }
    }

    public boolean guardar(clsBeServicios_instalado obj) {
        try {
            ins.init(tabla);

            ins.add("IdInstalacion", obj.IdInstalacion);
            ins.add("IdUsuarioServicio", obj.IdUsuarioServicio);
            ins.add("IdContador", obj.IdContador);
            ins.add("IdVoltaje", obj.IdVoltaje);
            ins.add("IdPotencia", obj.IdPotencia);
            ins.add("IdTransformador", obj.IdTransformador);
            ins.add("IdTipoServicio", obj.IdTipoServicio);
            ins.add("Fecha", obj.fecha);
            ins.add("Hora", obj.hora);
            ins.add("IdTecnico", obj.IdTecnico);
            ins.add("Contador_anterior", obj.contador_anterior);
            ins.add("Usuario_anterior", obj.usuario_anterior);
            ins.add("Contador_siguiente", obj.contador_siguiente);
            ins.add("Usuario_siguiente", obj.usuario_siguiente);
            ins.add("Modificacion_red", obj.modificacion_red);
            ins.add("Subestacion", obj.subestacion);
            ins.add("Id_circuito", obj.id_circuito);
            ins.add("IdGPS", obj.IdGPS);
            ins.add("IdFotografia", obj.IdFotografia);
            ins.add("Direccion", obj.direccion);
            ins.add("IdMunicipio", obj.IdMunicipio);
            ins.add("IdDepartamento", obj.IdDepartamento);
            ins.add("Zona", obj.Zona);
            ins.add("Colonia", obj.Colonia);
            ins.add("Avenida", obj.Avenida);
            ins.add("Calle", obj.Calle);
            ins.add("Numero", obj.Numero);
            ins.add("Centro", obj.centro);
            ins.add("Id_poste_inicio", obj.id_poste_inicio);
            ins.add("Tension_nominal_acom", obj.tension_nominal_acom);
            ins.add("Fases_de_conexion", obj.fases_de_conexion);
            ins.add("Acometida_subt_area", obj.acometida_subt_area);
            ins.add("Long_cable_acom", obj.long_cable_acom);
            ins.add("Tipo_de_conductor", obj.tipo_de_conductor);
            ins.add("Donacion_acom", obj.donacion_acom);
            ins.add("Fecha_puesto_servicio", obj.fecha_puesto_servicio);
            ins.add("Fecha_retiro_acom", obj.fecha_retiro_acom);
            ins.add("Num_serie_medido", obj.num_serie_medido);
            ins.add("Tipo_medidor", obj.tipo_medidor);
            ins.add("Voltaje_medidor", obj.voltaje_medidor);
            ins.add("Voltaje_suministro", obj.voltaje_suministro);
            ins.add("Corriente_nominal", obj.corriente_nominal);
            ins.add("Corriente_maxima", obj.corriente_maxima);
            ins.add("Kh", obj.kh);
            ins.add("Rr", obj.Rr);
            ins.add("Fecha_puesto_servicio_m", obj.fecha_puesto_servicio_m);
            ins.add("Fecha_retiro_medidor", obj.fecha_retiro_medidor);
            ins.add("Coor_x", obj.coor_x);
            ins.add("Coor_y", obj.coor_y);
            ins.add("Zona_utm_medidor", obj.zona_utm_medidor);
            ins.add("Fecha_ultimo_pago", obj.fecha_ultimo_pago);
            ins.add("Numero_contrato", obj.numero_contrato);
            ins.add("Fecha_contrato", obj.fecha_contrato);
            ins.add("Hora_contrato", obj.hora_contrato);
            ins.add("Servicio_bajo_demanda", obj.servicio_bajo_demanda);
            ins.add("Kw_contratada", obj.kw_contratada);
            ins.add("Potencia_contratada", obj.potencia_contratada);
            ins.add("Ruta", obj.ruta);
            ins.add("Itinerario", obj.itinerario);
            ins.add("IdUsuario", obj.IdUsuario);
            ins.add("Fecha_creacion", obj.fecha_creacion);
            ins.add("Idusuario_modifica", obj.idusuario_modifica);
            ins.add("Fecha_modificacion", obj.fecha_modificacion);
            ins.add("Activo", obj.Activo);
            ins.add("Estado_servicio", obj.estado_servicio);
            ins.add("IdTipoUsuario", obj.IdTipoUsuario);
            ins.add("Num_tarjeta", obj.num_tarjeta);
            ins.add("Tipo_registro", obj.tipo_registro);
            ins.add("Servicio_bajo_demandafp", obj.servicio_bajo_demandafp);
            ins.add("Es_autoproductor", obj.es_autoproductor);

            db.execSQL(ins.sql());

        } catch (Exception e) {
            Log.e("SERVICIOS_INSTALADO", "guardar: ", e);
            return false;
        }
        return  true;
    }

    public void actualizarServicio(clsBeServicios_instalado obj) {
        try {
            upd.init(tabla);

            upd.add("Lectura_realizada", obj.Lectura_realizada);
            upd.add("Lectura_correcta", obj.Lectura_correcta);
            upd.Where("IdInstalacion = " + obj.IdInstalacion);

            db.execSQL(upd.sql());

        } catch (Exception e) {
            Log.e("SERVICIOS_INSTALADO", "actualizar: ", e);
        }
    }
}
