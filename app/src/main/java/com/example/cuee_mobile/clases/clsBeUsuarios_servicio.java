package com.example.cuee_mobile.clases;

public class clsBeUsuarios_servicio {

    public int IdUsuarioServicio = 0;
    public String DPI = "";
    public String NIT = "";
    public String Nombres = "";
    public String Telefono = "";
    public int IdUsuario = 0;
    public String Fecha_creacion = "";
    public int Idusuario_modifica = 0;
    public String Fecha_modificacion = "";
    public boolean Activo = false;
    public String Correo_electronico = "";
    public boolean Exento_IVA = false;

    public int getIdUsuarioServicio() {
        return IdUsuarioServicio;
    }

    public void setIdUsuarioServicio(int idUsuarioServicio) {
        IdUsuarioServicio = idUsuarioServicio;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getFecha_creacion() {
        return Fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        Fecha_creacion = fecha_creacion;
    }

    public int getIdusuario_modifica() {
        return Idusuario_modifica;
    }

    public void setIdusuario_modifica(int idusuario_modifica) {
        Idusuario_modifica = idusuario_modifica;
    }

    public String getFecha_modificacion() {
        return Fecha_modificacion;
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        Fecha_modificacion = fecha_modificacion;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean activo) {
        Activo = activo;
    }

    public String getCorreo_electronico() {
        return Correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        Correo_electronico = correo_electronico;
    }

    public boolean isExento_IVA() {
        return Exento_IVA;
    }

    public void setExento_IVA(boolean exento_IVA) {
        Exento_IVA = exento_IVA;
    }
}
