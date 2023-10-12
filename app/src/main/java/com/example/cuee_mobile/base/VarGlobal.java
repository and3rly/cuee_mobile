package com.example.cuee_mobile.base;
import android.app.Application;

import com.example.cuee_mobile.clases.clsBeInstitucion;
import com.example.cuee_mobile.clases.clsBeRuta_lectura;
import com.example.cuee_mobile.clases.clsBeTecnicos;

public class VarGlobal extends Application {
    public clsBeRuta_lectura ruta = new clsBeRuta_lectura();
    public clsBeTecnicos tecnico = new clsBeTecnicos();
    public clsBeInstitucion institucion = new clsBeInstitucion();
    public String termino ="";
}
