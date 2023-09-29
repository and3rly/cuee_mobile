package com.example.cuee_mobile.clases;

public class clsBeMeses_proforma {

    private int idproformadet = 0;
    private int idproforma = 0;
    private int IdUsuarioServicio = 0;
    private int nomes = 0;
    private String mes = "";
    private int idrenglon = 0;
    private String descripcion = "";
    private double cantidad = 0;
    private int anno = 0;

    public int getIdproformadet() {
        return idproformadet;
    }

    public void setIdproformadet(int idproformadet) {
        this.idproformadet = idproformadet;
    }

    public int getIdproforma() {
        return idproforma;
    }

    public void setIdproforma(int idproforma) {
        this.idproforma = idproforma;
    }

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
