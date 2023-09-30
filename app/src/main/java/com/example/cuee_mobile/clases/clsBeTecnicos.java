package com.example.cuee_mobile.clases;

public class clsBeTecnicos {

    public int IdTecnico = 0;
    public String Nombre = "";
    public String Dpi = "";
    public String Direccion = "";
    public boolean Activo = false;
    public String Codigo = "";
    public String Clave = "";

    public int getIdTecnico() {
        return IdTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        IdTecnico = idTecnico;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDpi() {
        return Dpi;
    }

    public void setDpi(String dpi) {
        Dpi = dpi;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean activo) {
        Activo = activo;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }
}
