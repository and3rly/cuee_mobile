package com.example.cuee_mobile.controladores;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.api.ClienteConfig;
import com.example.cuee_mobile.base.CueeHelper;
import com.example.cuee_mobile.base.FechaHelper;
import com.example.cuee_mobile.base.NumerosALetras;
import com.example.cuee_mobile.base.VarGlobal;
import com.example.cuee_mobile.bd.HelperBD;
import com.example.cuee_mobile.modelos.CatalogoModel;

public class PBase extends Activity {
    protected ClienteConfig retrofit;
    protected HelperBD Con;
    protected CueeHelper helper;
    protected FechaHelper du;
    protected SQLiteDatabase db;
    protected String sql;
    protected int browse;
    protected VarGlobal gl;
    protected CatalogoModel catalogo;
    protected NumerosALetras letras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbase);
    }

    public void SetBase() {
        retrofit = new ClienteConfig(this);
        helper = new CueeHelper(this);
        du = new FechaHelper(this);
        letras = new NumerosALetras();

        gl=((VarGlobal) this.getApplication());

        String pathDataDir = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pathDataDir = this.getApplicationContext().getDataDir().getPath()+"/database";
            gl.path = this.getApplicationContext().getDataDir().getPath();
        }

        String nombre = pathDataDir + "/db_cuee.db";
        Con = new HelperBD(this, nombre);
        opendb();
        catalogo = new CatalogoModel(this, Con ,db);
    }
    public void opendb() {
        try {
            db = Con.getWritableDatabase();
            Con.vDatabase = db;
        } catch (Exception e) {
        }
    }
}
