package com.example.cuee_mobile.clases;

public class clsBeProforma_detalle {

    public int idproforma = 0;
    public int idrenglon = 0;
    public String descripcion = "";
    public double cantidad = 0;
    public boolean exonera = false;
    public double monto_impuesto = 0;
    public boolean exento = false;
    public double monto_gravable = 0;

    public int getIdproforma() {
        return idproforma;
    }

    public void setIdproforma(int idproforma) {
        this.idproforma = idproforma;
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

    public boolean isExonera() {
        return exonera;
    }

    public void setExonera(boolean exonera) {
        this.exonera = exonera;
    }

    public double getMonto_impuesto() {
        return monto_impuesto;
    }

    public void setMonto_impuesto(double monto_impuesto) {
        this.monto_impuesto = monto_impuesto;
    }

    public boolean isExento() {
        return exento;
    }

    public void setExento(boolean exento) {
        this.exento = exento;
    }

    public double getMonto_gravable() {
        return monto_gravable;
    }

    public void setMonto_gravable(double monto_gravable) {
        this.monto_gravable = monto_gravable;
    }
}
