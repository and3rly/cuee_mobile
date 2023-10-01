package com.example.cuee_mobile.clases;

public class clsBeRenglones {
    public int Idrenglon = 0;
    public String renglon = "";
    public String especifico1 = "";
    public String especifico2 = "";
    public String concepto = "";
    public boolean exento_IVA = false;

    public int getIdrenglon() {
        return Idrenglon;
    }

    public void setIdrenglon(int idrenglon) {
        Idrenglon = idrenglon;
    }

    public String getRenglon() {
        return renglon;
    }

    public void setRenglon(String renglon) {
        this.renglon = renglon;
    }

    public String getEspecifico1() {
        return especifico1;
    }

    public void setEspecifico1(String especifico1) {
        this.especifico1 = especifico1;
    }

    public String getEspecifico2() {
        return especifico2;
    }

    public void setEspecifico2(String especifico2) {
        this.especifico2 = especifico2;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public boolean isExento_IVA() {
        return exento_IVA;
    }

    public void setExento_IVA(boolean exento_IVA) {
        this.exento_IVA = exento_IVA;
    }
}
