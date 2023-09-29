package com.example.cuee_mobile.clases;

public class clsBePagos_detalle_rep {
    private int id = 0;
    private int idusuarioservicio = 0;
    private String nombreusuarioservicio = "";
    private String nocontador = "";
    private String nomarchamo = "";
    private String tiposervicio = "";
    private String tipotarifa = "";
    private int nomes = 0;
    private int año = 0;
    private double voltios = 0;
    private double lectura_anterior = 0;
    private double lectura_actual = 0;
    private double consumo = 0;
    private String fecha_inicio = "";
    private String fecha_fin = "";
    private int dias_facturados = 0;
    private String mes_operacion = "";
    private String fecha_emision = "";
    private double cargo_fijo = 0;
    private double consumo_energia_ts = 0;
    private double importe_ts = 0;
    private double consumo_energia_tns = 0;
    private double importe_tns = 0;
    private double consumo_energia_total = 0;
    private double importe_consumo_total = 0;
    private double alumbrado_publico = 0;
    private String fecha_sistema = "";
    private double potencia_contratada = 0;
    private double potencia_maxima = 0;
    private double importe_potencia_maxima = 0;
    private boolean servicio_bajo_demanda = false;
    private int IdPago = 0;
    private boolean servicio_bajo_demandafp = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdusuarioservicio() {
        return idusuarioservicio;
    }

    public void setIdusuarioservicio(int idusuarioservicio) {
        this.idusuarioservicio = idusuarioservicio;
    }

    public String getNombreusuarioservicio() {
        return nombreusuarioservicio;
    }

    public void setNombreusuarioservicio(String nombreusuarioservicio) {
        this.nombreusuarioservicio = nombreusuarioservicio;
    }

    public String getNocontador() {
        return nocontador;
    }

    public void setNocontador(String nocontador) {
        this.nocontador = nocontador;
    }

    public String getNomarchamo() {
        return nomarchamo;
    }

    public void setNomarchamo(String nomarchamo) {
        this.nomarchamo = nomarchamo;
    }

    public String getTiposervicio() {
        return tiposervicio;
    }

    public void setTiposervicio(String tiposervicio) {
        this.tiposervicio = tiposervicio;
    }

    public String getTipotarifa() {
        return tipotarifa;
    }

    public void setTipotarifa(String tipotarifa) {
        this.tipotarifa = tipotarifa;
    }

    public int getNomes() {
        return nomes;
    }

    public void setNomes(int nomes) {
        this.nomes = nomes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getVoltios() {
        return voltios;
    }

    public void setVoltios(double voltios) {
        this.voltios = voltios;
    }

    public double getLectura_anterior() {
        return lectura_anterior;
    }

    public void setLectura_anterior(double lectura_anterior) {
        this.lectura_anterior = lectura_anterior;
    }

    public double getLectura_actual() {
        return lectura_actual;
    }

    public void setLectura_actual(double lectura_actual) {
        this.lectura_actual = lectura_actual;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getDias_facturados() {
        return dias_facturados;
    }

    public void setDias_facturados(int dias_facturados) {
        this.dias_facturados = dias_facturados;
    }

    public String getMes_operacion() {
        return mes_operacion;
    }

    public void setMes_operacion(String mes_operacion) {
        this.mes_operacion = mes_operacion;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public double getCargo_fijo() {
        return cargo_fijo;
    }

    public void setCargo_fijo(double cargo_fijo) {
        this.cargo_fijo = cargo_fijo;
    }

    public double getConsumo_energia_ts() {
        return consumo_energia_ts;
    }

    public void setConsumo_energia_ts(double consumo_energia_ts) {
        this.consumo_energia_ts = consumo_energia_ts;
    }

    public double getImporte_ts() {
        return importe_ts;
    }

    public void setImporte_ts(double importe_ts) {
        this.importe_ts = importe_ts;
    }

    public double getConsumo_energia_tns() {
        return consumo_energia_tns;
    }

    public void setConsumo_energia_tns(double consumo_energia_tns) {
        this.consumo_energia_tns = consumo_energia_tns;
    }

    public double getImporte_tns() {
        return importe_tns;
    }

    public void setImporte_tns(double importe_tns) {
        this.importe_tns = importe_tns;
    }

    public double getConsumo_energia_total() {
        return consumo_energia_total;
    }

    public void setConsumo_energia_total(double consumo_energia_total) {
        this.consumo_energia_total = consumo_energia_total;
    }

    public double getImporte_consumo_total() {
        return importe_consumo_total;
    }

    public void setImporte_consumo_total(double importe_consumo_total) {
        this.importe_consumo_total = importe_consumo_total;
    }

    public double getAlumbrado_publico() {
        return alumbrado_publico;
    }

    public void setAlumbrado_publico(double alumbrado_publico) {
        this.alumbrado_publico = alumbrado_publico;
    }

    public String getFecha_sistema() {
        return fecha_sistema;
    }

    public void setFecha_sistema(String fecha_sistema) {
        this.fecha_sistema = fecha_sistema;
    }

    public double getPotencia_contratada() {
        return potencia_contratada;
    }

    public void setPotencia_contratada(double potencia_contratada) {
        this.potencia_contratada = potencia_contratada;
    }

    public double getPotencia_maxima() {
        return potencia_maxima;
    }

    public void setPotencia_maxima(double potencia_maxima) {
        this.potencia_maxima = potencia_maxima;
    }

    public double getImporte_potencia_maxima() {
        return importe_potencia_maxima;
    }

    public void setImporte_potencia_maxima(double importe_potencia_maxima) {
        this.importe_potencia_maxima = importe_potencia_maxima;
    }

    public boolean isServicio_bajo_demanda() {
        return servicio_bajo_demanda;
    }

    public void setServicio_bajo_demanda(boolean servicio_bajo_demanda) {
        this.servicio_bajo_demanda = servicio_bajo_demanda;
    }

    public int getIdPago() {
        return IdPago;
    }

    public void setIdPago(int idPago) {
        IdPago = idPago;
    }

    public boolean isServicio_bajo_demandafp() {
        return servicio_bajo_demandafp;
    }

    public void setServicio_bajo_demandafp(boolean servicio_bajo_demandafp) {
        this.servicio_bajo_demandafp = servicio_bajo_demandafp;
    }
}
