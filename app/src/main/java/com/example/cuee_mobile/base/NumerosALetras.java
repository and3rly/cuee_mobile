package com.example.cuee_mobile.base;

import java.text.DecimalFormat;

public class NumerosALetras {
    private static final String[] UNIDADES = {"", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
    private static final String[] DECENAS = {"", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"};
    private static final String[] DIEZ_A_DIECINUEVE = {"diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"};
    private static final String[] CENTENAS = {"", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"};

    public static void main(String[] args) {
        double numero = 123456789.25;
        System.out.println(numero + " en letras es: " + convertirALetras(numero));
    }

    public static String convertirALetras(double numero) {
        if (numero == 0) {
            return "cero";
        }
        if (numero < 0 || numero >= 1000000000) {
            return "Número fuera de rango";
        }

        String resultado = "";

        long parteEntera = (long) numero;
        int parteDecimal = (int) Math.round((numero - parteEntera) * 100);

        resultado += convertirParteEnteraALetras(parteEntera) + " QUETZALES";

        if (parteDecimal > 0) {
            resultado += " con " + convertirParteEnteraALetras(parteDecimal) + " CENTAVOS";
        }

        return resultado;
    }

    private static String convertirParteEnteraALetras(long numero) {
        String resultado = "";

        // Millones
        long millones = numero / 1000000;
        if (millones > 0) {
            resultado += convertirGrupo((int) millones) + " millón";
            if (millones > 1) {
                resultado += "es";
            }
        }

        // Miles
        long miles = (numero % 1000000) / 1000;
        if (miles > 0) {
            if (!resultado.isEmpty()) {
                resultado += " ";
            }
            resultado += convertirGrupo((int) miles) + " mil";
        }

        // Cientos
        int cientos = (int) (numero % 1000);
        if ((millones > 0 || miles > 0) && cientos > 0) {
            resultado += " ";
        }
        if (cientos > 0 || (millones == 0 && miles == 0)) {
            resultado += convertirGrupo(cientos);
        }

        return resultado;
    }

    private static String convertirGrupo(int numero) {
        String resultado = "";

        int centenas = numero / 100;
        int decenasYUnidades = numero % 100;
        int decenas = decenasYUnidades / 10;
        int unidades = decenasYUnidades % 10;

        if (numero > 99) {
            resultado += CENTENAS[centenas];
        }
        if (decenasYUnidades > 0) {
            if (numero > 99) {
                resultado += " ";
            }
            if (decenasYUnidades < 20) {
                resultado += decenasYUnidades < 10 ? UNIDADES[unidades] : DIEZ_A_DIECINUEVE[unidades];
            } else {
                resultado += DECENAS[decenas];
                if (unidades > 0) {
                    resultado += " y " + UNIDADES[unidades];
                }
            }
        }

        return resultado;
    }
}
