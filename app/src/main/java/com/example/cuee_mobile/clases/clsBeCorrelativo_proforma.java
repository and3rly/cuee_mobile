package com.example.cuee_mobile.clases;

import com.google.gson.annotations.SerializedName;

public class clsBeCorrelativo_proforma {
    public int idCorrelativoProforma;
    public String serie;
    public int inicial;

    @SerializedName("final")
    public int cfinal;
    public int actual;
    public int idtecnico;
    public boolean activo;
    public String fec_agr;
    public String user_agr;
    public int StatCom = 0;

    public int getIdCorrelativoProforma() {
        return idCorrelativoProforma;
    }

    public void setIdCorrelativoProforma(int idCorrelativoProforma) {
        this.idCorrelativoProforma = idCorrelativoProforma;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getInicial() {
        return inicial;
    }

    public void setInicial(int inicial) {
        this.inicial = inicial;
    }

    public int getCfinal() {
        return cfinal;
    }

    public void setCfinal(int cfinal) {
        this.cfinal = cfinal;
    }

    public int getAcutal() {
        return actual;
    }

    public void setAcutal(int acutal) {
        this.actual = acutal;
    }

    public int getIdtecnico() {
        return idtecnico;
    }

    public void setIdtecnico(int idtecnico) {
        this.idtecnico = idtecnico;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getFec_agr() {
        return fec_agr;
    }

    public void setFec_agr(String fec_agr) {
        this.fec_agr = fec_agr;
    }

    public String getUser_agr() {
        return user_agr;
    }

    public void setUser_agr(String user_agr) {
        this.user_agr = user_agr;
    }

    public String getFec_mod() {
        return fec_mod;
    }

    public void setFec_mod(String fec_mod) {
        this.fec_mod = fec_mod;
    }

    public String getUser_mod() {
        return user_mod;
    }

    public void setUser_mod(String user_mod) {
        this.user_mod = user_mod;
    }

    public String fec_mod;
    public String user_mod;


}
