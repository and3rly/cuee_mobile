package com.example.cuee_mobile.clases;

public class clsBeMeses_mora_pagada {

    private int IdRecibo = 0;
    private int NoMes = 0;
    private int Anno = 0;
    private boolean MoraPagada = false;
    private boolean Anulado = false;

    public int getIdRecibo() {
        return IdRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        IdRecibo = idRecibo;
    }

    public int getNoMes() {
        return NoMes;
    }

    public void setNoMes(int noMes) {
        NoMes = noMes;
    }

    public int getAnno() {
        return Anno;
    }

    public void setAnno(int anno) {
        Anno = anno;
    }

    public boolean isMoraPagada() {
        return MoraPagada;
    }

    public void setMoraPagada(boolean moraPagada) {
        MoraPagada = moraPagada;
    }

    public boolean isAnulado() {
        return Anulado;
    }

    public void setAnulado(boolean anulado) {
        Anulado = anulado;
    }
}
