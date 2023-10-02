package com.example.cuee_mobile.controladores;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.clases.clsBeInstitucion;
import com.example.cuee_mobile.clases.clsBeRuta_lectura;
import com.example.cuee_mobile.controladores.comunicacion.ComApi;
import com.example.cuee_mobile.modelos.institucion.InstitucionModel;
import com.example.cuee_mobile.modelos.ruta.RutaLecturaModel;

public class MainActivity extends PBase {

    private Button btnLogin;
    private EditText txtUsuario, txtClave, txtRuta;
    private TextView lblEmpresa;
    private InstitucionModel institucion;
    private RutaLecturaModel rutaLectura;
    private clsBeInstitucion objInstitucion = null;
    private clsBeRuta_lectura objRutaLec = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermissionGrant();
        super.SetBase();

        txtUsuario = findViewById(R.id.txtUsuario);
        txtClave = findViewById(R.id.txtClave);
        txtRuta = findViewById(R.id.txtRuta);
        lblEmpresa = findViewById(R.id.lblEmpresa);
        btnLogin = findViewById(R.id.btnLogin);

        setHandlers();
        setDatos();

    }

    private void setDatos() {
        try {
            setModels();
            getInstitucion();
            getRutaLectura();
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private void setHandlers() {
        try {
            btnLogin.setOnClickListener(view -> {
                startActivity(new Intent(this, ComApi.class));
            });
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private void setModels() {
        try {
            institucion = new InstitucionModel(this, Con, db);
            rutaLectura = new RutaLecturaModel(this, Con, db);
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private void getInstitucion() {
        try {
            institucion.getLinea();
            objInstitucion = institucion.objInstitucion;

            if (objInstitucion != null) {
                lblEmpresa.setText(objInstitucion.Nombre_Comercial);
            } else {
                startActivity(new Intent(this, ComApi.class));
                super.finish();
            }
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private void getRutaLectura() {
        try {
            rutaLectura.getLinea();
            objRutaLec = rutaLectura.objRutaLec;

            if (objRutaLec != null) {
                txtRuta.setText(objRutaLec.Nombre);
                gl.IdRuta = objRutaLec.IdRuta;
            } else {
                startActivity(new Intent(this, ComApi.class));
                super.finish();
            }
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private void getPermissionGrant() {
        if (Build.VERSION.SDK_INT >= 23) {

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
                    && checkCallingOrSelfPermission(Manifest.permission.WAKE_LOCK) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.CAMERA,
                                Manifest.permission.WAKE_LOCK,
                                Manifest.permission.READ_PHONE_STATE
                        }, 1);
            }
        }
    }
}