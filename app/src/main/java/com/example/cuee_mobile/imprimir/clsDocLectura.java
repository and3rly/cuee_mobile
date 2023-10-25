package com.example.cuee_mobile.imprimir;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuee_mobile.base.FechaHelper;
import com.example.cuee_mobile.base.VarGlobal;
import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.clases.clsBeLectura;
import com.example.cuee_mobile.clases.clsBeUsuarios_servicio;
import com.example.cuee_mobile.modelos.lectura.LecturaModel;
import com.example.cuee_mobile.modelos.usuario.UsrServicioModel;

public class clsDocLectura extends  clsDocumento{
    protected SQLiteDatabase db;
    protected String sql;
    protected HelperBD Con;
    protected VarGlobal gl;
    protected FechaHelper du;
    private clsBeLectura lecturaActual;
    private clsBeLectura lecturaAnterior;
    private clsBeUsuarios_servicio usuario;
    private LecturaModel lectura;
    private UsrServicioModel usuarioSer;
    public clsDocLectura(Context cont, int printwidth, HelperBD con, SQLiteDatabase dbase, String archivo) {
        super(cont, printwidth, archivo);

        gl = ((VarGlobal) (((Activity) cont).getApplication()));
        du = new FechaHelper(cont);
        Con = con;
        db = dbase;
        lectura = new LecturaModel(cont, Con, db);
        usuarioSer = new UsrServicioModel(cont, Con, db);
    }

    @Override
    protected boolean loadHeadData() {
        super.loadHeadData();
        try {
            rep.add(rep.ctrim("LECTURA"));
            rep.add(gl.institucion.Nombre_Comercial);
            rep.empty();

            if (!gl.institucion.NIT_Emisor.isEmpty()) {
                rep.add("NIT: " + gl.institucion.NIT_Emisor);
            }

            rep.add("Dirección: " + gl.institucion.Direccion_emisor);
            rep.add("Fecha: "+ du.strFechaHora(du.getFechaCompleta()));
            rep.add("Ruta: No." + gl.ruta.IdRuta);
            rep.add("Itinerario: No." + gl.itinerario);
            rep.add("Técnico: " + gl.tecnico.Nombre);
            rep.empty();
        } catch (Exception e) {
            Log.e("ImpLectura", "loadHeadData: ", e);
        }
        return true;
    }

    @Override
    protected boolean loadDocData(int id) {
        try {
            lectura.getLinea("WHERE IdLectura = "+id);

            if (lectura.objLectura != null) {
                lecturaActual = lectura.objLectura;
                lecturaAnterior = lectura.getLecturaAnterior(lecturaActual.IdUsuarioServicio);

                usuarioSer.getLinea("WHERE IdUsuarioServicio = "+lecturaActual.IdUsuarioServicio);
                usuario = usuarioSer.objUsuarioServicio;
            }

        } catch (Exception e) {
            Log.e("ImpLectura", "loadDocData: ", e);
        }

        return  super.loadDocData(id);
    }

    @Override
    protected boolean buildDetail() {
        try {
            rep.add("Usuario: " +usuario.IdUsuarioServicio+"-"+usuario.Nombres);
            rep.add("Contador: "+lecturaActual.IdContador);
            rep.add2string("Lectura anterior:",lecturaAnterior.Lectura+" KW");
            rep.add2string("Lectura actual:",lecturaActual.Lectura+" KW");
            rep.add2string("Consumo:",lecturaActual.Consumo+" KW");
        } catch (Exception e) {
            Log.e("ImpLectura", "buildDetail: ", e);
        }

        return super.buildDetail();
    }

    @Override
    protected boolean buildFooter() {
        try {
            rep.empty();
            rep.empty();
            rep.addc(gl.institucion.Correo_emisor);
        } catch (Exception e) {
            Log.e("ImpLectura", "buildFooter: ", e);
        }

        return super.buildFooter();
    }
}
