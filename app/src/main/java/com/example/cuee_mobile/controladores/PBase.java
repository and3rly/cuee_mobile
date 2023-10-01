package com.example.cuee_mobile.controladores;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.cuee_mobile.api.ClienteConfig;
import com.example.cuee_mobile.base.CueeHelper;
import com.example.cuee_mobile.bd.HelperBD;

public class PBase extends Activity {
    protected ClienteConfig retrofit;
    protected HelperBD Con;
    protected CueeHelper helper;
    protected SQLiteDatabase db;
    protected String sql;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void SetBase() {
        retrofit = new ClienteConfig(this);
        helper = new CueeHelper(this);

        String pathDataDir = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pathDataDir = this.getApplicationContext().getDataDir().getPath()+"/database";
        }

        String nombre = pathDataDir + "/db_cuee.db";
        Con = new HelperBD(this, nombre);
        opendb();
    }
    public void opendb() {
        try {
            db = Con.getWritableDatabase();
            Con.vDatabase = db;
        } catch (Exception e) {
        }
    }
}
