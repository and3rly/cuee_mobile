package com.example.cuee_mobile.controladores;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.adapter.MenuAdapter;
import com.example.cuee_mobile.clases.auxLecturaServicio;
import com.example.cuee_mobile.clases.clsBeMenu;
import com.example.cuee_mobile.controladores.comunicacion.ComApi;
import com.example.cuee_mobile.controladores.consultas.ConsultaContadores;
import com.example.cuee_mobile.controladores.consultas.ConsultaLectura;
import com.example.cuee_mobile.controladores.lectura.Lectura;
import com.example.cuee_mobile.controladores.utilerias.Tablas;
import com.example.cuee_mobile.modelos.lectura.LecturaModel;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class Menu extends PBase {

    private TextView lblUsuario;
    private GridView grdMenu;
    private ArrayList<clsBeMenu> lista = new ArrayList<>();
    private MenuAdapter adapter;
    private LecturaModel lectura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        super.SetBase();

        lblUsuario = findViewById(R.id.lblUsuario);
        grdMenu = findViewById(R.id.grdMenu);

        lectura = new LecturaModel(this, Con, db);
        setMenu();
        setHandlers();
        lblUsuario.setText(gl.tecnico.Nombre);
    }

    private void setHandlers() {
        grdMenu.setOnItemClickListener((parent, view, position, id) -> {
            clsBeMenu item = (clsBeMenu) adapter.getItem(position);

            procesa_menu(item.id);
        });
    }

    private void setMenu() {
        try {
            clsBeMenu item;

            item = new clsBeMenu();
            item.id  = 1;
            item.titulo = "Lectura";
            item.icono = 1;
            lista.add(item);

            item = new clsBeMenu();
            item.id  = 2;
            item.titulo = "Consultas";
            item.icono = 2;
            lista.add(item);

            item = new clsBeMenu();
            item.id  = 3;
            item.titulo = "Comunicación";
            item.icono = 3;
            lista.add(item);

            item = new clsBeMenu();
            item.id  = 4;
            item.titulo = "Utilerias";
            item.icono = 4;
            lista.add(item);

            item = new clsBeMenu();
            item.id  = 5;
            item.titulo = "Cierre ruta";
            item.icono = 5;
            lista.add(item);

            item = new clsBeMenu();
            item.id  = 6;
            item.titulo = "Cerrar sesión";
            item.icono = 6;
            lista.add(item);

            adapter = new MenuAdapter(this, lista);
            grdMenu.setAdapter(adapter);

        } catch (Exception e) {
            helper.msgbox(new Object(){} .getClass().getEnclosingClass().getName() + " -" + e);
        }
    }

    private void procesa_menu(int idmenu) {
        try {
            switch (idmenu) {
                case 1:
                    startActivity(new Intent(this, Lectura.class));
                    break;
                case 2:
                    menuConsultas();
                    break;
                case 3:
                    lectura.getLista("WHERE StatCom = 0");
                    if (lectura.filas > 0) {
                        startActivity(new Intent(this, ComApi.class));
                    } else {
                        helper.toast("No hay datos pendientes para enviar.");
                    }
                    break;
                case 4:
                    menuUtilerias();
                    break;
                case 5:
                    cierreRuta();
                    break;
                case 6:
                    gl.tecnico = null;
                    startActivity(new Intent(this, MainActivity.class));
                    super.finish();
            }

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private void cierreRuta() {
        ArrayList<auxLecturaServicio> pendientes;
        try {

            lectura.getLista("WHERE StatCom = 0");
            if (lectura.filas > 0)  {
                helper.toast("Tiene lecturas pendientes de enviar.");
                return;
            }

            pendientes = lectura.getServiciosLectura(true);
            if (pendientes.size() > 0) {
                dialogoPendientes("Confirmar cierre", "Existen "+pendientes.size()+" usuarios de lectura pendiente, ¿Está seguro de continuar?");
            } else {
                dialogoPendientes("Confirmar cierre", "¿Está seguro de continuar?");
            }

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private void completaCierre() {
        long dia = du.dayofweek(du.getActDate());
        try {
            try {
                File f1 = new File(gl.path + "/database/db_cuee.db");
                File f2 = new File(gl.path + "/database/db_cuee_" + dia + ".db");
                FileUtils.copyFile(f1, f2);

                catalogo.eliminarDatosTalbas();

            } catch (Exception e) {
                helper.msgbox("No se puede generar respaldo : " + e.getMessage());
            }


        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }
   private void dialogoPendientes(String titulo, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setIcon(R.drawable.logo);
        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setPositiveButton("Si", (dialog1, id) -> {
            dialogoConfirmar("Confirmar cierre", "¿Está seguro?");
        });

        dialog.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog12, id) -> {
        });

        dialog.show();
    }
    private void dialogoConfirmar(String titulo, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setIcon(R.drawable.logo);
        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setPositiveButton("Si", (dialog1, id) -> {
            completaCierre();
        });

        dialog.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog12, id) -> {
        });

        dialog.show();
    }

    public void menuUtilerias() {
        try {
            String[] items = {"Tablas","Versión CUEE"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.config);
            builder.setTitle("Utilerias");

            builder.setItems(items, (dialog, item) -> {
                switch (item) {
                    case 0:
                        startActivity(new Intent(this, Tablas.class));
                        break;
                    case 1:
                        helper.toast("Versión 1.0");
                        break;
                }
            });

            builder.setNegativeButton("Salir", (dialog, which) -> {
                dialog.cancel();
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            nbutton.setBackgroundColor(Color.parseColor("#880E4F"));
            nbutton.setTextColor(Color.WHITE);

        } catch (Exception e) {
            helper.msgbox(new Object(){}.getClass().getEnclosingMethod().getName() +"-"+ e) ;
        }
    }

    public void menuConsultas() {
        try {
            String[] items = {"Contadores","Lecturas"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.config);
            builder.setTitle("Consultas");

            builder.setItems(items, (dialog, item) -> {
                switch (item) {
                    case 0:
                        startActivity(new Intent(this, ConsultaContadores.class));
                        break;
                    case 1:
                        startActivity(new Intent(this, ConsultaLectura.class));
                        break;
                }
            });

            builder.setNegativeButton("Salir", (dialog, which) -> {
                dialog.cancel();
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            nbutton.setBackgroundColor(Color.parseColor("#880E4F"));
            nbutton.setTextColor(Color.WHITE);

        } catch (Exception e) {
            helper.msgbox(new Object(){}.getClass().getEnclosingMethod().getName() +"-"+ e) ;
        }
    }

    @Override
    public void onBackPressed() {
        if (gl.tecnico == null) super.onBackPressed();
    }
}