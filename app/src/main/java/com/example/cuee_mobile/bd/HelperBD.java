package com.example.cuee_mobile.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperBD extends SQLiteOpenHelper {
    private ScriptBD scriptBD;

    public HelperBD(@Nullable Context context, String nombre) {
        super(context, nombre, null, 1);
        scriptBD = new ScriptBD(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        scriptBD.ejecutarScript(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
