package com.example.cuee_mobile.modelos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.cuee_mobile.bd.HelperBD;

public class CatalogoModel {
    private Context context;
    private HelperBD Con;
    private SQLiteDatabase db;
    public HelperBD.Insert ins;
    public HelperBD.Update upd;

    public CatalogoModel(Context ct, HelperBD con, SQLiteDatabase dbase) {
        context = ct;
        Con = con;
        db = dbase;
        ins = Con.Ins; upd = Con.Upd;
    }
}
