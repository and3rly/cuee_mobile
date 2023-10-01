package com.example.cuee_mobile.clases;

public class clsBeTransformadores {
    public int IdTransformador = 0;
    public String Nombre = "";
    public boolean Activo = false;

    public int getIdTransformador() {
        return IdTransformador;
    }

    public void setIdTransformador(int idTransformador) {
        IdTransformador = idTransformador;
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
}
