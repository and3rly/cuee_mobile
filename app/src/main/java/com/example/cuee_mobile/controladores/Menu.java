package com.example.cuee_mobile.controladores;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.cuee_mobile.clases.clsBeMenu;
import com.example.cuee_mobile.controladores.comunicacion.ComApi;
import com.example.cuee_mobile.controladores.consulta_contadores.ConsultaContadores;
import com.example.cuee_mobile.controladores.lectura.Lectura;
import com.example.cuee_mobile.controladores.utilerias.Tablas;

import java.util.ArrayList;

public class Menu extends PBase {

    private TextView lblUsuario;
    private GridView grdMenu;
    private ArrayList<clsBeMenu> lista = new ArrayList<>();
    private MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        super.SetBase();

        lblUsuario = findViewById(R.id.lblUsuario);
        grdMenu = findViewById(R.id.grdMenu);

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
            item.titulo = "Consulta de contadores";
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
            item.titulo = "Cerrar sesión";
            item.icono = 5;
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
                    startActivity(new Intent(this, ConsultaContadores.class));
                    break;
                case 3:
                    startActivity(new Intent(this, ComApi.class));
                    break;
                case 4:
                    menuUtilerias();
                    break;
                case 5:
                    gl.tecnico = null;
                    startActivity(new Intent(this, MainActivity.class));
                    super.finish();
            }

        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName() +" - "+ e);
        }
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

    @Override
    public void onBackPressed() {
        if (gl.tecnico == null) super.onBackPressed();
    }
}