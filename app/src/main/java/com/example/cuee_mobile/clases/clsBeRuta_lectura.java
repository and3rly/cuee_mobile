package com.example.cuee_mobile.clases;

public class clsBeRuta_lectura {

    public int IdRuta;
    public String Nombre="";
    public boolean Activo = false;
    public int IdTecnicoDef;

    public int getIdRuta() {
        return IdRuta;
    }

    public void setIdRuta(int idRuta) {
        IdRuta = idRuta;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean activo) {
        Activo = activo;
    }

    public int getIdTecnicoDef() {
        return IdTecnicoDef;
    }

    public void setIdTecnicoDef(int idTecnicoDef) {
        IdTecnicoDef = idTecnicoDef;
    }
}

