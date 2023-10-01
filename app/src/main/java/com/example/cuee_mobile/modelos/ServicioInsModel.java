package com.example.cuee_mobile.modelos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeServicios_instalado;

public class ServicioInsModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;
    private final String tabla = "SERVICIOS_INSTALADO";

    public ServicioInsModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;
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
}
