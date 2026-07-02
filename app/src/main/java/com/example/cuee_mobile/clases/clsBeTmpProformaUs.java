package com.example.cuee_mobile.clases;

public class clsBeTmpProformaUs {
    public int IdUsuarioServicio;
    public int NoMes;
    public String Mes;
    public int IdRenglon;
    public String Descripcion;
    public double Cantidad;
    public int Anio;
    public double Mora;
    public double Demanda = 0;
    public double Consumo = 0;
    public boolean exento_iva = false;
    public boolean aplica_aporte_inde = false;
    public String rango_aporte_inde = "";
    public double precio_ts_base = 0;
    public double precio_ts_rango = 0;
    public double importe_energia_sin_aporte = 0;
    public double importe_aporte_inde = 0;
    public double iva_aporte_inde = 0;
    public double importe_energia_con_aporte = 0;
}
