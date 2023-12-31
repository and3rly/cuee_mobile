package com.example.cuee_mobile.controladores;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.clases.clsBeInstitucion;
import com.example.cuee_mobile.clases.clsBeRuta_lectura;
import com.example.cuee_mobile.clases.clsBeTecnicos;
import com.example.cuee_mobile.controladores.comunicacion.ComApi;
import com.example.cuee_mobile.modelos.institucion.InstitucionModel;
import com.example.cuee_mobile.modelos.mnt.TecnicoModel;
import com.example.cuee_mobile.modelos.ruta.RutaLecturaModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends PBase {

    private Button btnLogin;
    private EditText txtClave;
    private Spinner cmbTecnicos;
    private TextView lblEmpresa, lblRuta, lblVersion;
    private InstitucionModel institucion;
    private RutaLecturaModel rutaLectura;
    private TecnicoModel tecnico;
    private clsBeInstitucion objInstitucion = null;
    private clsBeRuta_lectura objRutaLec = null;
    private ArrayList<clsBeTecnicos> listaTec = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            grantPermissions();
        } catch (Exception e) {
            helper.msgbox(new Object() {
            }.getClass().getEnclosingMethod().getName() + " . " + e.getMessage());
        }
    }

    private void iniciarApp() {
        try {
            super.SetBase();

            cmbTecnicos = findViewById(R.id.cmbTecnicos);
            txtClave = findViewById(R.id.txtClave);
            lblRuta = findViewById(R.id.lblRuta);
            lblEmpresa = findViewById(R.id.lblEmpresa);
            lblVersion = findViewById(R.id.lblVersion);
            btnLogin = findViewById(R.id.btnLogin);

            setHandlers();
            setDatos();
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }
    private void setDatos() {
        try {
            setModels();
            if (getInstitucion()) {
                if (getRutaLectura()) {
                    getTecnicos();
                    lblVersion.setText("Versión " + gl.version + " / " + gl.vFecha);
                } else {
                    startActivity(new Intent(this, ComApi.class));
                    super.finish();
                }
            } else {
                startActivity(new Intent(this, ComApi.class));
                super.finish();
            }
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private void setHandlers() {
        try {
            btnLogin.setOnClickListener(view -> {
                login();
            });

            txtClave.setOnKeyListener((v, keyCode, event) -> {
                try {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        switch (keyCode) {
                            case KeyEvent.KEYCODE_ENTER:
                                login();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            });

            cmbTecnicos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    TextView spinlabel = (TextView) parent.getChildAt(0);

                    if (spinlabel != null){
                        spinlabel.setTextColor(Color.BLACK);
                        spinlabel.setPadding(5,0,0,0);spinlabel.setTextSize(18);
                        spinlabel.setTypeface(spinlabel.getTypeface(), Typeface.BOLD);
                    }

                    gl.tecnico  = listaTec.get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private void setModels() {
        try {
            institucion = new InstitucionModel(this, Con, db);
            rutaLectura = new RutaLecturaModel(this, Con, db);
            tecnico = new TecnicoModel(this, Con,db);
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private boolean getInstitucion() {
        try {
            institucion.getLinea();
            objInstitucion = institucion.objInstitucion;

            if (objInstitucion != null) {
                gl.institucion = objInstitucion;
                lblEmpresa.setText(objInstitucion.Nombre_Comercial);
            } else {
                return false;
            }

        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }

        return true;
    }

    private boolean getRutaLectura() {
        try {
            rutaLectura.getLinea();
            objRutaLec = rutaLectura.objRutaLec;

            gl.itinerario = catalogo.getItinerario();

            if (objRutaLec != null) {
                lblRuta.setText(objRutaLec.Nombre + " - " + "Itinerario " +gl.itinerario);
                gl.ruta = objRutaLec;
            } else {
                return false;
            }
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }

        return true;
    }

    private void getTecnicos() {
        ArrayList<String> lTec = new ArrayList<>();
        try {
            tecnico.getLista();

            if (tecnico.lista.size() > 0) {
                listaTec = tecnico.lista;

                lTec.clear();
                for (clsBeTecnicos obj: listaTec) {
                    lTec.add(obj.Nombre);
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, lTec);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cmbTecnicos.setAdapter(dataAdapter);

                if (listaTec.size()>0) {
                    cmbTecnicos.setSelection(0);
                    txtClave.requestFocus();
                }
            } else {
                helper.msgbox("No existen tecnicos registrados.");
            }
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private void login() {
        try {
            if (!txtClave.getText().toString().isEmpty()) {
                if (txtClave.getText().toString().equals(gl.tecnico.Clave))  {
                    startActivity(new Intent(this, Menu.class));
                    super.finish();
                } else {
                    helper.msgbox("Clave incorrecta.");
                    txtClave.requestFocus();
                    txtClave.selectAll();
                }
            } else {
                helper.msgbox("Ingrese clave.");
            }
        } catch (Exception e) {
            helper.msgbox(new Object() {}.getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private void grantPermissions() {
        try {

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                iniciarApp();
            } else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.BLUETOOTH,
                                Manifest.permission.CAMERA}, 1);
                Toast.makeText(this, "Permission not granted.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Error"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        try {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                iniciarApp();
            } else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.BLUETOOTH,
                                Manifest.permission.CAMERA}, 1);
                Toast.makeText(this, "Permission not granted.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e){}

    }
}