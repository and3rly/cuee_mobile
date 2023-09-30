package com.example.cuee_mobile.clases;

public class clsBeRuta_tecnico {

    public int IdRutaTecnico = 0;
    public int IdTecnico = 0;
    public int IdRuta = 0;
    public boolean Activo = false;
    public String Fecha_Agr = "";
    public int User_Agr = 0;
    public String Fecha_Mod = "";
    public int User_Mod = 0;

    public int getIdRutaTecnico() {
        return IdRutaTecnico;
    }

    public void setIdRutaTecnico(int idRutaTecnico) {
        IdRutaTecnico = idRutaTecnico;
    }

    public int getIdTecnico() {
        return IdTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        IdTecnico = idTecnico;
    }

    public int getIdRuta() {
        return IdRuta;
    }

    public void setIdRuta(int idRuta) {
        IdRuta = idRuta;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean activo) {
        Activo = activo;
    }

    public String getFecha_Agr() {
        return Fecha_Agr;
    }

    public void setFecha_Agr(String fecha_Agr) {
        Fecha_Agr = fecha_Agr;
    }

    public int getUser_Agr() {
        return User_Agr;
    }

    public void setUser_Agr(int user_Agr) {
        User_Agr = user_Agr;
    }

    public String getFecha_Mod() {
        return Fecha_Mod;
    }

    public void setFecha_Mod(String fecha_Mod) {
        Fecha_Mod = fecha_Mod;
    }

    public int getUser_Mod() {
        return User_Mod;
    }

    public void setUser_Mod(int user_Mod) {
        User_Mod = user_Mod;
    }
}
