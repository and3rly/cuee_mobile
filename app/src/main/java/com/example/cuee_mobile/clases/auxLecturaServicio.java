package com.example.cuee_mobile.clases;

public class auxLecturaServicio {

    public int IdInstalacion;
    public int IdUsuarioServicio;
    public String IdContador;
    public boolean Lectura_correcta = false;
    public int Lectura_realizada = 0;
    public String Usuario;
    public int IdItinerario;
    public boolean Servicio_bajo_demandafp = false;
    public boolean Servicio_bajo_demanda = false;
    public String Direccion = "";
    public double Consumo = 0;
    public int RazonSinLectura = 0;

    public int getIdUsuarioServicio() {
        return IdUsuarioServicio;
    }

    public void setIdUsuarioServicio(int idUsuarioServicio) {
        IdUsuarioServicio = idUsuarioServicio;
    }

    public String getIdContador() {
        return IdContador;
    }

    public void setIdContador(String idContador) {
        IdContador = idContador;
    }

    public boolean getLectura_correcta() {
        return Lectura_correcta;
    }

    public void setLectura_correcta(boolean lectura_correcta) {
        Lectura_correcta = lectura_correcta;
    }

    public int getLectura_realizada() {
        return Lectura_realizada;
    }

    public void setLectura_realizada(int lectura_realizada) {
        Lectura_realizada = lectura_realizada;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public int getIdItinerario() {
        return IdItinerario;
    }

    public void setIdItinerario(int idItinerario) {
        IdItinerario = idItinerario;
    }
}
