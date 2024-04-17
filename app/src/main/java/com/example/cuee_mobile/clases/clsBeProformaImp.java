package com.example.cuee_mobile.clases;

import java.util.ArrayList;

public class clsBeProformaImp {

    public String Fecha_notificacion = "";
    public String Nit="";
    public String Contribuyente = "";
    public String Direccion = "";
    public String Contador = "";
    public String Monto_letras = "";
    public double Total = 0;
    public String texto1 = "EMITIR CHEQUE A NOMBRE DE: EMPRESA ELECTRICA MUNICIPAL SAN PEDRO PINULA.";
    public clsBeProforma proforma = new clsBeProforma();
    public  ArrayList<clsBeTmpProformaUs> pendientes = new ArrayList<>();
    public ArrayList<clsBeUltimo_consumo> consumos = new ArrayList<>();
    public String LecturaActual = "";
    public String LecturaAnterior = "";
}
