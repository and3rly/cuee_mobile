package com.example.cuee_mobile.clases;

import java.util.ArrayList;

public class clsBeProforma {
    public int idproforma = 0;
    public int IdUsuarioServicio = 0;
    public String fecha_recibo = "";
    public double num_proforma = 0;
    public String serie_proforma = "";
    public boolean anulado = false;
    public int idusuario = 0;
    public String fecha_creacion = "";
    public int idusuario_modifica = 0;
    public String fecha_modifica = "";
    public int mes_pago = 0;
    public int año_pago = 0;
    public boolean Impresion = false;
    public String proveedor = "";
    public String observacion = "";
    public int IdPeriodoParametros = 0;
    public boolean pagol = false;
    public String fecha_ultimo_pago = "";
    public int Con_hh = 0;
    public int IdTecnico = 0;
    public ArrayList<clsBeProforma_detalle> detalle = new ArrayList<>();
    public ArrayList<clsBeMeses_proforma> MesesProforma = new ArrayList<>();

    public String StatCom = "N";

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

    public String getFecha_recibo() {
        return fecha_recibo;
    }

    public void setFecha_recibo(String fecha_recibo) {
        this.fecha_recibo = fecha_recibo;
    }

    public double getNum_proforma() {
        return num_proforma;
    }

    public void setNum_proforma(double num_proforma) {
        this.num_proforma = num_proforma;
    }

    public String getSerie_proforma() {
        return serie_proforma;
    }

    public void setSerie_proforma(String serie_proforma) {
        this.serie_proforma = serie_proforma;
    }

    public boolean isAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getIdusuario_modifica() {
        return idusuario_modifica;
    }

    public void setIdusuario_modifica(int idusuario_modifica) {
        this.idusuario_modifica = idusuario_modifica;
    }

    public String getFecha_modifica() {
        return fecha_modifica;
    }

    public void setFecha_modifica(String fecha_modifica) {
        this.fecha_modifica = fecha_modifica;
    }

    public int getMes_pago() {
        return mes_pago;
    }

    public void setMes_pago(int mes_pago) {
        this.mes_pago = mes_pago;
    }

    public int getAño_pago() {
        return año_pago;
    }

    public void setAño_pago(int año_pago) {
        this.año_pago = año_pago;
    }

    public boolean isImpresion() {
        return Impresion;
    }

    public void setImpresion(boolean impresion) {
        Impresion = impresion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getIdPeriodoParametros() {
        return IdPeriodoParametros;
    }

    public void setIdPeriodoParametros(int idPeriodoParametros) {
        IdPeriodoParametros = idPeriodoParametros;
    }

    public boolean isPagol() {
        return pagol;
    }

    public void setPagol(boolean pagol) {
        this.pagol = pagol;
    }

    public String getFecha_ultimo_pago() {
        return fecha_ultimo_pago;
    }

    public void setFecha_ultimo_pago(String fecha_ultimo_pago) {
        this.fecha_ultimo_pago = fecha_ultimo_pago;
    }
}
