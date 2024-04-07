package com.example.cuee_mobile.base;
import android.app.Application;

import com.example.cuee_mobile.clases.clsBeInstitucion;
import com.example.cuee_mobile.clases.clsBeRuta_lectura;
import com.example.cuee_mobile.clases.clsBeTecnicos;

public class VarGlobal extends Application {
    public clsBeRuta_lectura ruta = new clsBeRuta_lectura();
    public clsBeTecnicos tecnico = new clsBeTecnicos();
    public clsBeInstitucion institucion = new clsBeInstitucion();
    public String termino ="", path= "";
    public String version = "1.6";
    public String vFecha ="06-04-2024";
    public String urlApi;
    public int itinerario;
    public boolean cierreRuta = false;
}
