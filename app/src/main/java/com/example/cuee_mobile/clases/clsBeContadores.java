package com.example.cuee_mobile.clases;

public class clsBeContadores {
    private String IdContador = "";
    private int IdMarca = 0;
    private boolean Activo = false;
    private String No_marchamo = "";
    private String Color = "";
    private int IdUsuarioServicio = 0;
    private String Fecha_Cambio = "";
    private String Fecha_Creacion = "";
    private double Lectura = 0;

    public String getIdContador() {
        return IdContador;
    }

    public void setIdContador(String idContador) {
        IdContador = idContador;
    }

    public int getIdMarca() {
        return IdMarca;
    }

    public void setIdMarca(int idMarca) {
        IdMarca = idMarca;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean activo) {
        Activo = activo;
    }

    public String getNo_marchamo() {
        return No_marchamo;
    }

    public void setNo_marchamo(String no_marchamo) {
        No_marchamo = no_marchamo;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getIdUsuarioServicio() {
        return IdUsuarioServicio;
    }

    public void setIdUsuarioServicio(int idUsuarioServicio) {
        IdUsuarioServicio = idUsuarioServicio;
    }

    public String getFecha_Cambio() {
        return Fecha_Cambio;
    }

    public void setFecha_Cambio(String fecha_Cambio) {
        Fecha_Cambio = fecha_Cambio;
    }

    public String getFecha_Creacion() {
        return Fecha_Creacion;
    }

    public void setFecha_Creacion(String fecha_Creacion) {
        Fecha_Creacion = fecha_Creacion;
    }

    public double getLectura() {
        return Lectura;
    }

    public void setLectura(double lectura) {
        Lectura = lectura;
    }
}
