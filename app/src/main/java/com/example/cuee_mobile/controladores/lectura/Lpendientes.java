package com.example.cuee_mobile.controladores.lectura;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.adapter.LPendientesAdapter;
import com.example.cuee_mobile.adapter.LecturaAdapter;
import com.example.cuee_mobile.adapter.RazonesAdapter;
import com.example.cuee_mobile.clases.auxLecturaServicio;
import com.example.cuee_mobile.clases.clsBePendientes_lectura;
import com.example.cuee_mobile.clases.clsBeRazon_no_lectura;
import com.example.cuee_mobile.clases.clsBeUsuario_sin_lectura;
import com.example.cuee_mobile.controladores.PBase;
import com.example.cuee_mobile.modelos.lectura.LecturaModel;
import com.example.cuee_mobile.modelos.usuario.UsinLecturaModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.cuee_mobile.controladores.Menu.lpendientes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Lpendientes extends PBase {
    private LPendientesAdapter adapter;
    private RazonesAdapter adapterR;
    private FloatingActionButton btnContinuar;
    private ImageView btnRegresar;
    private ListView lvPendientes;
    private TextView lblTecnico, lblRegistros, lblContadorD, lblUsuarioD;
    private ListView lvRazones;
    private clsBePendientes_lectura item = null;
    private ArrayList<clsBeRazon_no_lectura> razones = new ArrayList<>();
    private int IdRazon = 0;
    private AlertDialog ad;
    private LecturaModel lecturaModel;
    private UsinLecturaModel pendientesModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lpendientes);
        super.SetBase();

        lvPendientes = findViewById(R.id.lvPendientes);
        lblTecnico = findViewById(R.id.lblTecnico);
        lblRegistros = findViewById(R.id.lblRegistros);
        btnRegresar = findViewById(R.id.btnRegresar);

        setDatos();
    }

    private void setDatos() {
        try {
            lecturaModel = new LecturaModel(this, Con, db);
            pendientesModel = new UsinLecturaModel(this, Con, db);

            setPendientesLectura();
            createDialog();
            setHandlers();

            razones = catalogo.getRazones();

            adapterR  = new RazonesAdapter(this, razones);
            lvRazones.setAdapter(adapterR);

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
        }
    }
    private void setHandlers() {
        lvPendientes.setOnItemClickListener((parent, view, position, id) -> {
            item = null;

            item = (clsBePendientes_lectura) lvPendientes.getItemAtPosition(position);
            lblContadorD.setText("CONTADOR - " + item.Contador);
            lblUsuarioD.setText(item.IdUsuarioServicio + " - " + item.Nombre);

            if (item != null) {
                lvPendientes.setClickable(false);
            }

            ad.show();
        });

        lvRazones.setOnItemClickListener((parent, view, position, id) -> {
            clsBeRazon_no_lectura irazon = new clsBeRazon_no_lectura();
            irazon = (clsBeRazon_no_lectura) lvRazones.getItemAtPosition(position);

            IdRazon = irazon.IdRazonNoLectura;
            guardar();
            ad.dismiss();
        });

        btnRegresar.setOnClickListener(view -> regresar());
    }

    private void setPendientesLectura() {
        try {
            lblTecnico.setText("Técnico: "+ gl.tecnico.Nombre);
            lpendientes.clear();
            lpendientes = lecturaModel.getLecturasPendientes();

            if (lpendientes.size() > 0) {
                adapter  = new LPendientesAdapter(this, lpendientes);
                lvPendientes.setAdapter(adapter);

                lblRegistros.setText("REGISTROS: " + lpendientes.size());
            } else {
                finish();
            }
        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
        }
    }

    private void createDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View vistaDialog = inflater.inflate(R.layout.dialog_no_lectura, null, false);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setIcon(R.drawable.file);

        dialog.setTitle("Razón no lectura");
        dialog.setView(vistaDialog);

        lblContadorD = (TextView) vistaDialog.findViewById(R.id.lblContador);
        lblUsuarioD = (TextView) vistaDialog.findViewById(R.id.lblUsuario);
        lvRazones = (ListView) vistaDialog.findViewById(R.id.lvRazones);

        dialog.setNegativeButton("Cancelar", (dialogInterface, i) -> {
            ad.dismiss();
        });
        ad = dialog.create();
    }

    private boolean guardar() {
        LocalDate  fechaNoLectura = LocalDate.now();
        boolean exito = false;
        clsBeUsuario_sin_lectura obj = new clsBeUsuario_sin_lectura();
        try {
            obj = new clsBeUsuario_sin_lectura();
            obj.IdUsuarioServicio = item.IdUsuarioServicio;
            obj.IdRuta = gl.ruta.IdRuta;
            obj.IdItinerario = gl.itinerario;
            obj.IdTecnico = gl.tecnico.IdTecnico;
            obj.IdRazonNoLectura = IdRazon;
            obj.Fecha_Crea = du.getFechaCompleta();
            obj.Fecha_Modifica = du.getFechaCompleta();
            obj.Usuario_Crea = gl.tecnico.IdTecnico;
            obj.Usuario_Modifica = gl.tecnico.IdTecnico;
            obj.StatCom = 0;
            obj.Mes = fechaNoLectura.getMonthValue();
            obj.Anno = fechaNoLectura.getYear();

            if (pendientesModel.guardarNoLectura(obj)) {
                helper.toast("Razón de no lectura guardada con éxito.");
                setPendientesLectura();
            }

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName()+" - "+ e);
        }

        return  exito;
    }

    public void regresar() {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}