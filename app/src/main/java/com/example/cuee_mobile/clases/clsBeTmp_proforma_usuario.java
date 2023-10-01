package com.example.cuee_mobile.clases;

public class clsBeTmp_proforma_usuario {

    public int IdUsuarioServicio = 0;
    public int nomes = 0;
    public String mes = "";
    public int idrenglon = 0;
    public String descripcion = "";
    public double cantidad = 0;
    public int anno = 0;

    public int getIdUsuarioServicio() {
        return IdUsuarioServicio;
    }

    public void setIdUsuarioServicio(int idUsuarioServicio) {
        IdUsuarioServicio = idUsuarioServicio;
    }

    public int getNomes() {
        return nomes;
    }

    public void setNomes(int nomes) {
        this.nomes = nomes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getIdrenglon() {
        return idrenglon;
    }

    public void setIdrenglon(int idrenglon) {
        this.idrenglon = idrenglon;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }
}
