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
                    item.Fecha = DT.getString(7);
                    item.Hora = DT.getString(8);
                    item.IdTecnico = DT.getInt(9);
                    item.Contador_anterior = DT.getString(10);
                    item.Usuario_anterior = DT.getString(11);
                    item.Contador_siguiente = DT.getString(12);
                    item.Usuario_siguiente = DT.getString(13);
                    item.Modificacion_red = Boolean.parseBoolean(String.valueOf(DT.getInt(14)));
                    item.Subestacion = DT.getInt(15);
                    item.Id_circuito = DT.getInt(16);
                    item.IdGPS = DT.getString(17);
                    item.IdFotografia = DT.getString(18);
                    item.Direccion = DT.getString(19);
                    item.IdMunicipio = DT.getInt(20);
                    item.IdDepartamento = DT.getInt(21);
                    item.Zona = DT.getString(22);
                    item.Colonia = DT.getString(23);
                    item.Avenida = DT.getString(24);
                    item.Calle = DT.getString(25);
                    item.Numero = DT.getString(26);
                    item.Centro = DT.getString(27);
                    item.Id_poste_inicio = DT.getString(28);
                    item.Tension_nominal_acom = DT.getString(29);
                    item.Fases_de_conexion = DT.getString(30);
                    item.Acometida_subt_area = DT.getString(31);
                    item.Long_cable_acom = DT.getInt(32);
                    item.Tipo_de_conductor = DT.getString(33);
                    item.Donacion_acom = DT.getString(34);
                    item.Fecha_puesto_servicio = DT.getString(35);
                    item.Fecha_retiro_acom = DT.getString(36);
                    item.Num_serie_medido = DT.getString(37);
                    item.Tipo_medidor = DT.getString(38);
                    item.Voltaje_medidor = DT.getString(39);
                    item.Voltaje_suministro = DT.getInt(40);
                    item.Corriente_nominal = DT.getString(41);
                    item.Corriente_maxima = DT.getString(42);
                    item.Kh = DT.getString(43);
                    item.Rr = DT.getString(44);
                    item.Fecha_puesto_servicio_m = DT.getString(45);
                    item.Fecha_retiro_medidor = DT.getString(46);
                    item.Coor_x = DT.getDouble(47);
                    item.Coor_y = DT.getDouble(48);
                    item.Zona_utm_medidor = DT.getString(49);
                    item.Fecha_ultimo_pago = DT.getString(50);
                    item.Numero_contrato = DT.getDouble(51);
                    item.Fecha_contrato = DT.getString(52);
                    item.Hora_contrato = DT.getString(53);
                    item.Servicio_bajo_demanda = Boolean.parseBoolean(String.valueOf(DT.getInt(54)));
                    item.Kw_contratada = DT.getDouble(55);
                    item.Potencia_contratada = DT.getDouble(56);
                    item.Ruta = DT.getString(57);
                    item.Itinerario = DT.getInt(58);
                    item.IdUsuario = DT.getInt(59);
                    item.Fecha_creacion = DT.getString(60);
                    item.Idusuario_modifica = DT.getInt(61);
                    item.Fecha_modificacion = DT.getString(62);
                    item.Activo = Boolean.parseBoolean(String.valueOf(DT.getInt(63)));
                    item.Estado_servicio = DT.getInt(64);
                    item.IdTipoUsuario = DT.getInt(65);
                    item.Num_tarjeta = DT.getString(66);
                    item.Tipo_registro = DT.getString(67);
                    item.Servicio_bajo_demandafp = Boolean.parseBoolean(String.valueOf(DT.getInt(68)));
                    item.Es_autoproductor = Boolean.parseBoolean(String.valueOf(DT.getInt(69)));

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
            ins.add("Fecha", obj.Fecha);
            ins.add("Hora", obj.Hora);
            ins.add("IdTecnico", obj.IdTecnico);
            ins.add("Contador_anterior", obj.Contador_anterior);
            ins.add("Usuario_anterior", obj.Usuario_anterior);
            ins.add("Contador_siguiente", obj.Contador_siguiente);
            ins.add("Usuario_siguiente", obj.Usuario_siguiente);
            ins.add("Modificacion_red", obj.Modificacion_red);
            ins.add("Subestacion", obj.Subestacion);
            ins.add("Id_circuito", obj.Id_circuito);
            ins.add("IdGPS", obj.IdGPS);
            ins.add("IdFotografia", obj.IdFotografia);
            ins.add("Direccion", obj.Direccion);
            ins.add("IdMunicipio", obj.IdMunicipio);
            ins.add("IdDepartamento", obj.IdDepartamento);
            ins.add("Zona", obj.Zona);
            ins.add("Colonia", obj.Colonia);
            ins.add("Avenida", obj.Avenida);
            ins.add("Calle", obj.Calle);
            ins.add("Numero", obj.Numero);
            ins.add("Centro", obj.Centro);
            ins.add("Id_poste_inicio", obj.Id_poste_inicio);
            ins.add("Tension_nominal_acom", obj.Tension_nominal_acom);
            ins.add("Fases_de_conexion", obj.Fases_de_conexion);
            ins.add("Acometida_subt_area", obj.Acometida_subt_area);
            ins.add("Long_cable_acom", obj.Long_cable_acom);
            ins.add("Tipo_de_conductor", obj.Tipo_de_conductor);
            ins.add("Donacion_acom", obj.Donacion_acom);
            ins.add("Fecha_puesto_servicio", obj.Fecha_puesto_servicio);
            ins.add("Fecha_retiro_acom", obj.Fecha_retiro_acom);
            ins.add("Num_serie_medido", obj.Num_serie_medido);
            ins.add("Tipo_medidor", obj.Tipo_medidor);
            ins.add("Voltaje_medidor", obj.Voltaje_medidor);
            ins.add("Voltaje_suministro", obj.Voltaje_suministro);
            ins.add("Corriente_nominal", obj.Corriente_nominal);
            ins.add("Corriente_maxima", obj.Corriente_maxima);
            ins.add("Kh", obj.Kh);
            ins.add("Rr", obj.Rr);
            ins.add("Fecha_puesto_servicio_m", obj.Fecha_puesto_servicio_m);
            ins.add("Fecha_retiro_medidor", obj.Fecha_retiro_medidor);
            ins.add("Coor_x", obj.Coor_x);
            ins.add("Coor_y", obj.Coor_y);
            ins.add("Zona_utm_medidor", obj.Zona_utm_medidor);
            ins.add("Fecha_ultimo_pago", obj.Fecha_ultimo_pago);
            ins.add("Numero_contrato", obj.Numero_contrato);
            ins.add("Fecha_contrato", obj.Fecha_contrato);
            ins.add("Hora_contrato", obj.Hora_contrato);
            ins.add("Servicio_bajo_demanda", obj.Servicio_bajo_demanda);
            ins.add("Kw_contratada", obj.Kw_contratada);
            ins.add("Potencia_contratada", obj.Potencia_contratada);
            ins.add("Ruta", obj.Ruta);
            ins.add("Itinerario", obj.Itinerario);
            ins.add("IdUsuario", obj.IdUsuario);
            ins.add("Fecha_creacion", obj.Fecha_creacion);
            ins.add("Idusuario_modifica", obj.Idusuario_modifica);
            ins.add("Fecha_modificacion", obj.Fecha_modificacion);
            ins.add("Activo", obj.Activo);
            ins.add("Estado_servicio", obj.Estado_servicio);
            ins.add("IdTipoUsuario", obj.IdTipoUsuario);
            ins.add("Num_tarjeta", obj.Num_tarjeta);
            ins.add("Tipo_registro", obj.Tipo_registro);
            ins.add("Servicio_bajo_demandafp", obj.Servicio_bajo_demandafp);
            ins.add("Es_autoproductor", obj.Es_autoproductor);

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
