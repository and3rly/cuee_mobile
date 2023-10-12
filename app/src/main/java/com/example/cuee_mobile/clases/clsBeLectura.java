package com.example.cuee_mobile.clases;

public class clsBeLectura {

    public int IdLectura = 0;
    public int IdUsuarioServicio = 0;
    public String IdContador = "";
    public String Fecha = "";
    public double Lectura = 0.0;
    public double Consumo = 0.0;
    public int IdUsuario = 0;
    public String Fecha_creacion = "";
    public double Lectura_kw = 0.0;
    public int IdTecnico = 0;
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
