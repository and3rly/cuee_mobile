package com.example.cuee_mobile.clases;

public class clsBeUsuarios_por_ruta {
    private int IdRuta = 0;
    private int IdItinerario = 0;
    private int IdUsuarioServicio = 0;
    private String NombreItinerario = "";
    private int Orden = 0;

    public int getIdRuta() {
        return IdRuta;
    }

    public void setIdRuta(int idRuta) {
        IdRuta = idRuta;
    }

    public int getIdItinerario() {
        return IdItinerario;
    }

    public void setIdItinerario(int idItinerario) {
        IdItinerario = idItinerario;
    }

    public int getIdUsuarioServicio() {
        return IdUsuarioServicio;
    }

    public void setIdUsuarioServicio(int idUsuarioServicio) {
        IdUsuarioServicio = idUsuarioServicio;
    }

    public String getNombreItinerario() {
        return NombreItinerario;
    }

    public void setNombreItinerario(String nombreItinerario) {
        NombreItinerario = nombreItinerario;
    }

    public int getOrden() {
        return Orden;
    }

    public void setOrden(int orden) {
        Orden = orden;
    }
}
