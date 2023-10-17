package com.example.cuee_mobile.clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class clsBeLectura {
    @SerializedName("idLectura")
    @Expose
    public int IdLectura = 0;
    @SerializedName("idUsuarioServicio")
    @Expose
    public int IdUsuarioServicio = 0;
    @SerializedName("idContador")
    @Expose
    public String IdContador = "";
    @SerializedName("fecha")
    @Expose
    public String Fecha = "";
    @SerializedName("lectura")
    @Expose
    public double Lectura = 0.0;
    @SerializedName("consumo")
    @Expose
    public double Consumo = 0.0;
    @SerializedName("idUsuario")
    @Expose
    public int IdUsuario = 0;
    @SerializedName("fecha_creacion")
    @Expose
    public String Fecha_creacion = "";
    @SerializedName("lectura_kw")
    @Expose
    public double Lectura_kw = 0.0;
    @SerializedName("idTecnico")
    @Expose
    public int IdTecnico = 0;
    @SerializedName("con_hh")
    @Expose
    public int Con_hh = 0;
    public int StatCom= 1;

    public int getIdLectura() {
        return IdLectura;
    }

    public void setIdLectura(int idLectura) {
        IdLectura = idLectura;
    }

    public int getIdUsuarioServicio() {
        return IdUsuarioServicio;
    }

    public void setIdUsuarioServicio(int idUsuarioServicio) {
        IdUsuarioServicio = idUsuarioServicio;
    }

    public String getIdContador() {
        return IdContador;
    }

    public void setIdContador(String idContador) {
        IdContador = idContador;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public double getLectura() {
        return Lectura;
    }

    public void setLectura(double lectura) {
        Lectura = lectura;
    }

    public double getConsumo() {
        return Consumo;
    }

    public void setConsumo(double consumo) {
        Consumo = consumo;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getFecha_creacion() {
        return Fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        Fecha_creacion = fecha_creacion;
    }

    public double getLectura_kw() {
        return Lectura_kw;
    }

    public void setLectura_kw(double lectura_kw) {
        Lectura_kw = lectura_kw;
    }

    public int getIdTecnico() {
        return IdTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        IdTecnico = idTecnico;
    }

    public int getStatCom() {
        return StatCom;
    }

    public void setStatCom(int statCom) {
        StatCom = statCom;
    }

}
